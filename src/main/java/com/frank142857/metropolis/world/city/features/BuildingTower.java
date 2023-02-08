package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.util.interfaces.IBuilding;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.*;

public class BuildingTower implements IBuilding {
    private int chunkX;
    private int chunkZ;
    private int baseHeight;
    private int floorCount;
    private int roofType;
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
        this.roofType = rand.nextInt(3); //roof count
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
    public ChunkPos getChunkPos() {
        return new ChunkPos(chunkX, chunkZ);
    }

    @Override
    public int getBaseHeight() {
        return baseHeight;
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
    public void generate(World worldIn, ChunkPrimer primer) {
        int y = baseHeight;

        //FLOOR
        fillMargin(primer, 0, 0, 15, 15, y, this.baseBlock);
        fillLayer(primer, 1, 1, 14, 14, y, this.fillerBlock);

        for (int f = 0; f < floorCount; f++) {
            fillMargin(primer, 1, 1, 14, 14, y + 1, this.fillerBlock);
            fillWalls(primer, 1, y + 2, 1, 14, y + this.getFloorHeight() - 1, 14, glass);
            fillPillar(primer, 1, y + 2, 1, 4, this.fillerBlock);
            fillPillar(primer, 1, y + 2, 14, 4, this.fillerBlock);
            fillPillar(primer, 14, y + 2, 1, 4, this.fillerBlock);
            fillPillar(primer, 14, y + 2, 14, 4, this.fillerBlock);
            switch (this.facing.getIndex()){
                case 2: //NORTH
                    fillPillar(primer, 6, y + 2, 1, 4, this.fillerBlock);
                    fillPillar(primer, 9, y + 2, 1, 4, this.fillerBlock);
                    fill(primer, 7, y + 2, 14, 8, y + this.getFloorHeight() - 1, 14, this.fillerBlock);
                    fill(primer, 1, y + 2, 7, 1, y + this.getFloorHeight() - 1, 8, this.fillerBlock);
                    fill(primer, 14, y + 2, 7, 14, y + this.getFloorHeight() - 1, 8, this.fillerBlock);
                    break;
                case 3: //SOUTH
                    fillPillar(primer, 6, y + 2, 14, 4, this.fillerBlock);
                    fillPillar(primer, 9, y + 2, 14, 4, this.fillerBlock);
                    fill(primer, 7, y + 2, 1, 8, y + this.getFloorHeight() - 1, 1, this.fillerBlock);
                    fill(primer, 1, y + 2, 7, 1, y + this.getFloorHeight() - 1, 8, this.fillerBlock);
                    fill(primer, 14, y + 2, 7, 14, y + this.getFloorHeight() - 1, 8, this.fillerBlock);
                    break;
                case 4: //WEST
                    fillPillar(primer, 1, y + 2, 6, 4, this.fillerBlock);
                    fillPillar(primer, 1, y + 2, 9, 4, this.fillerBlock);
                    fill(primer, 14, y + 2, 7, 14, y + this.getFloorHeight() - 1, 8, this.fillerBlock);
                    fill(primer, 7, y + 2, 1, 8, y + this.getFloorHeight() - 1, 1, this.fillerBlock);
                    fill(primer, 7, y + 2, 14, 8, y + this.getFloorHeight() - 1, 14, this.fillerBlock);
                    break;
                case 5: //EAST
                    fillPillar(primer, 14, y + 2, 6, 4, this.fillerBlock);
                    fillPillar(primer, 14, y + 2, 9, 4, this.fillerBlock);
                    fill(primer, 1, y + 2, 7, 1, y + this.getFloorHeight() - 1, 8, this.fillerBlock);
                    fill(primer, 7, y + 2, 1, 8, y + this.getFloorHeight() - 1, 1, this.fillerBlock);
                    fill(primer, 7, y + 2, 14, 8, y + this.getFloorHeight() - 1, 14, this.fillerBlock);
                    break;
                default:
                    System.out.println("ERROR! Building Facing illegal");
                    fill(primer, 7, y + 2, 1, 8, y + 3, 1, Blocks.BEDROCK.getDefaultState());
                    break;
            }

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
                        fill(primer, 7, y + 1, 0, 8, y + 1, 0, Blocks.STONE_SLAB.getDefaultState());
                        fill(primer, 7, y + 2, 1, 8, y + 3, 1, air);
                        fill(primer, 7, y + 1, 2, 8, y + 1, 2, Blocks.STONE_SLAB.getDefaultState());
                        break;
                    case 3: //SOUTH
                        fill(primer, 7, y + 1, 15, 8, y + 1, 15, Blocks.STONE_SLAB.getDefaultState());
                        fill(primer, 7, y + 2, 14, 8, y + 3, 14, air);
                        fill(primer, 7, y + 1, 13, 8, y + 1, 13, Blocks.STONE_SLAB.getDefaultState());
                        break;
                    case 4: //WEST
                        fill(primer, 0, y + 1, 7, 0, y + 1, 8, Blocks.STONE_SLAB.getDefaultState());
                        fill(primer, 1, y + 2, 7, 1, y + 3, 8, air);
                        fill(primer, 2, y + 1, 7, 2, y + 1, 8, Blocks.STONE_SLAB.getDefaultState());
                        break;
                    case 5: //EAST
                        fill(primer, 15, y + 1, 7, 15, y + 1, 8, Blocks.STONE_SLAB.getDefaultState());
                        fill(primer, 14, y + 2, 7, 14, y + 3, 8, air);
                        fill(primer, 13, y + 1, 7, 13, y + 1, 8, Blocks.STONE_SLAB.getDefaultState());
                        break;
                    default:
                        System.out.println("ERROR! Building Facing illegal");
                        fill(primer, 7, y + 2, 1, 8, y + 3, 1, Blocks.BEDROCK.getDefaultState());
                        break;
                }
            }

            y += this.getFloorHeight();
        }

        makeRoof(primer, y);

    }

    public void makeRoof(ChunkPrimer primer, int height){

        switch (this.roofType){
            case 0:
                break;
            case 1:
                fillLayer(primer, 6, 6, 9, 9, height + 1, fillerBlock);
                break;
            case 2:
                IBlockState[] top = new IBlockState[11];
                top[0] = fillerBlock;
                for(int i = 1; i < top.length; i++) top[i] = glass;
                fillMargin(primer, 2, 2, 13, 13, height + 1, fillerBlock);
                fillWallsPattern(primer, 2, height + 2, 2, 3, top);
                fillMargin(primer, 2, 2, 13, 13, height + 5, fillerBlock);
                fillMargin(primer, 7, 7, 8, 8, height + 5, lamp);
                fillLayer(primer, 3, 3, 12, 12, height + 6, fillerBlock);
        }
    }

    @Override
    public void addDetails(World world) {

    }
}
