package com.frank142857.metropolis.world.city;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import static com.frank142857.metropolis.world.city.ChunkGenUtil.*;
import static net.minecraft.block.BlockSlab.HALF;

public class Building {
    private int chunkX;
    private int chunkZ;
    private int baseHeight;
    private IBlockState baseBlock;
    private IBlockState fillerBlock;
    private int floor;
    private BuildingType type;
    private ChunkInfoMetropolis info;

    public Building(int chunkX, int chunkZ, int baseHeight, BuildingType type) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
        this.type = type;
        this.baseBlock = type.getBaseBlock();
        this.fillerBlock = Blocks.COBBLESTONE.getDefaultState();
        this.floor = 0;
    }

    public BuildingType getBuildingType() {
        return type;
    }

    public void setFillerBlock(IBlockState fillerBlock) {
        this.fillerBlock = fillerBlock;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void generate(ChunkPrimer primer){
        int y = baseHeight;

        fillMargin(primer, 0, 0, 15, 15, y, this.baseBlock);
        fillLayer(primer, 1, 1, 14, 14, y, this.fillerBlock);

        for(int f = 0; f < floor; f++){

            if(this.type.equals(BuildingType.TOWER)){
                fillMargin(primer, 1, 1, 14, 14, y + 1, this.fillerBlock);
                fillWalls(primer, 1, y + 2, 1, 14, y + type.getFloorHeight() - 1, 14, Blocks.GLASS_PANE.getDefaultState());
                fillLayer(primer, 1, 1, 14, 14, y + type.getFloorHeight(), this.fillerBlock);
            }

            else {
                fillWalls(primer, 1, y + 1, 1, 14, y + type.getFloorHeight() - 1, 14, this.fillerBlock);
                fillLayer(primer, 1, 1, 14, 14, y + type.getFloorHeight(), this.fillerBlock);

                fill(primer, 3, y + 2, 1, 5, y + type.getFloorHeight() - 2, 1, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 10, y + 2, 1, 12, y + type.getFloorHeight() - 2, 1, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 3, y + 2, 14, 5, y + type.getFloorHeight() - 2, 14, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 10, y + 2, 14, 12, y + type.getFloorHeight() - 2, 14, Blocks.GLASS_PANE.getDefaultState());

                fill(primer, 1, y + 2, 3, 1, y + type.getFloorHeight() - 2, 5, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 1, y + 2, 10, 1, y + type.getFloorHeight() - 2, 12, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 14, y + 2, 3, 14, y + type.getFloorHeight() - 2, 5, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 14, y + 2, 10, 14, y + type.getFloorHeight() - 2, 12, Blocks.GLASS_PANE.getDefaultState());
            }

            /*
            int x1 = 8 - type.getFloorHeight() / 2;

            fillLayer(primer,
                    3 + type.getFloorHeight() / 2, 7,
                    8 + type.getFloorHeight() / 2, 8,
                    y + type.getFloorHeight(),
                    Blocks.AIR.getDefaultState());

            for(int y1 = y + 1; y1 <= y + type.getFloorHeight(); y1++){
                primer.setBlockState(x1, y1, 7, Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST)); //TODO block category
                primer.setBlockState(x1, y1, 8, Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
                primer.setBlockState(x1 + 1, y1, 7, this.fillerBlock);
                primer.setBlockState(x1 + 1, y1, 8, this.fillerBlock);
                x1++;
            }
            */

            int x1 = 6;
            int z1 = 6;
            int y1 = y;
            int j = 1;

            fillLayer(primer,
                    8, 6, 9, 9,
                    y + type.getFloorHeight(),
                    Blocks.AIR.getDefaultState());

            fill(primer,
                    7, y + 1, 7,
                    8, y + type.getFloorHeight(), 8,
                    this.fillerBlock);

            for(int i1 = 0; i1 < 12; i1++){
                if(i1 >= 6) j = -1;
                if(i1 % 2 == 0) {
                    y1++;
                    primer.setBlockState(x1, y1, z1, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                } else {
                    primer.setBlockState(x1, y1, z1, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                }

                if(i1 % 6 < 3){
                    z1 += j;
                } else {
                    x1 += j;
                }
            }

            y += this.type.getFloorHeight();
        }
    }
}

