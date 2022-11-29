package com.frank142857.metropolis.world.city;

import com.frank142857.metropolis.init.BlockInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.ChunkPrimer;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.*;

public class Building {
    private int chunkX;
    private int chunkZ;
    private int baseHeight;
    private int floor;
    private EnumFacing facing;
    private IBlockState baseBlock;
    private IBlockState fillerBlock;
    private BuildingType type;
    private ChunkInfoMetropolis info;

    protected final IBlockState air = Blocks.AIR.getDefaultState();
    protected final IBlockState glass = Blocks.GLASS_PANE.getDefaultState();
    protected final IBlockState lamp = BlockInit.CEILING_LIGHT.getDefaultState();

    public Building(int chunkX, int chunkZ, int baseHeight, EnumFacing facing, BuildingType type) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
        this.facing = facing;
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

    public void setType(BuildingType type){
        this.type = type;
    }

    public void generate(ChunkPrimer primer){

        int y = baseHeight;

        //NORMAL-WALLS
        IBlockState[] sq = new IBlockState[13];
        sq[0] = air;
        for(int i2 = 1; i2 < sq.length; i2++){
            sq[i2] = fillerBlock;
        }

        fillMargin(primer, 0, 0, 15, 15, y, this.baseBlock);

        if(!this.type.equals(BuildingType.NONE)) {
            fillLayer(primer, 1, 1, 14, 14, y, this.fillerBlock); //TODO BASE

            for (int f = 0; f < floor; f++) {

                if (this.type.equals(BuildingType.TOWER)) {
                    fillMargin(primer, 1, 1, 14, 14, y + 1, this.fillerBlock);
                    fillWalls(primer, 1, y + 2, 1, 14, y + type.getFloorHeight() - 1, 14, Blocks.GLASS_PANE.getDefaultState());
                    fillLayer(primer, 1, 1, 14, 14, y + type.getFloorHeight(), this.fillerBlock);
                } else if (this.type.equals(BuildingType.NORMAL)) {

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

                    fillWallsPattern(primer, 1, y + type.getFloorHeight() - 1, 1, 2, sq);
                    fillLayer(primer, 2, 2, 13, 13, y + type.getFloorHeight(), fillerBlock);

                }

                //LIGHTS
                fillMarginPattern(primer, 4, y + type.getFloorHeight() - 1, 4, new IBlockState[]{
                        lamp,
                        air,
                        lamp,
                        air,
                        air,
                        lamp,
                        air,
                });


                //STAIRS
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

                //STAIRS PATTERN 1

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

                y += this.type.getFloorHeight();
            }
            fillLayer(primer, 6, 6, 9, 9, y + 1, fillerBlock);
        }
    }
}

