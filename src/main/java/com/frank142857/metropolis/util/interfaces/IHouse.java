package com.frank142857.metropolis.util.interfaces;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public interface IHouse {
    public String getType();

    public ChunkPos getChunkPos();

    public int getBaseHeight();

    public void generate(World world);
}
