package com.frank142857.metropolis.world.gen.feature;

import com.frank142857.metropolis.init.BlockInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenDevBuildings extends WorldGenerator {

    private static final BlockStateMatcher IS_SURFACE = BlockStateMatcher.forBlock(BlockInit.SURFACE_GRASS);
    private final IBlockState BRICK = BlockInit.POLISHED_FOUNDATION_STONE.getDefaultState();
    private final IBlockState GLASS = Blocks.GLASS_PANE.getDefaultState();

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        while(worldIn.isAirBlock(position) && position.getY() > 2){
            position = position.down();
        }

        if(!IS_SURFACE.apply(worldIn.getBlockState(position))) return false;
        else {
            for(int i1 = -2; i1 <= 2; ++i1){
                for(int i2 = -2; i2 <= 2; ++i2){
                    int j = -1;
                    while(worldIn.isAirBlock(position.add(i1, j, i2)) || !worldIn.getBlockState(new BlockPos(position.add(i1, j, i2))).isFullBlock()){
                        worldIn.setBlockState(position.add(i1, j, i2), BRICK);
                        --j;
                    }
                }
            }
        }
        for(int l = -1; l <= 10; ++l){
            for(int j1 = -2; j1 <= 2; ++j1){
                for(int j2 = -2; j2 <= 2; ++j2){
                    worldIn.setBlockState(position.add(j1, l, j2), BRICK);
                }
            }
        }
        return true;
    }
}
