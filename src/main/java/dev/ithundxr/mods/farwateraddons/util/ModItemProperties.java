package dev.ithundxr.mods.farwateraddons.util;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        coinStack(ModItems.CAKECOIN.get());
    }


    private static void coinStack(Item item) {
        ItemProperties.register(
                ModItems.CAKECOIN.get(),
                new ResourceLocation(FarwaterAddons.MOD_ID, "count"),
                (stack, world, living, seed) -> (
                        (float) stack.getCount()) / stack.getMaxStackSize()
        );

        ItemProperties.register(ModItems.CAKECOIN.get(), new ResourceLocation("count"), (stack, world, living, seed) -> ((float) stack.getCount()) / stack.getMaxStackSize());
    }
}