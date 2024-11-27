package com.frank142857.metropolis.world.gen;

import com.frank142857.metropolis.block.base.BlockBushMTR;
import com.frank142857.metropolis.block.base.BlockFlowerMTR;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFlowerMTR extends WorldGenerator {
    private BlockFlowerMTR flower;
    private IBlockState state;

    public WorldGenFlowerMTR(BlockFlowerMTR flowerIn)
    {
        this.setGeneratedBlock(flowerIn);
    }

    public void setGeneratedBlock(BlockFlowerMTR flowerIn)
    {
        this.flower = flowerIn;
        this.state = flowerIn.getDefaultState();
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255) && this.flower.canBlockStay(worldIn, blockpos, this.state))
            {
                worldIn.setBlockState(blockpos, this.state, 2);
            }
        }

        return true;
    }
}
