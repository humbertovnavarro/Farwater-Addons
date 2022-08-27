package dev.ithundxr.mods.farwateraddons.entity.client.hyperraccoon;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.entity.custom.HyperRaccoonTokenBoss;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HyperRaccoonTokenBossModel extends AnimatedGeoModel<HyperRaccoonTokenBoss> {
    @Override
    public ResourceLocation getModelLocation(HyperRaccoonTokenBoss object) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "geo/hyperraccoonmodel.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HyperRaccoonTokenBoss object) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "textures/entity/tokenbosses/hyperraccoon.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HyperRaccoonTokenBoss animatable) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "animations/tokenbossmodel.animation.json");
    }
}
