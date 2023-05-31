package com.frank142857.metropolis.world.biomes;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.ConfigInit;
import com.frank142857.metropolis.util.interfaces.IBiomeCity;
import com.frank142857.metropolis.world.dimension.metropolis.BiomeDecoratorMetropolis;
import com.frank142857.metropolis.world.gen.feature.WorldGenMtrFossils;
import net.minecraft.block.BlockFlower;
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
                .setTemperature(0.205F) //0.21
                .setRainfall(0.4F));

        topBlock = BlockInit.SURFACE_GRASS.getDefaultState();
        fillerBlock = BlockInit.HEAVY_DIRT.getDefaultState();
        this.decorator = new BiomeDecoratorMetropolis();
        this.decorator.treesPerChunk = 1; //from 0
        this.decorator.extraTreeChance = 0; //from 0.03F
        this.decorator.flowersPerChunk = 4;
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

    @Override
    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) { //TODO silver flower?
        int i = rand.nextInt(10);
        /*
        switch (i){
            case 0:
                return BlockFlower.EnumFlowerType.DANDELION;
            case 1:
                return BlockFlower.EnumFlowerType.POPPY;
            case 2:
                return BlockFlower.EnumFlowerType.ALLIUM;
            case 3:
                return BlockFlower.EnumFlowerType.BLUE_ORCHID;
            case 4:
                return BlockFlower.EnumFlowerType.HOUSTONIA;
            case 5:
                return BlockFlower.EnumFlowerType.RED_TULIP;
            case 6:
                return BlockFlower.EnumFlowerType.ORANGE_TULIP;
            case 7:
                return BlockFlower.EnumFlowerType.WHITE_TULIP;
            case 8:
                return BlockFlower.EnumFlowerType.PINK_TULIP;
            default:
                return BlockFlower.EnumFlowerType.OXEYE_DAISY;
        }
        */
        return super.pickRandomFlower(rand, pos);
    }

    @Override
    public void addDefaultFlowers()
    {
        BlockFlower red = net.minecraft.init.Blocks.RED_FLOWER;
        BlockFlower yel = net.minecraft.init.Blocks.YELLOW_FLOWER;
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ORANGE_TULIP), 15);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.RED_TULIP), 15);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.PINK_TULIP), 15);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.WHITE_TULIP), 15);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.POPPY), 20);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.HOUSTONIA), 20);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.OXEYE_DAISY), 20);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ALLIUM), 20);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.BLUE_ORCHID), 20);
        addFlower(yel.getDefaultState().withProperty(yel.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 30);
    }

    private void addSpawnables(){
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityVillager.class, 1, 1, 1));
    }
}
