package com.frank142857.metropolis.util.handlers;

import com.frank142857.metropolis.init.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {
    @SubscribeEvent
    public static void onOreDictRegistry(){
        OreDictionary.registerOre("ingotSteel", new ItemStack(ItemInit.INGOTS, 1, 0));
        OreDictionary.registerOre("ingotBlackSteel", new ItemStack(ItemInit.INGOTS, 1, 1));
        OreDictionary.registerOre("ingotBlueSteel", new ItemStack(ItemInit.INGOTS, 1, 2));
        OreDictionary.registerOre("ingotArgentum", new ItemStack(ItemInit.INGOTS, 1, 3));
        OreDictionary.registerOre("ingotDynamite", new ItemStack(ItemInit.INGOTS, 1, 4));

        OreDictionary.registerOre("oreArgentum", BlockInit.ORE_ARGENTUM);
        OreDictionary.registerOre("oreQuartz", BlockInit.ORE_MTR_QUARTZ);

        OreDictionary.registerOre("blockSteel", new ItemStack(BlockInit.METAL_BLOCKS, 1, 0));
        OreDictionary.registerOre("blockBlackSteel", new ItemStack(BlockInit.METAL_BLOCKS, 1, 1));
        OreDictionary.registerOre("blockBlueSteel", new ItemStack(BlockInit.METAL_BLOCKS, 1, 2));
        OreDictionary.registerOre("blockArgentum", new ItemStack(BlockInit.METAL_BLOCKS, 1, 3));
        OreDictionary.registerOre("blockDynamite", new ItemStack(BlockInit.METAL_BLOCKS, 1, 4));
    }
}
