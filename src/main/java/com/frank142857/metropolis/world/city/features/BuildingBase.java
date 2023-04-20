package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.util.interfaces.IBuilding;
import com.frank142857.metropolis.util.interfaces.IHouse;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.fillMargin;

public class BuildingBase implements IHouse { //TODO 待改
    private int chunkX;
    private int chunkZ;
    private int baseHeight;

    public BuildingBase(int chunkX, int chunkZ, int baseHeight) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
    }

    @Override
    public String getType() {
        return "base";
    }

    @Override
    public ChunkPos getChunkPos() {
        return new ChunkPos(chunkX, chunkZ);
    }

    @Override
    public int getBaseHeight() {
        return baseHeight;
    }

    @Override
    public void generate(World world) {
        int x = chunkX * 16 + 1;
        int z = chunkZ * 16 + 1;
        int y = this.baseHeight + 1;

        for (int i = 0; i < 4; i++) {
            for (int x1 = 0; x1 < 14; x1++) {
                for (int z1 = 0; z1 < 14; z1++) {
                    if (x1 == 0 || z1 == 0 || x1 == 13 || z1 == 13)
                        world.setBlockState(new BlockPos(x + x1, y, z + z1), Blocks.STONE_SLAB.getStateFromMeta(7));
                }
            } //TODO margin filler

            for (int x1 = 1; x1 < 13; x1++) {
                for (int z1 = 1; z1 < 13; z1++) {
                    if (x1 == 1 || z1 == 1 || x1 == 12 || z1 == 12)
                        world.setBlockState(new BlockPos(x + x1, y, z + z1), Blocks.QUARTZ_BLOCK.getDefaultState());
                }
            }

            for (int x2 = 2; x2 < 12; x2++) {
                for (int z2 = 2; z2 < 12; z2++) {
                    world.setBlockState(new BlockPos(x + x2, y, z + z2), BlockInit.POLISHED_FOUNDATION_STONE.getDefaultState());
                }
            }
            y += 6;
        }
    }

}
