package dev.ithundxr.mods.farwateraddons.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.nbt.ListTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import dev.ithundxr.mods.farwateraddons.config.Config;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(at = @At("HEAD"), cancellable = true, method = "getDigSpeed(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)F",remap=false)
    private void getDigSpeed(BlockState blockState, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if(blockState !=null){

            Player thiz=(Player)(Object)this;
            ItemStack itemStack = thiz.getMainHandItem();
            Item item = itemStack.getItem();
            int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, itemStack);

            if(j>=5 && thiz.hasEffect(MobEffects.DIG_SPEED)){
                float speed = ((TieredItem) item).getTier().getSpeed();
                MobEffectInstance eff= thiz.getEffect(MobEffects.DIG_SPEED);
                if(eff!=null && eff.getAmplifier()>=1){
                    if(Config.LogsInstamine.get() && Items.NETHERITE_AXE.equals(item)){
                        if(blockState.is(BlockTags.LOGS)) {
                            speed *= 10f;
                            cir.setReturnValue(speed);
                        }
                    }else if(Items.NETHERITE_PICKAXE.equals(item)){
                        if(blockState.getBlock().equals(Blocks.DEEPSLATE)||
                                (Config.CobbleInstamine.get() && blockState.getBlock().equals(Blocks.COBBLESTONE))||
                                (Config.EndstoneInstamine.get() && blockState.getBlock().equals(Blocks.END_STONE))
                        ) {
                            speed *= 10f;
                            cir.setReturnValue(speed);
                        }
                    }
                }
            }
        }
    }
}
