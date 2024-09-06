package com.frank142857.metropolis.world.biomes;

import com.frank142857.metropolis.init.*;
import com.frank142857.metropolis.util.interfaces.IBiomeCity;
import com.frank142857.metropolis.world.dimension.metropolis.BiomeDecoratorMetropolis;
import com.frank142857.metropolis.world.gen.feature.*;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class BiomeMetropolis extends Biome implements IBiomeCity {

    //TODO tree generation test. Replace it by "silver tree"
    private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    /** The block state for the Oak leaf */
    private static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));


    public BiomeMetropolis(){

        super(new BiomeProperties("overgrown_garden")
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
    } //e6e6e6

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
    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) { //TODO silver flower?
        int i = rand.nextInt(10);

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
        //this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityVillager.class, 1, 1, 1));
    }
}
