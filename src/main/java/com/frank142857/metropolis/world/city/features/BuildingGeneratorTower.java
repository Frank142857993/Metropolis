package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.init.BlockInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.*;

public class BuildingGeneratorTower { //TODO Implement everything about tower
    private int floorCount;
    private int roofType;
    private EnumFacing facing;

    private IBlockState baseBlock;
    private IBlockState fillerBlock;
    private IBlockState[] fillerBlocks = new IBlockState[]{
            BlockInit.MARBLE.getDefaultState(),
            //BlockInit.BLACK_BRICK.getDefaultState(),
            //BlockInit.MARBLE_BRICK.getDefaultState()
    };
    protected final IBlockState air = Blocks.AIR.getDefaultState();
    protected final IBlockState glass = BlockInit.CLOUD_GLASS_PANE.getDefaultState();
    protected final IBlockState lamp = BlockInit.CEILING_LIGHT.getDefaultState();
    protected final IBlockState pillar = BlockInit.MARBLE_PILLAR.getDefaultState();

    public int getMinFloor() {
        return 8;
    }

    public int getFloorVariation() {
        return 4;
    }

    public int getFloorHeight() {
        return 6;
    }

    public int getLayerCount() {
        return 2;
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
        IBlockState[] sq = new IBlockState[]{
                air,
                pillar,
                fillerBlock,
                fillerBlock,
                pillar
        };


        for(int f = 0; f < floorCount; f++) {
            //WALLS & WINDOWS
            fillWallsPattern(primer, 5, y + 1, 5, this.getFloorHeight(), sq);
            y += this.getFloorHeight();
        }

        fillMargin(primer, 6, 6,9, 9, y + 1, fillerBlock);
        fillMargin(primer, 5, 5,10, 10, y + 1, fillerBlock);
        fillMargin(primer, 4, 4,11, 11, y + 1, fillerBlock);
        fillMargin(primer, 3, 3,12, 12, y + 2, fillerBlock);
        fillMargin(primer, 2, 2,13, 13, y + 2, fillerBlock);

        y = y + 3;

        for (int m = 0; m < rand.nextInt(3); m++) {
            fillMargin(primer, 1, 1,14, 14, y, fillerBlock);
            fillMargin(primer, 0, 0, 15, 15, y, fillerBlock);

            fillWallsPattern(primer, 0, y + 1, 0, this.getFloorHeight() - 2, new IBlockState[]{
                    pillar,
                    glass,
                    pillar,
                    glass,
                    pillar,
                    glass,
                    pillar,
                    glass,
                    glass,
                    pillar,
                    glass,
                    pillar,
                    glass,
                    pillar,
                    glass
            });

            fillMargin(primer, 0, 0, 15, 15, y + this.getFloorHeight() - 1, fillerBlock);
            fillMargin(primer, 1, 1, 14, 14, y + this.getFloorHeight() - 1, fillerBlock);

            y = y + this.getFloorHeight();

            fillMargin(primer, 2, 2,13, 13, y, fillerBlock);
            fillMargin(primer, 3, 3,12, 12, y, fillerBlock);
            fillMargin(primer, 4, 4,11, 11, y + 1, fillerBlock);
            fillMargin(primer, 5, 5,10, 10, y + 1, fillerBlock);
            fillMargin(primer, 6, 6,9, 9, y + 2, fillerBlock);
            fillMargin(primer, 6, 6,9, 9, y + 3, fillerBlock);
            fillMargin(primer, 5, 5,10, 10, y + 4, fillerBlock);
            fillMargin(primer, 4, 4,11, 11, y + 4, fillerBlock);
            fillMargin(primer, 3, 3,12, 12, y + 5, fillerBlock);
            fillMargin(primer, 2, 2,13, 13, y + 5, fillerBlock);

            y = y + 6;
        }
        //SIMPLE ROOF
        //makeRoof(primer, y);
        //addDetails(primer, baseHeight, rand);

        fillMargin(primer, 1, 1,14, 14, y, fillerBlock);
        fillMargin(primer, 0, 0, 15, 15, y, fillerBlock);

        fillWallsPattern(primer, 0, y + 1, 0, this.getFloorHeight() - 2, new IBlockState[]{
                pillar,
                glass,
                pillar,
                glass,
                pillar,
                glass,
                pillar,
                glass,
                glass,
                pillar,
                glass,
                pillar,
                glass,
                pillar,
                glass
        });

        fillLayer(primer, 0, 0, 15, 15, y + this.getFloorHeight() - 1, fillerBlock);
    }

    public void makeRoof(ChunkPrimer primer, int height){

        switch (this.roofType){
            case 0:
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
