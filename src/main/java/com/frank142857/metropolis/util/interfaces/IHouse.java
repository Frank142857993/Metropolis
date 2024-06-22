package com.frank142857.metropolis.util.interfaces;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import java.util.Random;

public interface IHouse {
    public String getType();

    public ChunkPos getChunkPos();

    public int getBaseHeight();

    public void generate(World world);

    //public void generate(World world, int chunkX, int chunkZ, int baseHeight, Random rand);
}
