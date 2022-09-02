package dev.ithundxr.mods.farwateraddons.item;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.block.ModBlocks;
import dev.ithundxr.mods.farwateraddons.entity.ModEntityTypes;
import dev.ithundxr.mods.farwateraddons.fluid.ModFluids;
import dev.ithundxr.mods.farwateraddons.item.custom.RecallAmulet;
import dev.ithundxr.mods.farwateraddons.sound.ModSounds;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FarwaterAddons.MOD_ID);

    public static final RegistryObject<Item> ITHUNDXRITEM = ITEMS.register("ithundxr_item",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAKECOIN = ITEMS.register("cakecoin",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));

    public static final RegistryObject<Item> COTTONCANDY = ITEMS.register("cottoncandy",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).food(ModFoods.COTTONCANDY)));

    public static final RegistryObject<Item> COTTON = ITEMS.register("cotton",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));

    public static final RegistryObject<Item> COTTON_SEEDS = ITEMS.register("cotton_seeds",
            () -> new ItemNameBlockItem(ModBlocks.COTTON_PLANT.get(),
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));

    public static final RegistryObject<Item> MOLTEN_SUGAR_BUCKET = ITEMS.register("molten_sugar_bucket",
            () -> new BucketItem(ModFluids.MOLTEN_SUGAR,
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> CHICKEN_NUGGET = ITEMS.register("chicken_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).food(ModFoods.CHICKEN_NUGGET)));

    public static final RegistryObject<Item> CHICKEN_NUGGET_RAW = ITEMS.register("chicken_nugget_raw",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).food(ModFoods.CHICKEN_NUGGET_RAW)));

    public static final RegistryObject<Item> DINO_NUGGET = ITEMS.register("dino_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).food(ModFoods.DINO_NUGGET)));

    public static final RegistryObject<Item> DINO_NUGGET_RAW = ITEMS.register("dino_nugget_raw",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).food(ModFoods.DINO_NUGGET_RAW)));

    public static final RegistryObject<Item> DINO_NUGGET_CUTTER = ITEMS.register("dino_nugget_cutter",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));

    public static final RegistryObject<Item> NUGGET_CUTTER = ITEMS.register("nugget_cutter",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));

    public static final RegistryObject<Item> CAPYBARA_SPAWN_EGG = ITEMS.register("capybara_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CAPYBARA,0x9e5d39, 0x412f24,
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));

    public static final RegistryObject<Item> ORANGE_ON_A_STICK = ITEMS.register("orange_on_a_stick",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB)));

    public static final RegistryObject<Item> RECALL_AMULET = ITEMS.register("recall_amulet", RecallAmulet::new);

    public static final RegistryObject<Item> BLANK_MUSIC_DISC = ITEMS.register("blank_music_disc",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).stacksTo(16)));

    public static final RegistryObject<Item> DISC_FRAGMENT_LOST_SOUL = ITEMS.register("disc_fragment_lost_soul",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).stacksTo(8).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> THE_LOST_SOUL_MUSIC_DISC = ITEMS.register("the_lost_soul_music_disc",
            () -> new RecordItem(4, ModSounds.THE_LOST_SOUL,
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> DAVEY_JONES_PLAYS_HIS_ORAGAN_MUSIC_DISC = ITEMS.register("davey_jones_plays_his_organ_music_disc",
            () -> new RecordItem(4, ModSounds.DAVEY_JONES_PLAYS_HIS_ORAGAN,
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> PSTP_MUSIC_DISC = ITEMS.register("pstp_music_disc",
            () -> new RecordItem(4, ModSounds.PSTP,
                    new Item.Properties().tab(ModCreativeModeTab.FARWATERADDONS_TAB).stacksTo(1).rarity(Rarity.RARE)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
