package com.frank142857.metropolis.block.decay;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IBlockDecay;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nullable;

public class BlockWhiteMarble extends Block implements IHasModel, IBlockDecay {
    private final String name = "white_marble";

    public BlockWhiteMarble(String state, float hardness){
        super(Material.ROCK, MapColor.QUARTZ);
        String Name = name + state;
        this.setRegistryName(Name);
        this.setUnlocalizedName(Name);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setHardness(hardness);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(Name));
    }

    @Nullable
    @Override
    public String getHarvestTool(IBlockState state) {
        return "pickaxe";
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        if(this == BlockInit.WHITE_MARBLE) return 2;
        else return this == BlockInit.CRACKED_WHITE_MARBLE ? 0 : 1;
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public IBlockState getAfter(int meta) {
        if(this == BlockInit.WHITE_MARBLE) return BlockInit.SLIGHTLY_CRACKED_WHITE_MARBLE.getDefaultState();
        else if (this == BlockInit.SLIGHTLY_CRACKED_WHITE_MARBLE) return BlockInit.SEVERELY_CRACKED_WHITE_MARBLE.getDefaultState();
        else if (this == BlockInit.SEVERELY_CRACKED_WHITE_MARBLE) return BlockInit.CRACKED_WHITE_MARBLE.getDefaultState();
        else return Blocks.AIR.getDefaultState();
    }

    @Override
    public int getChance(int meta) {
        if(this == BlockInit.WHITE_MARBLE) return 8000;
        else if (this == BlockInit.SLIGHTLY_CRACKED_WHITE_MARBLE) return 4000;
        else if (this == BlockInit.SEVERELY_CRACKED_WHITE_MARBLE) return 2000;
        else return 1000;
    }
}
