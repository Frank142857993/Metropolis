package com.frank142857.metropolis.world.city;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

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

                fill(primer, 3, y + 2, 1, 5, y + 4, 1, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 10, y + 2, 1, 12, y + 4, 1, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 3, y + 2, 14, 5, y + 4, 14, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 10, y + 2, 14, 12, y + 4, 14, Blocks.GLASS_PANE.getDefaultState());

                fill(primer, 1, y + 2, 3, 1, y + 4, 5, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 1, y + 2, 10, 1, y + 4, 12, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 14, y + 2, 3, 14, y + 4, 5, Blocks.GLASS_PANE.getDefaultState());
                fill(primer, 14, y + 2, 10, 14, y + 4, 12, Blocks.GLASS_PANE.getDefaultState());
            }

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

            y += this.type.getFloorHeight();
        }
    }

    public void fill(ChunkPrimer primer, int x0, int y0, int z0, int xt, int yt, int zt, IBlockState block){
        for(int y = y0; y <= yt; y++){
            fillLayer(primer, x0, z0, xt, zt, y, block);
        }
    }

    public void fillLayer(ChunkPrimer primer, int x0, int z0, int xt, int zt, int y, IBlockState block){
        for(int x = x0; x <= xt; x++){
            for(int z = z0; z <= zt; z++){
                primer.setBlockState(x, y, z, block);
            }
        }
    }

    public void fillMargin(ChunkPrimer primer, int x0, int z0, int xt, int zt, int y, IBlockState block){
        for(int x = x0; x <= xt; x++){
            for(int z = z0; z <= zt; z++){
                if ((x == x0 || x == xt) || (z == z0 || z == zt)) {
                    primer.setBlockState(x, y, z, block);
                }
            }
        }
    }

    public void fillWalls(ChunkPrimer primer, int x0, int y0, int z0, int xt, int yt, int zt, IBlockState block){
        for(int y = y0; y <= yt; y++){
            fillMargin(primer, x0, z0, xt, zt, y, block);
        }
    }
}

