package com.frank142857.metropolis.world.biomes;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.ConfigInit;
import com.frank142857.metropolis.util.interfaces.IBiomeCity;
import com.frank142857.metropolis.world.dimension.metropolis.BiomeDecoratorMetropolis;
import com.frank142857.metropolis.world.gen.feature.WorldGenGrassMTR;
import com.frank142857.metropolis.world.gen.feature.WorldGenMtrFossils;
import com.frank142857.metropolis.world.gen.feature.WorldGenSilverTree;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomeLatticedRealm extends Biome implements IBiomeCity {

    public BiomeLatticedRealm(){

        super(new BiomeProperties("latticed_realm")
                .setBaseHeight(2.0F)
                .setHeightVariation(0.0F)
                .setTemperature(0.205F) //0.21
                .setRainfall(0.4F));

        topBlock = BlockInit.SURFACE_GRASS.getDefaultState();
        fillerBlock = BlockInit.HEAVY_DIRT.getDefaultState();
        this.decorator = new BiomeDecoratorMetropolis();
        this.decorator.treesPerChunk = 8; //from 0
        this.decorator.extraTreeChance = 0; //from 0.03F
        this.decorator.flowersPerChunk = 7;
        this.decorator.mushroomsPerChunk = -100;
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
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new WorldGenGrassMTR();
    }

    @Override
    public int getSkyColorByTemp(float temp){
        if (ConfigInit.USING_FOG_COLOR) return 0xc3c3c3;
        else return super.getSkyColorByTemp(temp);
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);
        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.CUSTOM)){
            if(rand.nextInt(64) == 0){
                (new WorldGenMtrFossils()).generate(worldIn, rand, pos);
            }
        }
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {

        //TODO tree generation test
        //if (rand.nextInt(10) == 0)
        //{
            //return BIG_TREE_FEATURE;
        //}
        //else
        //{
        //    return (WorldGenAbstractTree)(rand.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, JUNGLE_LOG, JUNGLE_LEAF) : new WorldGenTrees(false, 4 + rand.nextInt(7), JUNGLE_LOG, JUNGLE_LEAF, true));
        //}
        return new WorldGenSilverTree(false, true);
    }

    @Override
    public void addDefaultFlowers()
    {
        addFlower(BlockInit.FLUORESCENT_FLOWER_RED.getDefaultState(), 15);
        addFlower(BlockInit.FLUORESCENT_FLOWER_BLUE.getDefaultState(), 15);
    }

    private void addSpawnables(){
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.clear();
        //this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityVillager.class, 1, 1, 1));
    }
}
