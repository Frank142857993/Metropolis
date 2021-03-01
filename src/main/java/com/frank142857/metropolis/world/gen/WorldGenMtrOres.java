package com.frank142857.metropolis.world.gen;

import com.frank142857.metropolis.init.BiomeInit;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.DimensionInit;
import com.frank142857.metropolis.util.handlers.ConfigHandler;
import net.minecraft.block.state.IBlockState;
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
    private WorldGenerator ore_argentum, ore_mtr_quartz, stone, block_slimy_brick;

    public WorldGenMtrOres(){
        ore_argentum = new WorldGenMinable(BlockInit.ORES_MTR.getStateFromMeta(5), 9, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        ore_mtr_quartz = new WorldGenMinable(BlockInit.ORES_MTR.getStateFromMeta(4), 14, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));

        stone = new WorldGenMinable(Blocks.STONE.getDefaultState(), 33, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        block_slimy_brick = new WorldGenMinable(BlockInit.SLIMY_BRICK.getDefaultState(), 33, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
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
        if(world.provider.getDimension() == DimensionInit.metropolis.getId()){
            runGenerator(ore_argentum, world, random, chunkX, chunkZ, 2, 0, 32);
            runGenerator(ore_mtr_quartz, world, random, chunkX, chunkZ, 6, 0, 54);
            runGenerator(stone, world, random, chunkX, chunkZ, 10, 32, 64);
            runGenerator(block_slimy_brick, world, random, chunkX, chunkZ, 10, 16, 60);
        }
    }
}
