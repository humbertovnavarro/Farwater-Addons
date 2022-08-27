package dev.ithundxr.mods.farwateraddons.entity;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.entity.custom.CapybaraEntity;
import dev.ithundxr.mods.farwateraddons.entity.custom.HyperRaccoonTokenBoss;
import dev.ithundxr.mods.farwateraddons.entity.custom.IThundxrTokenBoss;
import dev.ithundxr.mods.farwateraddons.entity.custom.NovaNightimeTokenBoss;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, FarwaterAddons.MOD_ID);

/*    public static final RegistryObject<EntityType<IThundxrTokenBoss>> ITHUNDXRTOKENBOSS =
            ENTITY_TYPES.register("ithundxrtokenboss",
                    () -> EntityType.Builder.of(IThundxrTokenBoss::new, MobCategory.MONSTER)
                            .sized(0.8f, 1.9f)
                            .build(new ResourceLocation(FarwaterAddons.MOD_ID, "ithundxrtokenboss").toString()));

    public static final RegistryObject<EntityType<NovaNightimeTokenBoss>> NOVANIGHTIMETOKENBOSS =
            ENTITY_TYPES.register("novanightimetokenboss",
                    () -> EntityType.Builder.of(NovaNightimeTokenBoss::new, MobCategory.MONSTER)
                            .sized(0.8f, 1.9f)
                            .build(new ResourceLocation(FarwaterAddons.MOD_ID, "novanightimetokenboss").toString()));

    public static final RegistryObject<EntityType<HyperRaccoonTokenBoss>> HYPERRACCOONTOKENBOSS =
            ENTITY_TYPES.register("hyperraccoontokenboss",
                    () -> EntityType.Builder.of(HyperRaccoonTokenBoss::new, MobCategory.MONSTER)
                            .sized(0.8f, 1.9f)
                            .build(new ResourceLocation(FarwaterAddons.MOD_ID, "hyperraccoontokenboss").toString()));
*/

    public static final RegistryObject<EntityType<CapybaraEntity>> CAPYBARA =
            ENTITY_TYPES.register("capybara",
                    () -> EntityType.Builder.of(CapybaraEntity::new, MobCategory.MONSTER)
                            .sized(0.8f, 1.1f)
                            .build(new ResourceLocation(FarwaterAddons.MOD_ID, "capybara").toString()));




    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
