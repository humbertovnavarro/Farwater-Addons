package dev.ithundxr.mods.farwateraddons.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import dev.ithundxr.mods.farwateraddons.item.custom.RecallAmulet;
import dev.ithundxr.mods.farwateraddons.world.gen.ModEntityGeneration;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue PlaySounds;
    public static ForgeConfigSpec.BooleanValue EmitParticles;
    public static ForgeConfigSpec.BooleanValue AllowCrossDimension;

    public static ForgeConfigSpec.BooleanValue DeepslateInstamine;
    public static ForgeConfigSpec.BooleanValue LogsInstamine;
    public static ForgeConfigSpec.BooleanValue CobbleInstamine;
    public static ForgeConfigSpec.BooleanValue EndstoneInstamine;

    static {
        COMMON_BUILDER.push("server");

        COMMON_BUILDER.comment("Recall Amulet settings").push("recall_amulet");

        PlaySounds = COMMON_BUILDER.comment("Play sounds when using item").define("soundsEnabled", true);
        EmitParticles = COMMON_BUILDER.comment("Emit particles when using item").define("particlesEnabled", true);
        AllowCrossDimension = COMMON_BUILDER.comment("Allow to teleport across dimensions").define("allowCrossDimension", true);
        RecallAmulet.MaxDamage = COMMON_BUILDER.comment("Set max durability of item").defineInRange("durability", 50, 1, Integer.MAX_VALUE);
        RecallAmulet.MaxDuration = COMMON_BUILDER.comment("How long it should take to use the item").defineInRange("casttime", 100, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }

    static {
        COMMON_BUILDER.comment("Spawn Rates").push("spawnrates");

        ModEntityGeneration.capybaraspawnrate = COMMON_BUILDER.comment("Spawnrate For Capybaras\nlower is less spawns higher is more spawns").defineInRange("capybaraspawnrate", 3, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }

    static {
        COMMON_BUILDER.comment("Mining Configs").push("miningconfig");

        DeepslateInstamine = COMMON_BUILDER.comment("Make Deepslate Instamineable").define("InstamineDeepslate", true);
        LogsInstamine = COMMON_BUILDER.comment("Make Logs Instamineable").define("InstamineLogs", true);
        CobbleInstamine = COMMON_BUILDER.comment("Make Cobble Instamineable").define("InstamineCobble", false);
        EndstoneInstamine = COMMON_BUILDER.comment("Make Endstone Instamineable").define("InstamineEndstone", false);

        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }
}
