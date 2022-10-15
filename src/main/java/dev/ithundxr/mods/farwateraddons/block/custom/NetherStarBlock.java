package dev.ithundxr.mods.farwateraddons.block.custom;

import com.simibubi.create.content.contraptions.wrench.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class NetherStarBlock extends Block {
    public static boolean explode = false;
    public static Level world;

    public NetherStarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void destroy(LevelAccessor level, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        if (!level.isClientSide()) {
            if (explode && world != null) {
                world.explode(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 3, Explosion.BlockInteraction.DESTROY);
            }
            explode = false;
        }
        super.destroy(level, pos, state);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(!pLevel.isClientSide()) {
            if(pEntity instanceof LivingEntity) {
                LivingEntity livingEntity = ((LivingEntity) pEntity);
                livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5, 4, true, false, false));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 5, 3, true, false, true));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 5, 4, true, false, true));
            }
        }
    }
}