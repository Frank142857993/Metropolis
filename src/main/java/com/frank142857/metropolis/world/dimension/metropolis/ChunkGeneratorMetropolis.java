package com.frank142857.metropolis.world.dimension.metropolis;

import java.util.List;
import java.util.Random;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.util.interfaces.IBiomeCity;
import com.frank142857.metropolis.world.city.*;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkGeneratorMetropolis implements IChunkGenerator {
    private final Random rand;
    private final World world;
    private final WorldType terrainType;
    private Biome[] biomesForGeneration;

    protected static final IBlockState STONE = BlockInit.FOUNDATION_STONE.getDefaultState();

    public ChunkGeneratorMetropolis(World worldIn, long seed) {
        this.world = worldIn;
        this.world.setSeaLevel(95);
        this.terrainType = worldIn.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ) {
        this.rand.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(chunkX, chunkZ, chunkprimer);
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
        this.replaceBiomeBlocks(chunkX, chunkZ, chunkprimer, this.biomesForGeneration);
        Chunk chunk = new Chunk(this.world, chunkprimer, chunkX, chunkZ);
        byte[] abyte = chunk.getBiomeArray();
        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    public void generateQuadraticRoadNetwork(int chunkX, int chunkZ, ChunkPrimer primer){
        Road r = new Road(chunkX, chunkZ);
        int y = this.world.getSeaLevel();
        if(r.getRoadType().equals(RoadType.CROSS)){
            for (int x = 0; x < 16; ++x)
            {
                if(4 <= x && x < 12) continue;
                for (int z = 4; z < 12; ++z)
                {
                    primer.setBlockState(x, y, z, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                }
            }
            for(int x = 4; x < 12; ++x){
                for (int z = 0; z < 16; ++z)
                {
                    primer.setBlockState(x, y, z, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                }
            }
        } else if (r.getRoadType().equals(RoadType.EW)){
            for(int x = 4; x < 12; ++x){
                for (int z = 0; z < 16; ++z)
                {
                    primer.setBlockState(x, y, z, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                }
            }
        } else if (r.getRoadType().equals(RoadType.SN)){
            for(int z = 4; z < 12; ++z){
                for (int x = 0; x < 16; ++x)
                {
                    primer.setBlockState(x, y, z, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                }
            }
        }
    }

    public void setupArchitectures(int chunkX, int chunkZ, ChunkPrimer primer){
        Building b = new Building(chunkX, chunkZ, BuildingType.NORMAL);
        for(int i = 0; i < b.getHeight(); i++){
            IBlockState[][] buildingBlocks = b.prepareForGen(i);
            int y = this.world.getSeaLevel();
            for(int x = 0; x < 16; ++x){
                for (int z = 0; z < 16; ++z)
                {
                    primer.setBlockState(x, y + i, z, buildingBlocks[x][z]);
                }
            }
        }
    }

    @Override
    public void populate(int chunkX, int chunkZ) {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        BlockPos blockPos = new BlockPos(x, 0, z);
        Biome biome = this.world.getBiome(blockPos.add(16, 0, 16));
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) chunkX * k + (long) chunkZ * l ^ this.world.getSeed());
        ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, chunkX, chunkZ, false);
        biome.decorate(this.world, this.rand, new BlockPos(x, 0, z));
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, false,
                net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(this.world, biome, x + 8, z + 8, 16, 16, this.rand);
        }
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, chunkX, chunkZ, false);
        BlockFalling.fallInstantly = false;
    }

    private void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer){
        generatePlainBase(chunkX, chunkZ, primer);
    }

    private void replaceBiomeBlocks(int chunkX, int chunkZ, ChunkPrimer primer, Biome[] biomesIn) {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer, this.world)) {
            return;
        }
        if(biomesIn[120] instanceof IBiomeCity){
            if(new ChunkInfoMetropolis(chunkX, chunkZ).isRoad()) {
                generateQuadraticRoadNetwork(chunkX, chunkZ, primer);
            }
            else {
                setupArchitectures(chunkX, chunkZ, primer);
            }
        }
    }

    private void generatePlainBase(int chunkX, int chunkZ, ChunkPrimer primer){
        for (int i = 33; i <= this.world.getSeaLevel(); i++) {
            if (i == 33) {
                for (int j = 0; j < 16; ++j) {
                    for (int k = 0; k < 16; ++k) {
                        primer.setBlockState(j, i, k, BlockInit.SURFACE_GRASS.getDefaultState());
                    }
                }
            } else if (33 < i && i < 37) {
                for (int j = 0; j < 16; ++j) {
                    for (int k = 0; k < 16; ++k) {
                        primer.setBlockState(j, i, k, BlockInit.HEAVY_DIRT.getDefaultState());
                    }
                }
            } else if (this.world.getSeaLevel() - 4 < i && i <= this.world.getSeaLevel() - 1) {
                for (int j = 0; j < 16; ++j) {
                    for (int k = 0; k < 16; ++k) {
                        primer.setBlockState(j, i, k, BlockInit.HEAVY_DIRT.getDefaultState());
                    }
                }
            } else if (i == this.world.getSeaLevel()) {
                for (int j = 0; j < 16; ++j) {
                    for (int k = 0; k < 16; ++k) {
                        primer.setBlockState(j, i, k, BlockInit.SURFACE_GRASS.getDefaultState());
                    }
                }
            } else {
                for (int j = 0; j < 16; ++j) {
                    for (int k = 0; k < 16; ++k) {
                        primer.setBlockState(j, i, k, STONE);
                    }
                }
            }
        }
    }

    private void generateBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal, Biome biome) {
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.getBiome(pos);

        if(creatureType == EnumCreatureType.MONSTER){
            biome.getSpawnableList(creatureType).remove(creatureType);
        }

        return biome.getSpawnableList(creatureType);
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false; //不在此处生成结构
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }
}
