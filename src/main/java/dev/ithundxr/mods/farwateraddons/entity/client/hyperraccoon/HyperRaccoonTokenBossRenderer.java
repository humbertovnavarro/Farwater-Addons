package dev.ithundxr.mods.farwateraddons.entity.client.hyperraccoon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.entity.custom.HyperRaccoonTokenBoss;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HyperRaccoonTokenBossRenderer extends GeoEntityRenderer<HyperRaccoonTokenBoss> {
    public HyperRaccoonTokenBossRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HyperRaccoonTokenBossModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(HyperRaccoonTokenBoss instance) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "textures/entity/tokenbosses/hyperraccoon.png");
    }

    @Override
    public RenderType getRenderType(HyperRaccoonTokenBoss animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
