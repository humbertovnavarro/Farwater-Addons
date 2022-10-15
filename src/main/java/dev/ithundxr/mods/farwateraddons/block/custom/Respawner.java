package dev.ithundxr.mods.farwateraddons.block.custom;

import com.mojang.math.Vector3f;
import com.simibubi.create.content.contraptions.wrench.IWrenchable;
import dev.ithundxr.mods.farwateraddons.block.BlocksHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.server.command.TextComponentHelper;

public class Respawner extends Block implements IWrenchable {
    private static final VoxelShape SHAPE = box(1, 0, 1, 15, 16, 15);
    private static final DustParticleOptions EFFECT = new DustParticleOptions(Vector3f.XP, 1.0F);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    private final ItemStack requiredItem;

    public Respawner() {
        super(Properties.of(Material.AMETHYST).lightLevel(BlockState -> 15).noOcclusion().requiresCorrectToolForDrops().strength(20.0F));
        this.registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(TOP, false));

        String itemName = Registry.ITEM.getKey(Items.NETHER_STAR).toString();
        Item item = Registry.ITEM.get(new ResourceLocation(itemName));
        if (item == Items.AIR)
            item = Items.NETHER_STAR;
        requiredItem = new ItemStack((ItemLike) item, (int) 1F);
    }

    public Respawner(Properties pProperties, ItemStack requiredItem) {
        super(pProperties);
        this.requiredItem = requiredItem;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING, TOP);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ePos) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(
            BlockState state,
            Level world,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hit
    ) {
        ItemStack stack = player.getMainHandItem();
        if (stack.getItem() == requiredItem.getItem() && stack.getCount() >= requiredItem.getCount()) {
            float y = state.getValue(TOP) ? 0.4F : 1.4F;
            if (!player.isCreative()) {
                player.getMainHandItem().shrink(requiredItem.getCount());
            }
            for (int i = 0; i < 50; i++)
                world.addParticle(EFFECT,
                        pos.getX() + world.random.nextFloat(),
                        pos.getY() + y + world.random.nextFloat() * 0.2,
                        pos.getZ() + world.random.nextFloat(), 0, 0, 0
                );
            player.sendMessage(TextComponentHelper.createComponentTranslation(player,"message.spawn_set", new Object[0]), player.getUUID());
            if (!world.isClientSide) {
                ((ServerPlayer) player).setRespawnPosition(world.dimension(), pos, player.getYHeadRot(), true, false);
            }
            player.playSound(SoundEvents.TOTEM_USE, 0.7F, 1.0F);
            return InteractionResult.SUCCESS;
        } else {
            player.sendMessage(TextComponentHelper.createComponentTranslation(player,"message.spawn_help", requiredItem), player.getUUID());
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        if (state.getValue(TOP))
            return true;
        BlockState up = world.getBlockState(pos.above());
        return up.isAir() || (up.getBlock() == this && up.getValue(TOP));
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClientSide())
            BlocksHelper.setWithUpdate(world, pos.above(), state.setValue(TOP, true));
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Direction facing,
            BlockState neighborState,
            LevelAccessor world,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        if (state.getValue(TOP)) {
            return world.getBlockState(pos.below()).getBlock() == this ? state : Blocks.AIR.defaultBlockState();
        } else {
            return world.getBlockState(pos.above()).getBlock() == this ? state : Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return BlocksHelper.rotateHorizontal(state, rotation, FACING);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return BlocksHelper.mirrorHorizontal(state, mirror, FACING);
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (player.isCreative() && state.getValue(TOP) && world.getBlockState(pos.below()).getBlock() == this) {
            world.setBlockAndUpdate(pos.below(), Blocks.AIR.defaultBlockState());
        }
        super.playerWillDestroy(world, pos, state, player);
    }
}