package com.frank142857.metropolis.world.city;

import com.frank142857.metropolis.init.BlockInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import static net.minecraft.block.BlockSlab.HALF;

public enum BuildingType {
    NORMAL(
            3, 4, 6, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),
            new IBlockState[]{
                    BlockInit.POLISHED_FOUNDATION_STONE.getDefaultState(),
                    Blocks.QUARTZ_BLOCK.getDefaultState(),
                    Blocks.BRICK_BLOCK.getDefaultState()
            }
    ),
    TOWER(
            8, 5, 6, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),
            new IBlockState[]{
                    BlockInit.POLISHED_FOUNDATION_STONE.getDefaultState(),
                    //BlockInit.UNDER_BRICK.getDefaultState(), //Name change
                    Blocks.QUARTZ_BLOCK.getDefaultState()
            }
    );

    private int minFloor; //最低层数
    private int floorVariation; //浮动层数
    private int floorHeight; //单层高度
    private IBlockState baseBlock; //地基方块
    private IBlockState[] fillerBlocks; //楼体方块

    BuildingType(int minFloor, int floorVariation, int floorHeight, IBlockState baseBlock, IBlockState[] fillerBlocks) {
        this.minFloor = minFloor;
        this.floorVariation = floorVariation;
        this.floorHeight = floorHeight;
        this.baseBlock = baseBlock;
        this.fillerBlocks = fillerBlocks;
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
        return this.floorHeight;
    }
}