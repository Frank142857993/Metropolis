package com.frank142857.metropolis.world.city;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.ConfigInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.*;

public class Road {
    private int chunkX;
    private int chunkZ;
    private int baseHeight;

    private int width = ConfigInit.NEIGHBORHOOD_WIDTH;
    private int river = ConfigInit.RIVER_DISTRIBUTION * 2;

    private boolean isRiver; //TODO river network

    private RoadType roadType;

    public Road(int chunkX, int chunkZ){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = 0;
        this.init();
    }

    public Road init(){

        if (chunkX % ((width + 1) * river) == 0 || chunkZ % ((width + 1) * river) == 0){
            this.isRiver = true;
        } else {
            this.isRiver = false;
        }
        
        if (chunkX % (width + 1) == 0 && chunkZ % (width + 1) == 0){
            this.roadType = RoadType.CROSS;
        } else if (chunkX % (width + 1) == 0){
            this.roadType = RoadType.SN;
        } else if (chunkZ % (width + 1) == 0){
            this.roadType = RoadType.EW;
        } else {
            this.roadType = RoadType.NONE;
        }

        if(this.isRiver){
            if (this.roadType.equals(RoadType.SN)){
                this.roadType = RoadType.RIVER_SN;
            } else if (this.roadType.equals(RoadType.EW)){
                this.roadType = RoadType.RIVER_EW;
            } else {
                if (chunkZ % ((width + 1) * river) != 0) {
                    this.roadType = RoadType.BRIDGE_EW;
                } else if (chunkX % ((width + 1) * river) != 0){
                    this.roadType = RoadType.BRIDGE_SN;
                } else {
                    this.roadType = RoadType.WATER;
                }
            }
        }

        return this;
    }

    public void generate(ChunkPrimer primer){
        int y = baseHeight;
        switch (this.roadType){
            case WATER:
                fill(primer, 4, y - 6, 0, 4, y - 1, 15, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 11, y - 6, 0, 11, y - 1, 15, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 4, y, 0, 4, y, 15, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                fill(primer, 11, y, 0, 11, y, 15, Blocks.DOUBLE_STONE_SLAB.getDefaultState());

                fill(primer, 0, y - 6, 4, 15, y - 1, 4, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 0, y - 6, 11, 15, y - 1, 11, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 0, y, 4, 15, y, 4, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                fill(primer, 0, y, 11, 15, y, 11, Blocks.DOUBLE_STONE_SLAB.getDefaultState());

                fill(primer, 0, y - 6, 5, 4, y - 3, 10, Blocks.WATER.getDefaultState());//TODO 6 -> river depth?
                fill(primer, 5, y - 6, 0, 10, y - 3, 15, Blocks.WATER.getDefaultState());
                fill(primer, 11, y - 6, 5, 15, y - 3, 10, Blocks.WATER.getDefaultState());

                fill(primer, 0, y - 2, 5, 4, y, 10, Blocks.AIR.getDefaultState());
                fill(primer, 5, y - 2, 0, 10, y, 15, Blocks.AIR.getDefaultState());
                fill(primer, 11, y - 2, 5, 15, y, 10, Blocks.AIR.getDefaultState());
                break;
            case BRIDGE_EW:
                fill(primer, 5, y - 6, 0, 10, y - 3, 15, Blocks.WATER.getDefaultState());
                fill(primer, 5, y - 2, 0, 10, y, 15, Blocks.AIR.getDefaultState());

                fill(primer, 4, y - 6, 0, 4, y - 1, 15, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 11, y - 6, 0, 11, y - 1, 15, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 4, y, 0, 4, y, 15, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                fill(primer, 11, y, 0, 11, y, 15, Blocks.DOUBLE_STONE_SLAB.getDefaultState());

                fill(primer, 0, y, 4, 15, y, 11, BlockInit.STONE_PAVING.getDefaultState());
                break;
            case BRIDGE_SN:
                fill(primer, 0, y - 6, 5, 15, y - 3, 10, Blocks.WATER.getDefaultState());
                fill(primer, 0, y - 2, 5, 15, y, 10, Blocks.AIR.getDefaultState());

                fill(primer, 0, y - 6, 4, 15, y - 1, 4, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 0, y - 6, 11, 15, y - 1, 11, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 0, y, 4, 15, y, 4, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                fill(primer, 0, y, 11, 15, y, 11, Blocks.DOUBLE_STONE_SLAB.getDefaultState());

                fill(primer, 4, y, 0, 11, y, 15, BlockInit.STONE_PAVING.getDefaultState());
                break;
            case RIVER_SN:
                fill(primer, 5, y - 6, 0, 10, y - 3, 15, Blocks.WATER.getDefaultState());
                fill(primer, 5, y - 2, 0, 10, y, 15, Blocks.AIR.getDefaultState());

                fill(primer, 4, y - 6, 0, 4, y - 1, 15, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 11, y - 6, 0, 11, y - 1, 15, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 4, y, 0, 4, y, 15, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                fill(primer, 11, y, 0, 11, y, 15, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                break;
            case RIVER_EW:
                fill(primer, 0, y - 6, 5, 15, y - 3, 10, Blocks.WATER.getDefaultState());
                fill(primer, 0, y - 2, 5, 15, y, 10, Blocks.AIR.getDefaultState());

                fill(primer, 0, y - 6, 4, 15, y - 1, 4, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 0, y - 6, 11, 15, y - 1, 11, BlockInit.BLACK_BRICK.getDefaultState());
                fill(primer, 0, y, 4, 15, y, 4, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                fill(primer, 0, y, 11, 15, y, 11, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                break;
            case SN:
                fill(primer, 4, y, 0, 11, y, 15, BlockInit.STONE_PAVING.getDefaultState());

                //STREET LAMP
                if(Math.floorMod(chunkZ, (width + 1)) % 2 == 0) {
                    fill(primer, 2, y, 7, 2, y + 9, 7, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                    primer.setBlockState(1, y + 9, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(2, y + 10, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(3, y + 9, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                    primer.setBlockState(4, y + 10, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(5, y + 10, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(5, y + 9, 7, BlockInit.CEILING_LIGHT.getDefaultState());
                } else {
                    fill(primer, 13, y, 7, 13, y + 9, 7, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                    primer.setBlockState(14, y + 9, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(13, y + 10, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(12, y + 9, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                    primer.setBlockState(11, y + 10, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(10, y + 10, 7, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(10, y + 9, 7, BlockInit.CEILING_LIGHT.getDefaultState());
                }
                break;
            case EW:
                fill(primer, 0, y, 4, 15, y, 11, BlockInit.STONE_PAVING.getDefaultState());

                //STREET LAMP
                if(Math.floorMod(chunkX, (width + 1)) % 2 == 1) {
                    fill(primer, 7, y, 2, 7, y + 9, 2, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                    primer.setBlockState(7, y + 9, 1, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(7, y + 10, 2, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(7, y + 9, 3, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                    primer.setBlockState(7, y + 10, 4, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(7, y + 10, 5, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(7, y + 9, 5, BlockInit.CEILING_LIGHT.getDefaultState());
                } else {
                    fill(primer, 7, y, 13, 7, y + 9, 13, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                    primer.setBlockState(7, y + 9, 14, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(7, y + 10, 13, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(7, y + 9, 12, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                    primer.setBlockState(7, y + 10, 11, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(7, y + 10, 10, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                    primer.setBlockState(7, y + 9, 10, BlockInit.CEILING_LIGHT.getDefaultState());
                }
                break;
            case CROSS:
                fill(primer, 0, y, 4, 3, y, 11, BlockInit.STONE_PAVING.getDefaultState());
                fill(primer, 4, y, 0, 11, y, 15, BlockInit.STONE_PAVING.getDefaultState());
                fill(primer, 12, y, 4, 15, y, 11, BlockInit.STONE_PAVING.getDefaultState());
                break;
        }
    }

    public void setBaseHeight(int h) {
        this.baseHeight = h;
    }

    public RoadType getRoadType(){
        return this.roadType;
    }

    public boolean isRiver(){
        return this.isRiver;
    }
}