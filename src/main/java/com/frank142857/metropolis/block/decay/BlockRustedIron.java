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

public class BlockRustedIron extends Block implements IHasModel, IBlockDecay {
    private final String name = "iron_block";

    public BlockRustedIron(String state, float hardness){
        super(Material.IRON);
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
        return Blocks.IRON_BLOCK.getHarvestLevel(state);
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public IBlockState getAfter(int meta) {
        if(this == BlockInit.SLIGHTLY_RUSTED_IRON_BLOCK) return BlockInit.PARTLY_RUSTED_IRON_BLOCK.getDefaultState();
        else if (this == BlockInit.PARTLY_RUSTED_IRON_BLOCK) return BlockInit.SEVERELY_RUSTED_IRON_BLOCK.getDefaultState();
        else if (this == BlockInit.SEVERELY_RUSTED_IRON_BLOCK) return BlockInit.RUSTED_IRON_BLOCK.getDefaultState();
        else return this.getDefaultState();
    }

    @Override
    public int getChance(int meta) {
        return 6000;
    }
}
