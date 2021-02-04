package com.frank142857.metropolis.world.gen;

import com.frank142857.metropolis.init.BiomeInit;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.DimensionInit;
import com.frank142857.metropolis.util.handlers.ConfigHandler;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenMtrOres implements IWorldGenerator {
    private WorldGenerator ore_argentum, ore_mtr_quartz;

    public WorldGenMtrOres(){
        ore_argentum = new WorldGenMinable(BlockInit.ORE_ARGENTUM.getDefaultState(), 9, BlockMatcher.forBlock(Blocks.STONE));
        ore_mtr_quartz = new WorldGenMinable(BlockInit.ORE_MTR_QUARTZ.getDefaultState(), 14, BlockMatcher.forBlock(Blocks.STONE));
    }

    private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight){
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256){
            throw new IllegalArgumentException("Ore generation Metropolis: out of bounds");
        }

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chance; i++){
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);

            int y = minHeight + random.nextInt(heightDiff);

            if(world.getBiome(new BlockPos(x, y, z)).equals(BiomeInit.CITY_OF_PRESENT)){
                //TODO add more MTR biomes
                generator.generate(world, random, new BlockPos(x, y, z));
            }
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
        switch(world.provider.getDimension()){
            case 0:
                if(ConfigHandler.DO_MTR_ORES_GENERATE){
                    runGenerator(ore_argentum, world, random, chunkX, chunkZ, 2, 0, 32);
                    runGenerator(ore_mtr_quartz, world, random, chunkX, chunkZ, 6, 0, 54);
                }
                break;
        }
        if(world.provider.getDimension() == DimensionInit.metropolis.getId()){
            runGenerator(ore_argentum, world, random, chunkX, chunkZ, 2, 0, 32);
            runGenerator(ore_mtr_quartz, world, random, chunkX, chunkZ, 6, 0, 54);
        }
    }
}
