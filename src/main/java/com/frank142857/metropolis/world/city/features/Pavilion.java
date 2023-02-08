package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.util.interfaces.*;
import com.frank142857.metropolis.world.city.ChunkGenFactory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

public class Pavilion implements IBuilding {
    private int chunkX;
    private int chunkZ;
    private int baseHeight;
    private int floorCount;

    private IBlockState baseBlock = Blocks.QUARTZ_BLOCK.getDefaultState();
    private IBlockState fillerBlock = Blocks.QUARTZ_BLOCK.getDefaultState();

    public Pavilion(int chunkX, int chunkZ, int baseHeight, Random rand){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
    }

    @Override
    public String getType() {
        return "pavilion";
    }

    @Override
    public IBlockState getBaseBlock() {
        return this.baseBlock;
    }

    @Override
    public IBlockState[] getFillerBlocks() {
        return new IBlockState[]{
                this.fillerBlock
        };
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
    public int getMinFloor() {
        return 1;
    }

    @Override
    public int getFloorVariation() {
        return 0;
    }

    @Override
    public int getFloorHeight() {
        return 6;
    }

    @Override
    public void setFillerBlock(IBlockState fillerBlock) {
        this.fillerBlock = fillerBlock;
    }

    @Override
    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    @Override
    public void generate(World worldIn, ChunkPrimer primer) {
        int y = this.baseHeight;
        ChunkGenFactory.fillMargin(primer, 0, 0, 15, 15, y, this.baseBlock);
        ChunkGenFactory.fillLayer(primer, 2, 2, 13, 13, y, this.fillerBlock);
        ChunkGenFactory.fillPillar(primer, 3, y + 1, 3, 4, this.fillerBlock);
        ChunkGenFactory.fillPillar(primer, 3, y + 1, 12, 4, this.fillerBlock);
        ChunkGenFactory.fillPillar(primer, 12, y + 1, 3, 4, this.fillerBlock);
        ChunkGenFactory.fillPillar(primer, 12, y + 1, 12, 4, this.fillerBlock);
        ChunkGenFactory.fillMargin(primer, 2, 2, 13, 13, y + 5, this.fillerBlock);
        ChunkGenFactory.fillMargin(primer, 3, 3, 12, 12, y + 5, this.fillerBlock);
        ChunkGenFactory.fillMargin(primer, 3, 3, 12, 12, y + 6, this.fillerBlock);
    }

    @Override
    public void addDetails(World world) { //debug
        /*
        int x = chunkX * 16;
        int z = chunkZ * 16;
        int y = this.baseHeight + 6;
        for(int x0 = x + 2; x0 < x + 14; x0++){
            for(int z0 = z + 2; z0 < z + 14; z0++){
                world.setBlockState(new BlockPos(x0, y, z0), Blocks.GLASS.getDefaultState());
            }
        }*/
    }
}
