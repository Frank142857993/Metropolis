package com.frank142857.metropolis.world.gen.structure.pieces;

import com.frank142857.metropolis.init.BlockInit;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockTorch;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.*;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class ComponentStructureDevHousePieces {
    public static void registerDevHousePieces(){
        MapGenStructureIO.registerStructureComponent(ComponentStructureDevHousePieces.House.class, "House");
    }

    abstract static class Feature extends StructureComponent {
        /** The size of the bounding box for this feature in the X axis */
        protected int width;
        /** The size of the bounding box for this feature in the Y axis */
        protected int height;
        /** The size of the bounding box for this feature in the Z axis */
        protected int depth;
        protected int horizontalPos = -1;

        public Feature()
        {
        }

        protected Feature(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ)
        {
            super(0);
            this.width = sizeX;
            this.height = sizeY;
            this.depth = sizeZ;
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

            if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
            {
                this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeZ - 1, y + sizeY - 1, z + sizeX - 1);
            }
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            tagCompound.setInteger("Width", this.width);
            tagCompound.setInteger("Height", this.height);
            tagCompound.setInteger("Depth", this.depth);
            tagCompound.setInteger("HPos", this.horizontalPos);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
            this.width = tagCompound.getInteger("Width");
            this.height = tagCompound.getInteger("Height");
            this.depth = tagCompound.getInteger("Depth");
            this.horizontalPos = tagCompound.getInteger("HPos");
        }

        /**
         * Calculates and offsets this structure boundingbox to average ground level
         */
        protected boolean offsetToAverageGroundLevel(World worldIn, StructureBoundingBox structurebb, int yOffset)
        {
            if (this.horizontalPos >= 0)
            {
                return true;
            }
            else
            {
                int i = 0;
                int j = 0;
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
                {
                    for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                    {
                        blockpos$mutableblockpos.setPos(l, 64, k);

                        if (structurebb.isVecInside(blockpos$mutableblockpos))
                        {
                            i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel());
                            ++j;
                        }
                    }
                }

                if (j == 0)
                {
                    return false;
                }
                else
                {
                    this.horizontalPos = i / j;
                    this.boundingBox.offset(0, this.horizontalPos - this.boundingBox.minY + yOffset, 0);
                    return true;
                }
            }
        }
    }

    public static class House extends ComponentStructureDevHousePieces.Feature {
        public House()
        {
        }

        public House(Random rand, int x, int z){
            super(rand, x, 64, z, 5, 5, 5);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
        }

        @Override
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
            if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, 0))
            {
                return false;
            }
            else {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                        0, 0, 0,
                        this.width - 1, this.height - 1, this.depth - 1,
                        BlockInit.POLISHED_FOUNDATION_STONE.getDefaultState(),
                        Blocks.AIR.getDefaultState(),
                        false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                        1, 0, 1,
                        this.width - 2, 0, this.depth - 2,
                        Blocks.COBBLESTONE.getDefaultState(),
                        Blocks.COBBLESTONE.getDefaultState(),
                        false);
                this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(),
                        0, getAverage(0, this.height), getAverage(0, this.depth),
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(),
                        this.width - 1, getAverage(0, this.height), getAverage(0, this.depth),
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(),
                        getAverage(0, this.width), getAverage(0, this.height), 0,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(),
                        getAverage(0, this.width), getAverage(0, this.height), this.depth - 1,
                        structureBoundingBoxIn);
                this.fillWithAir(worldIn, structureBoundingBoxIn,
                        getAverage(0, this.width), 1, 0,
                        getAverage(0, this.width), 2, 0);
                this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState(),
                        getAverage(0, this.width), 1, 0,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER),
                        getAverage(0, this.width), 2, 0,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.CRAFTING_TABLE.getDefaultState(),
                        1, 1, 1,
                        structureBoundingBoxIn);
            }

            for (int k = 0; k < 5; ++k)
            {
                for (int j = 0; j < 5; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, k, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, BlockInit.POLISHED_FOUNDATION_STONE.getDefaultState(), j, -1, k, structureBoundingBoxIn);
                }
            }

            return true;
        }

        public int getAverage(int a, int b){
            return (a + b) / 2;
        }
    }
}
