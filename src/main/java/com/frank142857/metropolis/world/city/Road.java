package com.frank142857.metropolis.world.city;

public class Road {
    private int chunkX;
    private int chunkZ;

    public Road(int chunkX, int chunkZ){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    public RoadType getRoadType(){
        if (chunkX % 5 == 0 && chunkZ % 5 == 0){
            return RoadType.CROSS;
        } else if (chunkX % 5 == 0){
            return RoadType.EW;
        } else if (chunkZ % 5 == 0){
            return RoadType.SN;
        } else return RoadType.NONE;
    }
}