package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.util.interfaces.IBuilding;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.*;

public class BuildingTower implements IBuilding {
    private int chunkX;
    private int chunkZ;
    private int baseHeight;
    private int floorCount;
    private EnumFacing facing;

    private IBlockState baseBlock;
    private IBlockState fillerBlock;
    protected final IBlockState[] fillerBlocks = new IBlockState[]{
            BlockInit.POLISHED_FOUNDATION_STONE.getDefaultState(),
            Blocks.QUARTZ_BLOCK.getDefaultState()
    };
    protected final IBlockState air = Blocks.AIR.getDefaultState();
    protected final IBlockState glass = Blocks.GLASS_PANE.getDefaultState();
    protected final IBlockState lamp = BlockInit.CEILING_LIGHT.getDefaultState();

    public BuildingTower(int chunkX, int chunkZ, int baseHeight, Random rand, EnumFacing facing){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
        this.facing = facing;
        this.baseBlock = Blocks.DOUBLE_STONE_SLAB.getDefaultState();
        this.fillerBlock = this.fillerBlocks[rand.nextInt(this.fillerBlocks.length)];
        this.floorCount = this.getMinFloor() + rand.nextInt(this.getFloorVariation());
    }

    @Override
    public String getType() {
        return "tower";
    }

    @Override
    public IBlockState getBaseBlock() {
        return baseBlock;
    }

    @Override
    public IBlockState[] getFillerBlocks() {
        return fillerBlocks;
    }

    @Override
    public int getMinFloor() {
        return 8;
    }

    @Override
    public int getFloorVariation() {
        return 5;
    }

    @Override
    public int getFloorHeight() {
        return 6;
    }

    @Override
    public void setFillerBlock(IBlockState fillerBlock) {
        this.fillerBlock = fillerBlock;
    }

    @Override
    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    @Override
    public void generate(ChunkPrimer primer) {
        int y = baseHeight;

        //FLOOR
        fillMargin(primer, 0, 0, 15, 15, y, this.baseBlock);
        fillLayer(primer, 1, 1, 14, 14, y, this.fillerBlock);

        for (int f = 0; f < floorCount; f++) {
            fillMargin(primer, 1, 1, 14, 14, y + 1, this.fillerBlock);
            fillWalls(primer, 1, y + 2, 1, 14, y + this.getFloorHeight() - 1, 14, glass);
            fillLayer(primer, 1, 1, 14, 14, y + this.getFloorHeight(), this.fillerBlock);

            //LIGHTS
            fillMarginPattern(primer, 4, y + this.getFloorHeight() - 1, 4, new IBlockState[]{
                    lamp,
                    air,
                    lamp,
                    air,
                    air,
                    lamp,
                    air,
            });

            //STAIRS PATTERN 1

            int x1 = 6;
            int z1 = 6;
            int y1 = y;
            int j = 1;

            fillLayer(primer,
                    8, 6, 9, 9,
                    y + this.getFloorHeight(),
                    Blocks.AIR.getDefaultState());

            fill(primer,
                    7, y + 1, 7,
                    8, y + this.getFloorHeight(), 8,
                    this.fillerBlock);

            for (int i1 = 0; i1 < 12; i1++) {
                if (i1 >= 6) j = -1;
                if (i1 % 2 == 0) {
                    y1++;
                    primer.setBlockState(x1, y1, z1, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                } else {
                    primer.setBlockState(x1, y1, z1, Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                }

                if (i1 % 6 < 3) {
                    z1 += j;
                } else {
                    x1 += j;
                }
            }

            //DOOR
            if(f == 0){
                switch (this.facing.getIndex()){
                    case 2: //NORTH
                        fill(primer, 7, y + 2, 1, 8, y + 3, 1, air);
                        break;
                    case 3: //SOUTH
                        fill(primer, 7, y + 2, 14, 8, y + 3, 14, air);
                        break;
                    case 4: //WEST
                        fill(primer, 1, y + 2, 7, 1, y + 3, 8, air);
                        break;
                    case 5: //EAST
                        fill(primer, 14, y + 2, 7, 14, y + 3, 8, air);
                        break;
                    default:
                        System.out.println("ERROR! Building Facing illegal");
                        fill(primer, 7, y + 2, 1, 8, y + 3, 1, Blocks.BEDROCK.getDefaultState());
                        break;
                }
            }

            y += this.getFloorHeight();
        }

        fillLayer(primer, 6, 6, 9, 9, y + 1, fillerBlock);
    }
}
