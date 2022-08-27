package dev.ithundxr.mods.farwateraddons.entity.client.ithundxrtokenboss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.entity.custom.IThundxrTokenBoss;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class IThundxrTokenBossRenderer extends GeoEntityRenderer<IThundxrTokenBoss> {
    public IThundxrTokenBossRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new IThundxrTokenBossModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(IThundxrTokenBoss instance) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "textures/entity/tokenbosses/ithundxr.png");
    }

    @Override
    public RenderType getRenderType(IThundxrTokenBoss animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
