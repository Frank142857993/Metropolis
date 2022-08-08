package com.frank142857.metropolis.world.city;

public class BuildingPattern {
    private BuildingPatternType type;
    private String[][] slices;

    public BuildingPattern(BuildingPatternType type, String[][] slices){
        this.type = type;
        this.slices = slices;
    }

    public BuildingPatternType getBuildingPatternType(){
        return type;
    }
}
