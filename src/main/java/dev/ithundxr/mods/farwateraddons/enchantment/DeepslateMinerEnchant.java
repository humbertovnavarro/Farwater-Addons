package dev.ithundxr.mods.farwateraddons.enchantment;


import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DeepslateMinerEnchant extends Enchantment {
    public DeepslateMinerEnchant(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    //@Override


    @Override
    public int getMaxLevel() {
        return 1;
    }
}
