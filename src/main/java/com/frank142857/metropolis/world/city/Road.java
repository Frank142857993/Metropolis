package com.frank142857.metropolis.world.city;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.ConfigInit;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.*;

public class Road {
    private int chunkX;
    private int chunkZ;
    private int baseHeight;
    private boolean containRiver; //TODO river network

    private RoadType roadType;

    public Road(int chunkX, int chunkZ){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = 0;
        this.init();
    }

    public Road init(){

        int width = ConfigInit.NEIGHBORHOOD_WIDTH;
        int river = ConfigInit.RIVER_DISTRIBUTION;

        if (chunkX % (width + 1) == 0 && chunkZ % (width + 1) == 0){
            this.roadType = RoadType.CROSS;
        } else if (chunkX % (width + 1) == 0){
            this.roadType = RoadType.SN;
        } else if (chunkZ % (width + 1) == 0){
            this.roadType = RoadType.EW;
        } else {
            this.roadType = RoadType.NONE;
        }

        if (chunkX % ((width + 1) * river) == 0 || chunkZ % ((width + 1) * river) == 0){
            this.containRiver = true;
        } else {
            this.containRiver = false;
        }

        return this;
    }

    public void generate(ChunkPrimer primer){
        int y = baseHeight;
        int width = ConfigInit.NEIGHBORHOOD_WIDTH;
        int river = ConfigInit.RIVER_DISTRIBUTION;
        if(this.containRiver()){ //TODO river network
            if(this.getRoadType().equals(RoadType.CROSS)){
                if(chunkZ % ((width + 1) * river) != 0) {
                    fill(primer, 4, y - 6, 0, 11, y - 3, 15, Blocks.WATER.getDefaultState());
                    fill(primer, 4, y - 2, 0, 11, y, 15, Blocks.AIR.getDefaultState());
                    fill(primer, 0, y, 4, 15, y, 11, BlockInit.STONE_PAVING.getDefaultState());
                } else if (chunkX % ((width + 1) * river) != 0){
                    fill(primer, 0, y - 6, 4, 15, y - 3, 11, Blocks.WATER.getDefaultState());
                    fill(primer, 0, y - 2, 4, 15, y, 11, Blocks.AIR.getDefaultState());
                    fill(primer, 4, y, 0, 11, y, 15, BlockInit.STONE_PAVING.getDefaultState());
                }
                else {
                    fill(primer, 0, y - 6, 4, 3, y - 3, 11, Blocks.WATER.getDefaultState());//TODO 6 -> river depth?
                    fill(primer, 4, y - 6, 0, 11, y - 3, 15, Blocks.WATER.getDefaultState());
                    fill(primer, 12, y - 6, 4, 15, y - 3, 11, Blocks.WATER.getDefaultState());

                    fill(primer, 0, y - 2, 4, 3, y, 11, Blocks.AIR.getDefaultState());
                    fill(primer, 4, y - 2, 0, 11, y, 15, Blocks.AIR.getDefaultState());
                    fill(primer, 12, y - 2, 4, 15, y, 11, Blocks.AIR.getDefaultState());
                }

            } else if (this.getRoadType().equals(RoadType.SN)){
                fill(primer, 4, y - 6, 0, 11, y - 3, 15, Blocks.WATER.getDefaultState());
                fill(primer, 4, y - 2, 0, 11, y, 15, Blocks.AIR.getDefaultState());
            } else if (this.getRoadType().equals(RoadType.EW)){
                fill(primer, 0, y - 6, 4, 15, y - 3, 11, Blocks.WATER.getDefaultState());
                fill(primer, 0, y - 2, 4, 15, y, 11, Blocks.AIR.getDefaultState());
            }
        } else {
            if(this.getRoadType().equals(RoadType.CROSS)){
                fill(primer, 0, y, 4, 3, y, 11, BlockInit.STONE_PAVING.getDefaultState());
                fill(primer, 4, y, 0, 11, y, 15, BlockInit.STONE_PAVING.getDefaultState());
                fill(primer, 12, y, 4, 15, y, 11, BlockInit.STONE_PAVING.getDefaultState());
            } else if (this.getRoadType().equals(RoadType.SN)){
                fill(primer, 4, y, 0, 11, y, 15, BlockInit.STONE_PAVING.getDefaultState());
            } else if (this.getRoadType().equals(RoadType.EW)){
                fill(primer, 0, y, 4, 15, y, 11, BlockInit.STONE_PAVING.getDefaultState());
            }
        }
    }

    public void setBaseHeight(int h) {
        this.baseHeight = h;
    }

    public RoadType getRoadType(){
        return this.roadType;
    }

    public boolean containRiver(){
        return this.containRiver;
    }
}