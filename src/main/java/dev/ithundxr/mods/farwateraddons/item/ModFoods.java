package dev.ithundxr.mods.farwateraddons.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties COTTONCANDY = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.4F).alwaysEat().build();

    public static final FoodProperties CHICKEN_NUGGET = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.6F).meat().build();
    public static final FoodProperties CHICKEN_NUGGET_RAW = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();
    public static final FoodProperties DINO_NUGGET = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.8F).meat().build();
    public static final FoodProperties DINO_NUGGET_RAW = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();
}