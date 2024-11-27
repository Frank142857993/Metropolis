package com.frank142857.metropolis.world.dimension.metropolis;

import com.frank142857.metropolis.block.base.BlockFlowerMTR;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.world.gen.WorldGenFlowerMTR;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenPumpkin;

import java.util.Random;

public class BiomeDecoratorMetropolis extends BiomeDecorator {
    public WorldGenFlowerMTR flowerGen = new WorldGenFlowerMTR(BlockInit.FLUORESCENT_FLOWER_RED);

    protected void genDecorations(Biome biomeIn, World worldIn, Random random)
    {
        net.minecraft.util.math.ChunkPos forgeChunkPos = new net.minecraft.util.math.ChunkPos(chunkPos); // actual ChunkPos instead of BlockPos, used for events
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre(worldIn, random, forgeChunkPos));
        this.generateOres(worldIn, random);

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND))
            for (int i = 0; i < this.sandPatchesPerChunk; ++i)
            {
                int j = random.nextInt(16) + 8;
                int k = random.nextInt(16) + 8;
                this.sandGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(j, 0, k)));
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY))
            for (int i1 = 0; i1 < this.clayPerChunk; ++i1)
            {
                int l1 = random.nextInt(16) + 8;
                int i6 = random.nextInt(16) + 8;
                this.clayGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(l1, 0, i6)));
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND_PASS2))
            for (int j1 = 0; j1 < this.gravelPatchesPerChunk; ++j1)
            {
                int i2 = random.nextInt(16) + 8;
                int j6 = random.nextInt(16) + 8;
                this.gravelGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(i2, 0, j6)));
            }

        int k1 = this.treesPerChunk;

        if (random.nextFloat() < this.extraTreeChance)
        {
            ++k1;
        }


        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS))
            for (int l2 = 0; l2 < this.flowersPerChunk; ++l2)
            {
                int i7 = random.nextInt(16) + 8;
                int l10 = random.nextInt(16) + 8;
                int j14 = worldIn.getHeight(this.chunkPos.add(i7, 0, l10)).getY() + 32;

                if (j14 > 0)
                {
                    int k17 = random.nextInt(j14);
                    BlockPos blockpos1 = this.chunkPos.add(i7, k17, l10);

                    // Pick Random Flower
                    BlockFlowerMTR flower = BlockInit.FLUORESCENT_FLOWER_RED;
                    int z = random.nextInt(3); // TODO add more
                    switch (z){
                        case 1:
                            flower = BlockInit.FLUORESCENT_FLOWER_GREEN;
                            break;
                        case 2:
                            flower = BlockInit.FLUORESCENT_FLOWER_BLUE;
                        default:
                            break;
                    }

                    if (flower.getDefaultState().getMaterial() != Material.AIR)
                    {
                        this.flowerGen.setGeneratedBlock(flower);
                        this.flowerGen.generate(worldIn, random, blockpos1);
                    }
                }
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
            for (int i3 = 0; i3 < this.grassPerChunk; ++i3)
            {
                int j7 = random.nextInt(16) + 8;
                int i11 = random.nextInt(16) + 8;
                int k14 = worldIn.getHeight(this.chunkPos.add(j7, 0, i11)).getY() * 2;

                if (k14 > 0)
                {
                    int l17 = random.nextInt(k14);
                    biomeIn.getRandomWorldGenForGrass(random).generate(worldIn, random, this.chunkPos.add(j7, l17, i11));
                }
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH))
            for (int j3 = 0; j3 < this.deadBushPerChunk; ++j3)
            {
                int k7 = random.nextInt(16) + 8;
                int j11 = random.nextInt(16) + 8;
                int l14 = worldIn.getHeight(this.chunkPos.add(k7, 0, j11)).getY() * 2;

                if (l14 > 0)
                {
                    int i18 = random.nextInt(l14);
                    (new WorldGenDeadBush()).generate(worldIn, random, this.chunkPos.add(k7, i18, j11));
                }
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD))
            for (int k3 = 0; k3 < this.waterlilyPerChunk; ++k3)
            {
                int l7 = random.nextInt(16) + 8;
                int k11 = random.nextInt(16) + 8;
                int i15 = worldIn.getHeight(this.chunkPos.add(l7, 0, k11)).getY() * 2;

                if (i15 > 0)
                {
                    int j18 = random.nextInt(i15);
                    BlockPos blockpos4;
                    BlockPos blockpos7;

                    for (blockpos4 = this.chunkPos.add(l7, j18, k11); blockpos4.getY() > 0; blockpos4 = blockpos7)
                    {
                        blockpos7 = blockpos4.down();

                        if (!worldIn.isAirBlock(blockpos7))
                        {
                            break;
                        }
                    }

                    this.waterlilyGen.generate(worldIn, random, blockpos4);
                }
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED))
        {
            for (int k4 = 0; k4 < this.reedsPerChunk; ++k4)
            {
                int i9 = random.nextInt(16) + 8;
                int l12 = random.nextInt(16) + 8;
                int i16 = worldIn.getHeight(this.chunkPos.add(i9, 0, l12)).getY() * 2;

                if (i16 > 0)
                {
                    int l18 = random.nextInt(i16);
                    this.reedGen.generate(worldIn, random, this.chunkPos.add(i9, l18, l12));
                }
            }

            for (int l4 = 0; l4 < 10; ++l4)
            {
                int j9 = random.nextInt(16) + 8;
                int i13 = random.nextInt(16) + 8;
                int j16 = worldIn.getHeight(this.chunkPos.add(j9, 0, i13)).getY() * 2;

                if (j16 > 0)
                {
                    int i19 = random.nextInt(j16);
                    this.reedGen.generate(worldIn, random, this.chunkPos.add(j9, i19, i13));
                }
            }
        } // End of Reed generation

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE))
            for (int j2 = 0; j2 < k1; ++j2)
            {

                int k6 = random.nextInt(16) + 8;
                int l = random.nextInt(16) + 8;
                WorldGenAbstractTree worldgenabstracttree = biomeIn.getRandomTreeFeature(random);
                worldgenabstracttree.setDecorationDefaults();
                BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(k6, 0, l));

                if (worldgenabstracttree.generate(worldIn, random, blockpos))
                {
                    worldgenabstracttree.generateSaplings(worldIn, random, blockpos);
                }
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN))
            if (random.nextInt(32) == 0)
            {
                int i5 = random.nextInt(16) + 8;
                int k9 = random.nextInt(16) + 8;
                int j13 = worldIn.getHeight(this.chunkPos.add(i5, 0, k9)).getY() * 2;

                if (j13 > 0)
                {
                    int k16 = random.nextInt(j13);
                    (new WorldGenPumpkin()).generate(worldIn, random, this.chunkPos.add(i5, k16, k9));
                }
            }

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS))
            for (int j5 = 0; j5 < this.cactiPerChunk; ++j5)
            {
                int l9 = random.nextInt(16) + 8;
                int k13 = random.nextInt(16) + 8;
                int l16 = worldIn.getHeight(this.chunkPos.add(l9, 0, k13)).getY() * 2;

                if (l16 > 0)
                {
                    int j19 = random.nextInt(l16);
                    this.cactusGen.generate(worldIn, random, this.chunkPos.add(l9, j19, k13));
                }
            }

        if (this.generateFalls)
        {
            if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE_WATER))
                for (int k5 = 0; k5 < 50; ++k5)
                {
                    int i10 = random.nextInt(16) + 8;
                    int l13 = random.nextInt(16) + 8;
                    int i17 = random.nextInt(248) + 8;

                    if (i17 > 0)
                    {
                        int k19 = random.nextInt(i17);
                        BlockPos blockpos6 = this.chunkPos.add(i10, k19, l13);
                        (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(worldIn, random, blockpos6);
                    }
                }

            if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA))
                for (int l5 = 0; l5 < 20; ++l5)
                {
                    int j10 = random.nextInt(16) + 8;
                    int i14 = random.nextInt(16) + 8;
                    int j17 = random.nextInt(random.nextInt(random.nextInt(240) + 8) + 8);
                    BlockPos blockpos3 = this.chunkPos.add(j10, j17, i14);
                    (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(worldIn, random, blockpos3);
                }
        }
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Post(worldIn, random, forgeChunkPos));
    }
}
