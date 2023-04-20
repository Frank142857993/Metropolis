package com.frank142857.metropolis.world.city;

import com.frank142857.metropolis.init.ConfigInit;
import com.frank142857.metropolis.util.interfaces.IBuilding;

public class ChunkInfoMetropolis {
    private int chunkX;
    private int chunkZ;
    private int width = ConfigInit.NEIGHBORHOOD_WIDTH;
    private Road roadIn;
    private IBuilding featureIn;

    public ChunkInfoMetropolis(int chunkX, int chunkZ){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        roadIn = new Road(this.chunkX, this.chunkZ);
    }

    public StructureType getStructureType(){
        int x = Math.abs(chunkX);
        int z = Math.abs(chunkZ);
        if(x % (width + 1) == 0 || z % (width + 1) == 0){
            return StructureType.ROAD;
        } else if (x % (width + 1) == 1 || z % (width + 1) == 1 || x % (width + 1) == width || z % (width + 1) == width){
            return StructureType.SMALL_FEATURE;
        } else {
            return StructureType.BUILDING;
        }
    }

    public boolean isRoad(){
        return !(roadIn.getRoadType().equals(RoadType.NONE));
    }

    public Road getRoad() {
        return roadIn;
    }
}
