package dev.ithundxr.mods.farwateraddons.painting;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<Motive> PAINTING_MOVTIES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, FarwaterAddons.MOD_ID);


    public static final RegistryObject<Motive> ITHUNDXR_PAINTING =
            PAINTING_MOVTIES.register("ithundxr_painting", () -> new Motive(16, 16));


    public static void register(IEventBus eventBus) {
        PAINTING_MOVTIES.register(eventBus);
    }
}
