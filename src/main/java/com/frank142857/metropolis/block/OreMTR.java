package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class OreMTR extends BlockOre implements IHasModel {
    public OreMTR(String name, int harvestLevel, int lightValue){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setHardness(4.5F);
        this.setHarvestLevel("pickaxe", harvestLevel);
        this.setLightLevel(lightValue);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return BlockInit.HARDENED_STONE.getMapColor(state, worldIn, pos);
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int chance) {
        if (this == BlockInit.DIAMOND_ORE) {
            return Items.DIAMOND;
        } else if (this == BlockInit.REDSTONE_ORE) {
            return Items.REDSTONE;
        } else if (this == BlockInit.QUARTZ_ORE) {
            return Items.QUARTZ;
        } else {
            return Item.getItemFromBlock(this);
        }
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess worldIn, BlockPos pos, int chance) {
        if (this == BlockInit.DIAMOND_ORE) {
            return Blocks.DIAMOND_ORE.getExpDrop(state, worldIn, pos, chance);
        } else if (this == BlockInit.REDSTONE_ORE) {
            return Blocks.REDSTONE_ORE.getExpDrop(state, worldIn, pos, chance);
        } else if (this == BlockInit.QUARTZ_ORE) {
            return Blocks.QUARTZ_ORE.getExpDrop(state, worldIn, pos, chance);
        }
        return 0;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random rand) {
        if (this == BlockInit.DIAMOND_ORE) {
            return Blocks.DIAMOND_ORE.quantityDropped(state, fortune, rand);
        } else if (this == BlockInit.REDSTONE_ORE) {
            return Blocks.REDSTONE_ORE.quantityDropped(state, fortune, rand);
        } else if (this == BlockInit.QUARTZ_ORE) {
            return Blocks.QUARTZ_ORE.quantityDropped(state, fortune, rand);
        }
        return 1;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand) {
        if (this == BlockInit.DIAMOND_ORE) {
            return Blocks.DIAMOND_ORE.quantityDroppedWithBonus(fortune, rand);
        } else if (this == BlockInit.REDSTONE_ORE) {
            return Blocks.REDSTONE_ORE.quantityDroppedWithBonus(fortune, rand);
        } else if (this == BlockInit.QUARTZ_ORE) {
            return Blocks.QUARTZ_ORE.quantityDroppedWithBonus(fortune, rand);
        }
        return 1;
    }
}