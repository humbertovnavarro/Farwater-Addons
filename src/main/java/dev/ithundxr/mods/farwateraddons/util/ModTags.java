package dev.ithundxr.mods.farwateraddons.util;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static class Blocks {
        //stuff here

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(FarwaterAddons.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        //stuff here

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(FarwaterAddons.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
