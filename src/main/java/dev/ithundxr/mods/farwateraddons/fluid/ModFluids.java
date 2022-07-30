package dev.ithundxr.mods.farwateraddons.fluid;

import dev.ithundxr.mods.farwateraddons.FarwaterAddons;
import dev.ithundxr.mods.farwateraddons.block.ModBlocks;
import dev.ithundxr.mods.farwateraddons.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {

    public static final ResourceLocation MOLTEN_SUGAR_STILL_RL = new ResourceLocation("farwateraddons:fluid/molten_sugar_still");
    public static final ResourceLocation MOLTEN_SUGAR_FLOWING_RL = new ResourceLocation("farwateraddons:fluid/molten_sugar_flow");

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, FarwaterAddons.MOD_ID);


    public static final RegistryObject<FlowingFluid> MOLTEN_SUGAR
            = FLUIDS.register("molten_sugar", () -> new ForgeFlowingFluid.Source(ModFluids.MOLTEN_SUGAR_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MOLTEN_SUGAR_FLOWING
            = FLUIDS.register("molten_sugar_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.MOLTEN_SUGAR_PROPERTIES));


    public static final ForgeFlowingFluid.Properties MOLTEN_SUGAR_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MOLTEN_SUGAR.get(), () -> MOLTEN_SUGAR_FLOWING.get(), FluidAttributes.builder(MOLTEN_SUGAR_STILL_RL, MOLTEN_SUGAR_FLOWING_RL)
            .density(1400).viscosity(1500).color(0xc8fff7e0)).slopeFindDistance(6)
            .block(() -> ModFluids.MOLTEN_SUGAR_BLOCK.get()).bucket(() -> ModItems.MOLTEN_SUGAR_BUCKET.get());

    public static final RegistryObject<LiquidBlock> MOLTEN_SUGAR_BLOCK = ModBlocks.BLOCKS.register("molten_sugar",
            () -> new LiquidBlock(() -> ModFluids.MOLTEN_SUGAR.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
