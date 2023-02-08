package com.frank142857.metropolis.world.city;

import com.frank142857.metropolis.util.interfaces.IBuilding;

public class ChunkInfoMetropolis {
    private int chunkX;
    private int chunkZ;
    private Road roadIn;
    private IBuilding featureIn;

    public ChunkInfoMetropolis(int chunkX, int chunkZ){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        roadIn = new Road(this.chunkX, this.chunkZ);
    }

    public boolean isRoad(){
        return !(roadIn.getRoadType().equals(RoadType.NONE));
    }

    public Road getRoad() {
        return roadIn;
    }
}
