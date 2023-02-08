package com.frank142857.metropolis.util.interfaces;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public interface IBuilding {

    public String getType();

    public IBlockState getBaseBlock();

    public IBlockState[] getFillerBlocks();

    public ChunkPos getChunkPos();

    public int getBaseHeight();

    public int getMinFloor();

    public int getFloorVariation();

    public int getFloorHeight();

    public void setFillerBlock(IBlockState fillerBlock);

    public void setFloorCount(int floorCount);

    public void generate(World world, ChunkPrimer primer);

    public void addDetails(World world);

}
