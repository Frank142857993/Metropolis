package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.*;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockStairsMTR extends BlockStairs implements IHasModel {
    public BlockStairsMTR(String name, IBlockState modelState, float hardness, float resistance, SoundType sound, String harvestTool, int harvestLevel){
        super(modelState);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        setHardness(hardness);
        setResistance(resistance);
        setSoundType(sound);
        setHarvestLevel(harvestTool, harvestLevel);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
