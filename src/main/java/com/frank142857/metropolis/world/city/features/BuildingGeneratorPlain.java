package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.init.BlockInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.*;
import static com.frank142857.metropolis.world.city.ChunkGenFactory.fillMargin;
import static com.frank142857.metropolis.world.city.ChunkGenFactory.fillMarginPattern;

public class BuildingGeneratorPlain {
    private int floorCount;
    private int roofType;
    private EnumFacing facing;

    private IBlockState baseBlock;
    private IBlockState fillerBlock;
    private IBlockState[] fillerBlocks = new IBlockState[]{
            BlockInit.POLISHED_FOUNDATION_STONE.getDefaultState(),
            //BlockInit.BLACK_BRICK.getDefaultState(),
            //BlockInit.MARBLE_BRICK.getDefaultState()
    };
    protected final IBlockState air = Blocks.AIR.getDefaultState();
    protected final IBlockState glass = BlockInit.CLOUD_GLASS_PANE.getDefaultState();
    protected final IBlockState lamp = BlockInit.CEILING_LIGHT.getDefaultState();
    protected IBlockState[] sq;

    public int getMinFloor() {
        return 3;
    }

    public int getFloorVariation() {
        return 4;
    }

    public int getFloorHeight() {
        return 6;
    }

    public void setFillerBlocks(IBlockState[] fillerBlocks){
        this.fillerBlocks = fillerBlocks;
    }

    public void generate(ChunkPrimer primer, int baseHeight, Random rand) {

        int index = 2 + rand.nextInt(4); //DIRECTION
        this.facing = EnumFacing.getFront(index);
        this.floorCount = this.getMinFloor() + rand.nextInt(this.getFloorVariation());
        this.baseBlock = BlockInit.STONE_PAVING.getDefaultState();
        this.fillerBlock = this.fillerBlocks[rand.nextInt(this.fillerBlocks.length)];
        this.roofType = rand.nextInt(4); //roof count

        int y = baseHeight;

        //WALLS SEQUENCE
        IBlockState[] sq = new IBlockState[13];
        sq[0] = air;
        for(int i2 = 1; i2 < sq.length; i2++){
            sq[i2] = fillerBlock;
        }
        this.sq = sq;

        //FLOOR
        fillMargin(primer, 0, 0, 15, 15, y, this.baseBlock);
        fillLayer(primer, 1, 1, 14, 14, y, this.fillerBlock);

        for(int f = 0; f < floorCount; f++) {
            //WALLS & WINDOWS
            fillMarginPattern(primer, 1, y + 1, 1, sq);

            fillWallsPattern(primer, 1, y + 2, 1, 3, new IBlockState[]{
                    air,
                    fillerBlock,
                    glass,
                    fillerBlock,
                    glass,
                    fillerBlock,
                    glass,
                    glass,
                    fillerBlock,
                    glass,
                    fillerBlock,
                    glass,
                    fillerBlock
            });

            fillWallsPattern(primer, 1, y + this.getFloorHeight() - 1, 1, 2, sq);
            fillLayer(primer, 2, 2, 13, 13, y + this.getFloorHeight(), fillerBlock);

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
                    primer.setBlockState(x1, y1, z1, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                } else {
                    primer.setBlockState(x1, y1, z1, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
                }

                if (i1 % 6 < 3) {
                    z1 += j;
                } else {
                    x1 += j;
                }
            }

            //DOOR
            if (f == 0) {
                switch (this.facing.getIndex()) {
                    case 2: //NORTH
                        fill(primer, 7, y + 1, 0, 8, y + 1, 0, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                        fill(primer, 7, y + 2, 1, 8, y + 3, 1, air);
                        fill(primer, 7, y + 1, 2, 8, y + 1, 2, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                        break;
                    case 3: //SOUTH
                        fill(primer, 7, y + 1, 15, 8, y + 1, 15, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                        fill(primer, 7, y + 2, 14, 8, y + 3, 14, air);
                        fill(primer, 7, y + 1, 13, 8, y + 1, 13, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                        break;
                    case 4: //WEST
                        fill(primer, 0, y + 1, 7, 0, y + 1, 8, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                        fill(primer, 1, y + 2, 7, 1, y + 3, 8, air);
                        fill(primer, 2, y + 1, 7, 2, y + 1, 8, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                        break;
                    case 5: //EAST
                        fill(primer, 15, y + 1, 7, 15, y + 1, 8, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                        fill(primer, 14, y + 2, 7, 14, y + 3, 8, air);
                        fill(primer, 13, y + 1, 7, 13, y + 1, 8, BlockInit.SILVER_WOOD_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));
                        break;
                    default:
                        System.out.println("ERROR! Building Facing illegal");
                        fill(primer, 7, y + 2, 1, 8, y + 3, 1, Blocks.BEDROCK.getDefaultState());
                        break;
                }
            }

            y += this.getFloorHeight();
        }

        //SIMPLE ROOF
        makeRoof(primer, y);
        addDetails(primer, baseHeight, rand);
    }

    public void makeRoof(ChunkPrimer primer, int height){

        switch (this.roofType){
            case 0: //TODO Garden
                fillMarginPattern(primer, 1, height + 1, 1, sq);
                fillLayer(primer, 2, 2, 13, 13, height + 1, BlockInit.SURFACE_GRASS.getDefaultState());
                fillLayer(primer, 6, 6, 9, 9, height + 1, Blocks.AIR.getDefaultState());
                fillMargin(primer, 5, 5, 10, 10, height + 1, fillerBlock);
                break;
            case 1:
                fillLayer(primer, 2, 2, 13, 13, height + 1, fillerBlock);
                break;
            case 2:
                fillMargin(primer, 2, 2, 13, 13, height + 1, fillerBlock);
                fillWallsPattern(primer, 2, height + 2, 2, 3, new IBlockState[]{
                        fillerBlock,
                        fillerBlock,
                        glass,
                        fillerBlock,
                        glass,
                        fillerBlock,
                        fillerBlock,
                        glass,
                        fillerBlock,
                        glass,
                        fillerBlock
                });
                fillMargin(primer, 2, 2, 13, 13, height + 5, fillerBlock);
                fillMargin(primer, 7, 7, 8, 8, height + 5, lamp);
                fillLayer(primer, 3, 3, 12, 12, height + 6, fillerBlock);
                break;
            default:
                IBlockState[] top = new IBlockState[5];
                top[0] = lamp;

                int i = 0;
                while(i < 6){
                    fillMargin(primer, 2 + i, 2 + i, 13 - i, 13 - i, height + i + 1, fillerBlock);
                    i = i + 1;
                }
                fillMarginPattern(primer, 5, height + 3, 5, top);
                break;
        }
    }

    public void addDetails(ChunkPrimer primer, int baseHeight, Random rand) {
        //2 2 13 13
        //Test: library
        fillMargin(primer, 2, 2, 13, 13, baseHeight + getFloorHeight() + 1, BlockInit.SILVER_BOOKSHELF.getDefaultState());
        fillMargin(primer, 2, 2, 13, 13, baseHeight + getFloorHeight() + 5, BlockInit.SILVER_BOOKSHELF.getDefaultState());
        primer.setBlockState(2, baseHeight + getFloorHeight() + 1, 2, Blocks.CRAFTING_TABLE.getDefaultState());
        primer.setBlockState(2, baseHeight + getFloorHeight() + 1, 13, Blocks.CRAFTING_TABLE.getDefaultState());
        primer.setBlockState(13, baseHeight + getFloorHeight() + 1, 2, Blocks.CRAFTING_TABLE.getDefaultState());
        primer.setBlockState(13, baseHeight + getFloorHeight() + 1, 13, Blocks.CRAFTING_TABLE.getDefaultState());
        fillMarginPattern(primer, 4, baseHeight + getFloorHeight() + 1, 4, new IBlockState[]{
                BlockInit.SILVER_WOOD_FENCE.getDefaultState(),
                BlockInit.SILVER_WOOD_FENCE.getDefaultState(),
                air,
                air,
                air,
                air,
                BlockInit.SILVER_WOOD_FENCE.getDefaultState()
        });
        fillMarginPattern(primer, 4, baseHeight + getFloorHeight() + 2, 4, new IBlockState[]{
                Blocks.CARPET.getDefaultState(),
                Blocks.CARPET.getDefaultState(),
                air,
                air,
                air,
                air,
                Blocks.CARPET.getDefaultState()
        });
        /*
        if(rand.nextInt(20) == 1){
            fillMargin(primer, 2, 2, 13, 13, height + floorCount + 1, Blocks.BOOKSHELF.getDefaultState());
        }*/
    }
}
