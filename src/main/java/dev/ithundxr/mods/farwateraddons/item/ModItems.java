package dev.ithundxr.mods.farwateraddons.item;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.block.ModBlocks;
import dev.ithundxr.mods.farwateraddons.fluid.ModFluids;
import dev.ithundxr.mods.farwateraddons.sound.ModSounds;
import net.minecraft.world.item.*;
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

    public static final RegistryObject<Item> COTTON = ITEMS.register("cotton",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).food(ModFoods.COTTONCANDY)));

    public static final RegistryObject<Item> COTTON_SEEDS = ITEMS.register("cotton_seeds",
            () -> new ItemNameBlockItem(ModBlocks.COTTON_PLANT.get(),
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));

    public static final RegistryObject<Item> MOLTEN_SUGAR_BUCKET = ITEMS.register("molten_sugar_bucket",
            () -> new BucketItem(ModFluids.MOLTEN_SUGAR,
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> THE_LOST_SOUL_MUSIC_DISC = ITEMS.register("the_lost_soul_music_disc",
            () -> new RecordItem(4, ModSounds.THE_LOST_SOUL,
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).stacksTo(1).rarity(Rarity.RARE)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
