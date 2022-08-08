package com.frank142857.metropolis.world.city;

public class ChunkInfoMetropolis {
    private int chunkX;
    private int chunkZ;

    public ChunkInfoMetropolis(int chunkX, int chunkZ){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    public boolean isRoad(){
        return !(new Road(this.chunkX, this.chunkZ).getRoadType().equals(RoadType.NONE));
    }
}
