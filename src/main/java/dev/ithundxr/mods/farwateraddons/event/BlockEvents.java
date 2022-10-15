package dev.ithundxr.mods.farwateraddons.event;

import dev.ithundxr.mods.farwateraddons.block.custom.NetherStarBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockEvents {

    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {
        LevelAccessor level = event.getWorld();

        if (!level.isClientSide()) {
            Player player = event.getPlayer();
            BlockState state = event.getState();
            ItemStack stack = player.getMainHandItem();

            if (state.getBlock() instanceof NetherStarBlock && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                NetherStarBlock.explode = true;
                NetherStarBlock.world = (Level) event.getWorld();
            }
        }
    }
}