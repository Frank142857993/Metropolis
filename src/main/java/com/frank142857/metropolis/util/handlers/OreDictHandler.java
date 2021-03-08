package com.frank142857.metropolis.util.handlers;

import com.frank142857.metropolis.init.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {
    @SubscribeEvent
    public static void registerOreDict(){
        OreDictionary.registerOre("ingotSteel", new ItemStack(ItemInit.INGOTS, 1, 0));
        OreDictionary.registerOre("ingotBlueSteel", new ItemStack(ItemInit.INGOTS, 1, 1));
        OreDictionary.registerOre("ingotArgentum", new ItemStack(ItemInit.INGOTS, 1, 2));
        OreDictionary.registerOre("ingotDynamite", new ItemStack(ItemInit.INGOTS, 1, 3));

        OreDictionary.registerOre("oreQuartz", BlockInit.ORE_MTR_QUARTZ);
        OreDictionary.registerOre("oreArgentum", BlockInit.ORE_ARGENTUM);

        OreDictionary.registerOre("dirt", BlockInit.HEAVY_DIRT);
        OreDictionary.registerOre("stone", BlockInit.HARDENED_STONE);
        OreDictionary.registerOre("cobblestone", BlockInit.HARDENED_STONE);
        OreDictionary.registerOre("grass", BlockInit.SURFACE_GRASS);

        OreDictionary.registerOre("blockSteel", new ItemStack(BlockInit.METAL_BLOCKS, 1, 0));
        OreDictionary.registerOre("blockBlueSteel", new ItemStack(BlockInit.METAL_BLOCKS, 1, 1));
        OreDictionary.registerOre("blockArgentum", new ItemStack(BlockInit.METAL_BLOCKS, 1, 2));
        OreDictionary.registerOre("blockDynamite", new ItemStack(BlockInit.METAL_BLOCKS, 1, 3));
    }
}