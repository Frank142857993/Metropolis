package com.frank142857.metropolis.world.city;

import com.frank142857.metropolis.init.BlockInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import static net.minecraft.block.BlockSlab.HALF;

public enum BuildingType {
    NORMAL(
            3, 4, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),
            new IBlockState[]{
                    BlockInit.POLISHED_FOUNDATION_STONE.getDefaultState(),
                    Blocks.QUARTZ_BLOCK.getDefaultState(),
                    Blocks.BRICK_BLOCK.getDefaultState()
            },
            BuildingPatterns.BASE_1, BuildingPatterns.FLOORS_1
    );

    private int minFloor; //最低层数
    private int floorVariation; //浮动层数
    private IBlockState baseBlock; //地基方块
    private IBlockState[] fillerBlocks; //楼体方块
    private String[] baseSlice;
    private String[][] floorSlices; //方块列表

    BuildingType(int minFloor, int floorVariation, IBlockState baseBlock, IBlockState[] fillerBlocks, String[] baseSlice, String[][] floorSlices) {
        this.minFloor = minFloor;
        this.floorVariation = floorVariation;
        this.baseBlock = baseBlock;
        this.fillerBlocks = fillerBlocks;
        this.baseSlice = baseSlice;
        this.floorSlices = floorSlices;
    }

    public String[] getBase() {
        return this.baseSlice;
    }

    public String[][] getFloor() {
        return this.floorSlices;
    }

    public IBlockState getBaseBlock() {
        return this.baseBlock;
    }

    public IBlockState[] getFillerBlocks() {
        return this.fillerBlocks;
    }

    public int getMinFloor() {
        return this.minFloor;
    }

    public int getFloorVariation() {
        return this.floorVariation;
    }

    public int getFloorHeight() {
        return this.floorSlices.length;
    }
}