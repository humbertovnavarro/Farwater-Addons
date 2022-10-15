package dev.ithundxr.mods.farwateraddons.event;

import dev.ithundxr.mods.farwateraddons.config.Config;
import dev.ithundxr.mods.farwateraddons.enchantment.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class DigSpeed {
/*    @SubscribeEvent
    private void getDigSpeed(BreakSpeed event, BlockState blockState, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (blockState != null) {

            Player thiz = (Player) (Object) this;
            ItemStack itemStack = thiz.getMainHandItem();
            Item item = itemStack.getItem();
            int efficiency = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, itemStack);

            if (efficiency >= 5) {
                if (Items.NETHERITE_PICKAXE.equals(item) && item instanceof TieredItem tItem) {
                    float speed = tItem.getTier().getSpeed();

                    int dm = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.DEEPSLATE_MINER.get(), itemStack);
                    int cm = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.COBBLESTONE_MINER.get(), itemStack);
                    int em = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.ENDSTONE_MINER.get(), itemStack);

                    if (Config.DeepslateInstamine.get() && dm >= 1 && blockState.getBlock().equals(Blocks.DEEPSLATE) ||
                            (Config.CobbleInstamine.get() && cm >= 1 && blockState.getBlock().equals(Blocks.COBBLESTONE)) ||
                            (Config.EndstoneInstamine.get() && em >= 1 && blockState.getBlock().equals(Blocks.END_STONE))
                    ) {
                        speed *= 10f;
                        cir.setReturnValue(speed);
                    }
                }
            }
        }
    }*/
}
