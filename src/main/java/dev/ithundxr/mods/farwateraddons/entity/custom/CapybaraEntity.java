package dev.ithundxr.mods.farwateraddons.entity.custom;

import com.google.common.base.Suppliers;
import dev.ithundxr.mods.farwateraddons.entity.ModEntityTypes;
import dev.ithundxr.mods.farwateraddons.entity.client.capybara.CapybaraAnimalAttractionGoal;
import dev.ithundxr.mods.farwateraddons.item.ModItems;
import dev.ithundxr.mods.farwateraddons.sound.ModSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.navigation.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.scores.Team;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


import org.jetbrains.annotations.Nullable;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CapybaraEntity extends TamableAnimal implements ItemSteerable, IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME = SynchedEntityData.defineId(CapybaraEntity.class, EntityDataSerializers.INT);
    private final ItemBasedSteering steering = new ItemBasedSteering(this.entityData, DATA_BOOST_TIME, null);

    private static final EntityDataAccessor<Boolean> SITTING =
            SynchedEntityData.defineId(CapybaraEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Supplier<Set<ItemLike>> TEMPT_ITEMS = Suppliers.memoize(() -> {
        Stream<ItemLike> stream = Stream.of(Blocks.MELON, Items.APPLE, Items.SUGAR_CANE, Items.MELON_SLICE);
        return stream.map(ItemLike::asItem).collect(Collectors.toSet());
    });

    public CapybaraEntity(EntityType<? extends CapybaraEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.25D, Ingredient.of(TEMPT_ITEMS.get().toArray(new ItemLike[0])), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(10, new CapybaraAnimalAttractionGoal(this));
    }

    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D).build();
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.65f;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Blocks.MELON.asItem();
    }

    protected SoundEvent getAmbientSound() {
        return ModSounds.CAPYBARA_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return ModSounds.CAPYBARA_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.CAPYBARA_DEATH.get();
    }

    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            Entity entity = pSource.getEntity();
            this.setSitting(false);
            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                pAmount = (pAmount + 1.0F) / 2.0F;
            }

            return super.hurt(pSource, pAmount);
        }
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        final ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            if (stack.getItem() == Items.STICK) {
                this.setSitting(!this.isSitting());
            }
        }
        else if (TEMPT_ITEMS.get().contains(stack.getItem()) && !isTame()) {
            if (this.random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
                this.tame(player);
                this.navigation.stop();
                this.setTarget(null);
                this.setSitting(true);
                this.level.broadcastEntityEvent(this, (byte) 7);
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            else {
                this.level.broadcastEntityEvent(this, (byte) 6);
            }
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
        else if (!this.isVehicle() && !player.isSecondaryUseActive() && !this.isBaby() && !isInSittingPose()) {
            boolean flag = this.isFood(player.getItemInHand(hand));
            if (!flag && !this.isVehicle() && !player.isSecondaryUseActive()) {
                if (!this.level.isClientSide) {
                    player.startRiding(this);
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        }
        else if (!this.getPassengers().isEmpty()) {
            ejectPassengers();
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setSitting(tag.getBoolean("isSitting"));
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("isSitting", this.isSitting());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SITTING, false);
    }

    public void setSitting(boolean sitting) {
        this.entityData.set(SITTING, sitting);
        this.setOrderedToSit(sitting);
    }

    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }

    @Override
    public Team getTeam() {
        return super.getTeam();
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public CapybaraEntity getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob mob) {
        return ModEntityTypes.CAPYBARA.get().create(serverLevel);
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose poseIn, @NotNull EntityDimensions sizeIn) {
        return this.isBaby() ? 0.5F : 0.9F;
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(ModItems.CAPYBARA_SPAWN_EGG.get());
    }

    @Override
    public void tick() {
        super.tick();
        this.floatStrider();
        this.checkInsideBlocks();
        if (getPassengers().isEmpty()) {
            for (Entity e : level.getEntities(this, getBoundingBox().inflate(0.5))) {
                if (e instanceof Mob && e.getBbWidth() <= 0.75f && e.getBbHeight() <= 0.75f && !this.isBaby() && ((Mob) e).getMobType() != MobType.WATER && !isInWater()) {
                    e.startRiding(this);
                }
            }
        } else if (isInWater()) {
            ejectPassengers();
        }
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level worldIn) {
        return new CapybaraEntity.WaterPathNavigation(this, worldIn);
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof Player player)) {
            return false;
        } else {
            return player.getMainHandItem().is(Items.CARROT_ON_A_STICK) || player.getOffhandItem().is(Items.CARROT_ON_A_STICK);
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor worldIn, @NotNull DifficultyInstance difficultyIn, @NotNull MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        if (spawnData == null) {
            spawnData = new AgeableMobGroupData(1);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnData, dataTag);
    }

    private void floatStrider() {
        if (this.isInWater()) {
            CollisionContext iselectioncontext = CollisionContext.of(this);
            if (iselectioncontext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.WATER)) {
                this.onGround = true;
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D).add(0.0D, 0.05D, 0.0D));
            }
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.capybara.walk", true));
            return PlayState.CONTINUE;
        }

        if (this.isSitting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.capybara.sitting", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.capybara.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void travel(@NotNull Vec3 pTravelVector) {
        this.travel(this, this.steering, pTravelVector);
    }

    @Override
    public float getSteeringSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225F;
    }

    @Override
    public boolean boost() {
        return this.steering.boost(this.getRandom());
    }

    @Override
    public void travelWithInput(@NotNull Vec3 pTravelVec) {
        super.travel(pTravelVec);
    }

    static class WaterPathNavigation extends GroundPathNavigation {
        WaterPathNavigation(CapybaraEntity p_i231565_1_, Level p_i231565_2_) {
            super(p_i231565_1_, p_i231565_2_);
        }

        @Override
        protected @NotNull PathFinder createPathFinder(int p_179679_1_) {
            this.nodeEvaluator = new WalkNodeEvaluator();
            return new PathFinder(this.nodeEvaluator, p_179679_1_);
        }

        @Override
        protected boolean hasValidPathType(@NotNull BlockPathTypes types) {
            return types == BlockPathTypes.WATER || super.hasValidPathType(types);
        }

        @Override
        public boolean isStableDestination(@NotNull BlockPos pos) {
            return this.level.getBlockState(pos).is(Blocks.WATER) || super.isStableDestination(pos);
        }
    }
}