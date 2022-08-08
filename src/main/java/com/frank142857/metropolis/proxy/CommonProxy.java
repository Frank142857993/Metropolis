package com.frank142857.metropolis.proxy;

import com.frank142857.metropolis.init.*;
import com.frank142857.metropolis.util.handlers.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.registerConfig(event);
        DimensionInit.registerDimensionTypes();
        DimensionInit.registerDimensions();
        BiomeInit.registerBiomes();
        RegistryHandler.worldGenRegistries();
    }

    public void init(FMLInitializationEvent event){
        SoundsHandler.registerSounds();
        OreDictHandler.registerOreDict();
        RegistryHandler.registerSmeltingRecipe();
        MapGenInit.registerMapGenPieces();
    }

    public void postInit(FMLPostInitializationEvent event){

    }

    public void registerItemRenderer(Item item, int meta, String id) {}

    public void registerItemRenderer(Item item, int meta, String fileName, String id) {}
}