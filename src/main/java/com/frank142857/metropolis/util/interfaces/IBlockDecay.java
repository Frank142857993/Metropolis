package com.frank142857.metropolis.util.interfaces;

import net.minecraft.block.state.IBlockState;

public interface IBlockDecay {

    public IBlockState getAfter(int meta);

    public int getChance(int meta);
}
