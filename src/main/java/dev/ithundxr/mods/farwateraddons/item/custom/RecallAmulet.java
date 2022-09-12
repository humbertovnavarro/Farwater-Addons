package dev.ithundxr.mods.farwateraddons.item.custom;

import dev.ithundxr.mods.farwateraddons.config.Config;
import dev.ithundxr.mods.farwateraddons.item.ModCreativeModeTab;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.server.command.TextComponentHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RecallAmulet extends Item {
    public static ForgeConfigSpec.IntValue MaxDamage;
    public static ForgeConfigSpec.IntValue MaxDuration;

    public RecallAmulet() {
        super(new Item.Properties()
                .durability(MaxDamage.get())
                .tab(ModCreativeModeTab.FARWATERADDONS_TAB)
                .rarity(Rarity.RARE)
        );
    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.DIAMOND || super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level worldIn, Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);

        playerIn.startUsingItem(handIn);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, Level worldIn, @NotNull LivingEntity entity) {
        if (!worldIn.isClientSide) {
            ServerPlayer player = (ServerPlayer) entity;
            BlockPos bedLocation = player.getRespawnPosition();

            if (bedLocation == null) {
                player.sendMessage(TextComponentHelper.createComponentTranslation(player, "chat.farwateraddons.recall.nobed"), player.getUUID());
                return stack;
            }

            if (!Config.AllowCrossDimension.get() && player.level.dimension() != Level.OVERWORLD) {
                player.sendMessage(TextComponentHelper.createComponentTranslation(player, "chat.farwateraddons.recall.dimension"), player.getUUID());

                return stack;
            }

            double distance = entity.distanceToSqr(bedLocation.getX(), bedLocation.getY(), bedLocation.getZ());

            if (Config.PlaySounds.get() && distance > 24) {
                worldIn.playSound(null, player.blockPosition().getX(), player.blockPosition().getY(), player.blockPosition().getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 0.75F, 0.75F);
            }

            entity.stopRiding();

            if (player.level.dimension() != player.getRespawnDimension()) {
                LogicalSidedProvider.WORKQUEUE.get(LogicalSide.SERVER);
                ServerLevel transferWorld = ((ServerLevel) worldIn).getServer().getLevel(player.getRespawnDimension());

                assert transferWorld != null;
                player.teleportTo(transferWorld, bedLocation.getX() + 0.5D, bedLocation.getY() + 0.6D, bedLocation.getZ() + 0.5D, player.getRotationVector().x, player.getRotationVector().y);
            } else {
                entity.moveTo(bedLocation.getX() + 0.5D, bedLocation.getY() + 0.6D, bedLocation.getZ() + 0.5D);
            }

            entity.fallDistance = 0;

            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));

            player.awardStat(Stats.ITEM_USED.get(this));

            if (Config.PlaySounds.get())
                worldIn.playSound(null, player.blockPosition().getX(), player.blockPosition().getY(), player.blockPosition().getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 0.75F, 0.75F);
        }

        return stack;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        int maxDamage = stack.getMaxDamage();
        if (damage >= maxDamage) {
            stack.shrink(1);
        }

        super.setDamage(stack, damage);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity entity, int count) {
        if (!entity.level.isClientSide) {
            if (Config.PlaySounds.get()) {
                if (entity.hurtTime > 0) {
                    entity.stopUsingItem();
                    entity.level.playSound(null, entity.position().x, entity.position().y, entity.position().z, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1, 1);
                }

                if (count < MaxDuration.get() - 10 && count % 20 == 0) {
                    entity.level.playSound(null, entity.position().x, entity.position().y, entity.position().z, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 0.05F + 0.4F * clamp(80 - count, 1, 80) / 80.0F, 0.5F + (1 - (count / MaxDuration.get())));
                }
            }
        } else {
            if (Config.EmitParticles.get()) {
                Random rand = entity.level.random;

                for (int i = 0; i < 60; i++) {
                    entity.level.addParticle(ParticleTypes.PORTAL, entity.blockPosition().getX() + (rand.nextBoolean() ? -1 : 1) * Math.pow(rand.nextFloat(), 2) * 3, entity.blockPosition().getY() + rand.nextFloat() * 4 - 2, entity.blockPosition().getZ() + (rand.nextBoolean() ? -1 : 1) * Math.pow(rand.nextFloat(), 2) * 3, 0, 0.2D, 0);
                }
            }
        }
    }


    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW;
    }


    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return MaxDuration.get();
    }
}
