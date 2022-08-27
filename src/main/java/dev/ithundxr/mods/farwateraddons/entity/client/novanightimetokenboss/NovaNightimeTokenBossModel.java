package dev.ithundxr.mods.farwateraddons.entity.client.novanightimetokenboss;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.entity.custom.NovaNightimeTokenBoss;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NovaNightimeTokenBossModel extends AnimatedGeoModel<NovaNightimeTokenBoss> {
    @Override
    public ResourceLocation getModelLocation(NovaNightimeTokenBoss object) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "geo/novanightimemodel.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NovaNightimeTokenBoss object) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "textures/entity/tokenbosses/novanightime.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NovaNightimeTokenBoss animatable) {
        return new ResourceLocation(FarwaterAddons.MOD_ID, "animations/tokenbossmodel.animation.json");
    }
}