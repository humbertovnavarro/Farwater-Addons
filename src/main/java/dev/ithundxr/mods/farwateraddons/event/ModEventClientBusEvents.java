package dev.ithundxr.mods.farwateraddons.event;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.block.ModBlocks;
import dev.ithundxr.mods.farwateraddons.entity.ModEntityTypes;
import dev.ithundxr.mods.farwateraddons.entity.client.capybara.CapybaraRenderer;
import dev.ithundxr.mods.farwateraddons.util.ModItemProperties;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FarwaterAddons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COTTON_PLANT.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.AMETHYST_GLASS.get(), RenderType.translucent());

        ModItemProperties.addCustomItemProperties();

        /*EntityRenderers.register(ModEntityTypes.ITHUNDXRTOKENBOSS.get(), IThundxrTokenBossRenderer::new);
        EntityRenderers.register(ModEntityTypes.NOVANIGHTIMETOKENBOSS.get(), NovaNightimeTokenBossRenderer::new);
        EntityRenderers.register(ModEntityTypes.HYPERRACCOONTOKENBOSS.get(), HyperRaccoonTokenBossRenderer::new);*/


        EntityRenderers.register(ModEntityTypes.CAPYBARA.get(), CapybaraRenderer::new);
    }
}