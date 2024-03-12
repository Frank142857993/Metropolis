package com.frank142857.metropolis.block;

import com.frank142857.metropolis.init.CreativeTabInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDoubleSlabMTR extends BlockSlabMTR {
    public BlockDoubleSlabMTR(String name, Material material, String harvestTool, int harvestLevel, float hardness, BlockSlab half){
        super(name, material, half);
        setHarvestLevel(harvestTool, harvestLevel);
        setHardness(hardness);
        setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
    }

    public boolean isDouble(){
        return true;
    }
}
