package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;

public class BlockHalfSlabMTR extends BlockSlabMTR implements IHasModel {
    public BlockHalfSlabMTR(String name, Material material, String harvestTool, int harvestLevel, float hardness, BlockSlab half, BlockSlab doubleSlab){
        super(name, material, half);
        setHarvestLevel(harvestTool, harvestLevel);
        setHardness(hardness);
        setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        ItemInit.REGISTER_ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
    }

    public boolean isDouble(){
        return false;
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
