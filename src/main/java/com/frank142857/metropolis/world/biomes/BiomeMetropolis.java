package com.frank142857.metropolis.world.biomes;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.util.handlers.ConfigHandler;
import com.frank142857.metropolis.util.interfaces.IBiomeCity;
import com.frank142857.metropolis.world.gen.feature.WorldGenDevBuildings;
import net.minecraft.entity.passive.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomeMetropolis extends Biome implements IBiomeCity {
    public BiomeMetropolis(){

        super(new BiomeProperties("Metropolis")
                .setBaseHeight(2.0F)
                .setHeightVariation(0.0F)
                .setTemperature(0.8F)
                .setRainfall(0.4F));

        topBlock = BlockInit.SURFACE_GRASS.getDefaultState();
        fillerBlock = BlockInit.HEAVY_DIRT.getDefaultState();
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.03F;
        this.addSpawnables();
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos){
        return 0xe6e6e6;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos){
        return 0x7b8770;
    }

    @Override
    public int getSkyColorByTemp(float temp){
        if (ConfigHandler.USING_FOG_COLOR) return 0xf3f3f3;
        else return super.getSkyColorByTemp(temp);
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);
        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.CUSTOM)){
            if(rand.nextInt(10) == 0){
                int i = rand.nextInt(16) + 8;
                int j = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
                (new WorldGenDevBuildings()).generate(worldIn, rand, blockpos);
            }
        }
    }

    private void addSpawnables(){
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityVillager.class, 1, 1, 1));
    }
}
