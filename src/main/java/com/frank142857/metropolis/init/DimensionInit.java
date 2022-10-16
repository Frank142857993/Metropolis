package com.frank142857.metropolis.init;

import com.frank142857.metropolis.world.dimension.metropolis.WorldProviderMetropolis;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit {
    public static DimensionType metropolis;

    public static void init(){
        registerDimensionTypes();
        registerDimensions();
    }

    public static void registerDimensionTypes(){
        metropolis = DimensionType.register("Metropolis", "_metropolis", ConfigInit.METROPOLIS, WorldProviderMetropolis.class, false);
    }

    public static void registerDimensions(){
        DimensionManager.registerDimension(ConfigInit.METROPOLIS, metropolis);
    }
}
