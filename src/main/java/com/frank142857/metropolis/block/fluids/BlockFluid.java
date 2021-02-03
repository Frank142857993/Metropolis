package com.frank142857.metropolis.block.fluids;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluid extends BlockFluidClassic {
    public BlockFluid(String name, Fluid fluid, Material material, MapColor mapColor, int tickrate) {
        super(fluid, material, mapColor);
        super.setTickRate(tickrate);
        setUnlocalizedName(name);
        setRegistryName(name);

        //BlockRegistryHandler.BLOCKS.add(this);
        //ItemRegistryHandler.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }
}
