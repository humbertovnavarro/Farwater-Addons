package dev.ithundxr.mods.farwateraddons.block.custom;

import dev.ithundxr.mods.farwateraddons.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

public class CottonPlantBlock extends CropBlock {

    public CottonPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.COTTON_SEEDS.get();
    }
}
