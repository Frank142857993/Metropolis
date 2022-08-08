package com.frank142857.metropolis.world.city;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import static net.minecraft.block.BlockSlab.HALF;

public class Building {
    private int chunkX;
    private int chunkZ;
    private IBlockState baseBlock;
    private IBlockState fillerBlock;
    private int floor;
    private BuildingType type;
    private ChunkInfoMetropolis info;
    private String[][] slices;

    public Building(int chunkX, int chunkZ, BuildingType type) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.type = type;
        this.baseBlock = type.getBaseBlock();
        this.fillerBlock = type.getFillerBlocks()[(int) (Math.random() * type.getFillerBlocks().length)];
        this.floor = (int)(Math.random() * type.getFloorVariation()) + type.getMinFloor();
        this.slices = new String[type.getFloor().length * floor + 1][16];
        setupSlices(); //test
    }

    public int getHeight() {
        return slices.length;
    }

    public void setupSlices(){
        this.slices[0] = type.getBase();
        int index = 1;
        for(int i1 = 0; i1 < floor; i1++){
            for(int i2 = 0; i2 < type.getFloor().length; i2++){
                this.slices[index] = type.getFloor()[i2];
                index++;
            }
        }
    }

    public IBlockState[][] prepareForGen(int height) {
        char[][] pattern = new char[16][16];
        for(int i = 0; i < slices[height].length; i++) {
            pattern[i] = slices[height][i].toCharArray();
        }
        IBlockState[][] blocks = new IBlockState[pattern.length][pattern[0].length];
        for(int i1 = 0; i1 < pattern.length; i1++){
            for(int i2 = 0; i2 < pattern[0].length; i2++){
                blocks[i1][i2] = getActualBlockState(pattern[i1][i2]);
            }
        }
        return blocks;
    }

    public IBlockState getActualBlockState(char val) {
        switch (val) {
            case 'K':
                return this.baseBlock;
            case 'S':
                return this.fillerBlock;
            case 'G':
                return Blocks.GLASS_PANE.getDefaultState();
            case 'O':
                return Blocks.SEA_LANTERN.getDefaultState();
            case 'H':
                return Blocks.STONE_SLAB.getDefaultState().withProperty(HALF, BlockSlab.EnumBlockHalf.TOP);
            default:
                return Blocks.AIR.getDefaultState();
        }
    }
    public BuildingType getBuildingType() {
        return type;
    }
}

