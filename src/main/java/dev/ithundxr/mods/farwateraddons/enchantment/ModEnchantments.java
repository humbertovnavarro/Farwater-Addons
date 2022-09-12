package dev.ithundxr.mods.farwateraddons.enchantment;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, FarwaterAddons.MOD_ID);

    public static RegistryObject<Enchantment> DEEPSLATE_MINER =
            ENCHANTMENTS.register("deepslate_miner",
                    () -> new DeepslateMinerEnchant(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public static RegistryObject<Enchantment> ENDSTONE_MINER =
            ENCHANTMENTS.register("endstone_miner",
                    () -> new EndstoneMinerEnchant(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public static RegistryObject<Enchantment> COBBLESTONE_MINER =
            ENCHANTMENTS.register("cobblestone_miner",
                    () -> new CobblestoneMinerEnchant(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));


    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
