package dev.ithundxr.mods.farwateraddons.event;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.entity.ModEntityTypes;
import dev.ithundxr.mods.farwateraddons.entity.custom.CapybaraEntity;
import dev.ithundxr.mods.farwateraddons.event.loot.CottonSeedsFromGrassAdditionModifier;
import dev.ithundxr.mods.farwateraddons.event.loot.LostSoulInBastionAdditionModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = FarwaterAddons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new CottonSeedsFromGrassAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(FarwaterAddons.MOD_ID,"cotton_seeds_from_grass")),
                new LostSoulInBastionAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(FarwaterAddons.MOD_ID,"lost_soul_in_bastion"))
        );
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
/*        event.put(ModEntityTypes.ITHUNDXRTOKENBOSS.get(), IThundxrTokenBoss.setAttributes());
        event.put(ModEntityTypes.NOVANIGHTIMETOKENBOSS.get(), NovaNightimeTokenBoss.setAttributes());
        event.put(ModEntityTypes.HYPERRACCOONTOKENBOSS.get(), HyperRaccoonTokenBoss.setAttributes());
 */

        event.put(ModEntityTypes.CAPYBARA.get(), CapybaraEntity.setAttributes());
    }
}
