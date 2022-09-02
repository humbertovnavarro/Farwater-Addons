package dev.ithundxr.mods.farwateraddons.sound;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FarwaterAddons.MOD_ID);

    public static RegistryObject<SoundEvent> THE_LOST_SOUL = registerSoundEvent("the_lost_soul");

    public static RegistryObject<SoundEvent> DAVEY_JONES_PLAYS_HIS_ORAGAN = registerSoundEvent("davey_jones_plays_his_organ");
    public static RegistryObject<SoundEvent> PSTP = registerSoundEvent("pstp");

    public static RegistryObject<SoundEvent> CAPYBARA_AMBIENT = registerSoundEvent("capybara.ambient");
    public static RegistryObject<SoundEvent> CAPYBARA_DEATH = registerSoundEvent("capybara.death");
    public static RegistryObject<SoundEvent> CAPYBARA_HURT = registerSoundEvent("capybara.hurt");


    //start ForgeSoundType here


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(FarwaterAddons.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
