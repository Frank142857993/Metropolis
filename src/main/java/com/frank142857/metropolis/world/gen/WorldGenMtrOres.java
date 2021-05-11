package com.frank142857.metropolis.world.gen;

import com.frank142857.metropolis.init.BiomeInit;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.DimensionInit;
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
    private WorldGenerator iron, gold, diamond, redstone, ore_argentum, ore_mtr_quartz, dynamite, dirt, stone, block_under_brick, block_slimy_brick;

    public WorldGenMtrOres(){
        iron = new WorldGenMinable(BlockInit.IRON_ORE.getDefaultState(), 9, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        gold = new WorldGenMinable(BlockInit.GOLD_ORE.getDefaultState(), 9, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        diamond = new WorldGenMinable(BlockInit.DIAMOND_ORE.getDefaultState(), 8, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        redstone = new WorldGenMinable(BlockInit.REDSTONE_ORE.getDefaultState(), 8, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        ore_argentum = new WorldGenMinable(BlockInit.ARGENTUM_ORE.getDefaultState(), 9, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        dynamite = new WorldGenMinable(BlockInit.DYNAMITE_ORE.getDefaultState(), 4, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        ore_mtr_quartz = new WorldGenMinable(BlockInit.QUARTZ_ORE.getDefaultState(), 14, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));

        dirt = new WorldGenMinable(BlockInit.HEAVY_DIRT.getDefaultState(), 45, BlockMatcher.forBlock(Blocks.DIRT));
        stone = new WorldGenMinable(Blocks.STONE.getDefaultState(), 33, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        block_under_brick = new WorldGenMinable(BlockInit.UNDER_BRICK.getDefaultState(), 17, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
        block_slimy_brick = new WorldGenMinable(BlockInit.SLIMY_UNDER_BRICK.getDefaultState(), 17, BlockMatcher.forBlock(BlockInit.HARDENED_STONE));
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

            generator.generate(world, random, new BlockPos(x, y, z));
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
        if(world.provider.getDimension() == DimensionInit.metropolis.getId()){
            runGenerator(iron, world, random, chunkX, chunkZ, 6, 0, 60);
            runGenerator(gold, world, random, chunkX, chunkZ, 2, 0, 32);
            runGenerator(diamond, world, random, chunkX, chunkZ, 1, 0, 16);
            runGenerator(redstone, world, random, chunkX, chunkZ, 8, 0, 16);
            runGenerator(ore_argentum, world, random, chunkX, chunkZ, 2, 0, 32);
            runGenerator(dynamite, world, random, chunkX, chunkZ, 1, 0, 8);
            runGenerator(ore_mtr_quartz, world, random, chunkX, chunkZ, 6, 0, 54);
            runGenerator(dirt, world, random, chunkX, chunkZ, 5, 60, 256);
            runGenerator(stone, world, random, chunkX, chunkZ, 10, 32, 64);
            runGenerator(block_under_brick, world, random, chunkX, chunkZ, 5, 28, 60);
            runGenerator(block_slimy_brick, world, random, chunkX, chunkZ, 5, 16, 40);
        }
    }
}
