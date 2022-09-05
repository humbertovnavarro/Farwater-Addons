package dev.ithundxr.mods.farwateraddons;

import dev.ithundxr.mods.farwateraddons.block.ModBlocks;
import dev.ithundxr.mods.farwateraddons.config.Config;
import dev.ithundxr.mods.farwateraddons.enchantment.ModEnchantments;
import dev.ithundxr.mods.farwateraddons.entity.ModEntityTypes;
import dev.ithundxr.mods.farwateraddons.fluid.ModFluids;
import dev.ithundxr.mods.farwateraddons.item.ModItems;
import dev.ithundxr.mods.farwateraddons.painting.ModPaintings;
import dev.ithundxr.mods.farwateraddons.sound.ModSounds;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;


@Mod(FarwaterAddons.MOD_ID)
public class FarwaterAddons {
    public static final String MOD_ID = "farwateraddons";
    public static final Logger LOGGER = LogManager.getLogger();

    public FarwaterAddons() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModPaintings.register(eventBus);
        ModSounds.register(eventBus);

        ModFluids.register(eventBus);

        ModEntityTypes.register(eventBus);

        ModEnchantments.register(eventBus);

        eventBus.addListener(this::setup);

        GeckoLib.initialize();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("farwateraddons-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("farwateraddons-common.toml"));

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
/*            SpawnPlacements.register(ModEntityTypes.ITHUNDXRTOKENBOSS.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    IThundxrTokenBoss::canSpawn);

            SpawnPlacements.register(ModEntityTypes.NOVANIGHTIMETOKENBOSS.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    NovaNightimeTokenBoss::canSpawn);

            SpawnPlacements.register(ModEntityTypes.HYPERRACCOONTOKENBOSS.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    HyperRaccoonTokenBoss::canSpawn);
*/

            SpawnPlacements.register(ModEntityTypes.CAPYBARA.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);
        });
    }
}
