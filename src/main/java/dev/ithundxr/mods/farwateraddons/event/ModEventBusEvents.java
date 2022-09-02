package dev.ithundxr.mods.farwateraddons.event;

import com.simibubi.create.Create;
import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.entity.ModEntityTypes;
import dev.ithundxr.mods.farwateraddons.entity.custom.CapybaraEntity;
import dev.ithundxr.mods.farwateraddons.event.loot.CottonSeedsFromGrassAdditionModifier;
import dev.ithundxr.mods.farwateraddons.event.loot.LostSoulInBastionAdditionModifier;
import dev.ithundxr.mods.farwateraddons.util.ModFilePackResources;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.locating.IModFile;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = FarwaterAddons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new CottonSeedsFromGrassAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(FarwaterAddons.MOD_ID, "cotton_seeds_from_grass")),
                new LostSoulInBastionAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(FarwaterAddons.MOD_ID, "lost_soul_in_bastion"))
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

    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            IModFileInfo modFileInfo = ModList.get().getModFileById(FarwaterAddons.MOD_ID);
            if (modFileInfo == null) {
                FarwaterAddons.LOGGER.error("Could not find Farwateraddons mod file info; built-in resource packs will be missing!");
                return;
            }
            IModFile modFile = modFileInfo.getFile();
            event.addRepositorySource((consumer, constructor) -> {
                consumer.accept(Pack.create(Create.asResource("faithless").toString(), false, () -> new ModFilePackResources("Faithless Texture Pack", modFile, "resourcepacks/faithless"), constructor, Pack.Position.TOP, PackSource.DEFAULT));
            });
        }
    }
}