package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.util.interfaces.IBuilding;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.fillMargin;

public class BuildingBase implements IBuilding {
    private int chunkX;
    private int chunkZ;
    private int baseHeight;

    public BuildingBase(int chunkX, int chunkZ, int baseHeight, EnumFacing facing){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
    }

    @Override
    public String getType() {
        return "base";
    }

    @Override
    public IBlockState getBaseBlock() {
        return Blocks.DOUBLE_STONE_SLAB.getDefaultState();
    }

    @Override
    public IBlockState[] getFillerBlocks() {
        return new IBlockState[0];
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
        return 0;
    }

    @Override
    public int getFloorVariation() {
        return 0;
    }

    @Override
    public int getFloorHeight() {
        return 0;
    }

    @Override
    public void setFillerBlock(IBlockState fillerBlock) {

    }

    @Override
    public void setFloorCount(int floorCount) {

    }

    @Override
    public void generate(World worldIn, ChunkPrimer primer) {
        int y = baseHeight;
        //fillMargin(primer, 0, 0, 15, 15, y, Blocks.DOUBLE_STONE_SLAB.getDefaultState()); //TODO Nothing generated
    }

    @Override
    public void addDetails(World world) {

    }
}
