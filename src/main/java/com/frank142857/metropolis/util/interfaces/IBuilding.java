package com.frank142857.metropolis.util.interfaces;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;

public interface IBuilding {

    public String getType();

    public IBlockState getBaseBlock();

    public IBlockState[] getFillerBlocks();

    public int getMinFloor();

    public int getFloorVariation();

    public int getFloorHeight();

    public void setFillerBlock(IBlockState fillerBlock);

    public void setFloorCount(int floorCount);

    public void generate(ChunkPrimer primer);

}
