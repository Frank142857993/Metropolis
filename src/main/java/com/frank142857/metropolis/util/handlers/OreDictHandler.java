package com.frank142857.metropolis.util.handlers;

import com.frank142857.metropolis.init.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {
    @SubscribeEvent
    public static void registerOreDict(){
        OreDictionary.registerOre("ingotShadowMetal", ItemInit.SHADOW_METAL_INGOT);

        OreDictionary.registerOre("oreDiamond", BlockInit.DIAMOND_ORE);
        OreDictionary.registerOre("oreRedstone", BlockInit.REDSTONE_ORE);
        OreDictionary.registerOre("oreQuartz", BlockInit.QUARTZ_ORE);
        OreDictionary.registerOre("oreShadowMetal", BlockInit.SHADOW_METAL_ORE);

        OreDictionary.registerOre("stoneMarble", BlockInit.RAW_MARBLE);

        OreDictionary.registerOre("dirt", BlockInit.HEAVY_DIRT);
        OreDictionary.registerOre("stone", BlockInit.FOUNDATION_STONE);
        OreDictionary.registerOre("cobblestone", BlockInit.FOUNDATION_STONE);
        OreDictionary.registerOre("grass", BlockInit.SURFACE_GRASS);

        OreDictionary.registerOre("cobblestone", BlockInit.SILVER_PLANKS); //Silver planks > cobblestone

        OreDictionary.registerOre("blockShadowMetal", BlockInit.SHADOW_METAL_BLOCK);
    }
}