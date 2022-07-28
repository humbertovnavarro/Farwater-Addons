package dev.ithundxr.mods.farwateraddons.item;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FarwaterAddons.MOD_ID);

    public static final RegistryObject<Item> CAKECOIN = ITEMS.register("cakecoin",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));

    public static final RegistryObject<Item> COTTONCANDY = ITEMS.register("cottoncandy",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).food(ModFoods.COTTONCANDY)));

    public static final RegistryObject<Item> COTTON_SEEDS = ITEMS.register("cotton_seeds",
            () -> new ItemNameBlockItem(ModBlocks.COTTON_PLANT.get(),
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
