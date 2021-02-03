package com.frank142857.metropolis.world.biomes;

import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class BiomeCityOfPresent extends Biome {
    public BiomeCityOfPresent(){

        super(new BiomeProperties("City of Present")
                .setBaseHeight(0.125F)
                .setHeightVariation(0.0005F)
                .setTemperature(0.8F)
                .setRainfall(0.4F)
                .setSnowEnabled()
                .setWaterColor(0x669966));

        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.03F;
        this.addSpawnables();
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos){
        return 0xcccccc;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos){
        return 0x7b8770;
    }

    @Override
    public int getSkyColorByTemp(float temp){
        return 0xdcdcdc;
    }

    private void addSpawnables(){
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityVillager.class, 1, 1, 1));
    }
}
