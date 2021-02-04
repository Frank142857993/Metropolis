package com.frank142857.metropolis.init;

import com.frank142857.metropolis.util.handlers.ConfigHandler;
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
        metropolis = DimensionType.register("Metropolis", "_metropolis", ConfigHandler.METROPOLIS, WorldProviderMetropolis.class, false);
    }

    public static void registerDimensions(){
        DimensionManager.registerDimension(ConfigHandler.METROPOLIS, metropolis);
    }
}
