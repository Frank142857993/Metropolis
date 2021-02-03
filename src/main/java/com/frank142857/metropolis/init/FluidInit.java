package com.frank142857.metropolis.init;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.fluids.FluidBaseMTR;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.awt.*;

public class FluidInit {
    public static final FluidBaseMTR CRUDE = (FluidBaseMTR) new FluidBaseMTR("crude",
            new ResourceLocation(Metropolis.MODID, "blocks/crude_still"),
            new ResourceLocation(Metropolis.MODID, "blocks/crude_flow"))
            .setMaterial(Material.LAVA)
            .setHasBucket(true)
            .setColor(Color.BLACK)
            .setDensity(800)
            .setLuminosity(0)
            .setGaseous(false)
            .setTemperature(400)
            .setViscosity(25000);

    public static void registerFluids(){
        registerFluid(CRUDE);
    }

    public static void registerFluid(FluidBaseMTR fluid){
        FluidRegistry.registerFluid(fluid);
        if(fluid.isBucketEnabled()){
            FluidRegistry.addBucketForFluid(fluid);
        }
    }
}
