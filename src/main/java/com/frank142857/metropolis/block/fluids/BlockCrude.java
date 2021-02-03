package com.frank142857.metropolis.block.fluids;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.FluidInit;
import com.frank142857.metropolis.init.ItemInit;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockCrude extends BlockFluid {
    public BlockCrude() {
        super("crude", FluidInit.CRUDE, Material.LAVA, MapColor.BLACK, 10);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        BlockInit.REGISTER_BLOCKS.add(this);
    }
}
