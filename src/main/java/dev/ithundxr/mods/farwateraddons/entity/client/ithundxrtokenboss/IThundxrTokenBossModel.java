package dev.ithundxr.mods.farwateraddons.entity.client.ithundxrtokenboss;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.entity.custom.IThundxrTokenBoss;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class IThundxrTokenBossModel extends AnimatedGeoModel<IThundxrTokenBoss> {
    @Override
    public ResourceLocation getModelLocation(IThundxrTokenBoss object) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "geo/ithundxrmodel.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(IThundxrTokenBoss object) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "textures/entity/tokenbosses/ithundxr.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(IThundxrTokenBoss animatable) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "animations/tokenbossmodel.animation.json");
    }
}
