package dev.ithundxr.mods.farwateraddons;

import dev.ithundxr.mods.farwateraddons.block.ModBlocks;
import dev.ithundxr.mods.farwateraddons.item.ModItems;
import dev.ithundxr.mods.farwateraddons.painting.ModPaintings;
import dev.ithundxr.mods.farwateraddons.sound.ModSounds;
import dev.ithundxr.mods.farwateraddons.util.ModItemProperties;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FarwaterAddons.MOD_ID)
public class FarwaterAddons {
    public static final String MOD_ID = "farwateraddons";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public FarwaterAddons() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModPaintings.register(eventBus);
        ModSounds.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COTTON_PLANT.get(), RenderType.cutout());

        ModItemProperties.addCustomItemProperties();
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }
}
