package dev.ithundxr.mods.farwateraddons.utils;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.items.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        stackTexture(ModItems.CAKECOIN.get());
    }

    private static void stackTexture(Item item) {
        ItemProperties.register(
                ModItems.CAKECOIN.get(),
                new ResourceLocation(FarwaterAddons.MOD_ID, "count"),
                (stack, world, living, seed) -> (
                        (float) stack.getCount()) / stack.getMaxStackSize()
        );
    }
}
