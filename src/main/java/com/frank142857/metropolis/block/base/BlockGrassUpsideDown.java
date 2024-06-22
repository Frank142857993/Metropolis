package com.frank142857.metropolis.block.base;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import com.frank142857.metropolis.util.interfaces.IPlantColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockGrassUpsideDown extends Block implements IHasModel, IPlantColor
        //, IGrowable
{
    private final String name = "upside_down_surface_grass";

    public BlockGrassUpsideDown(){
        super(Material.GRASS, MapColor.GRAY);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setTickRandomly(true);
        this.setSoundType(SoundType.STONE);
        this.setHardness(1.0F);
        this.setHarvestLevel("shovel", 1);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel() {
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    /*
    public void updateTick(World world, BlockPos p_updateTick_2_, IBlockState p_updateTick_3_, Random p_updateTick_4_) {
        if (!world.isRemote) {
            if (!world.isAreaLoaded(p_updateTick_2_, 3)) {
                return;
            }

            if (world.getLightFromNeighbors(p_updateTick_2_.up()) < 4 && world.getBlockState(p_updateTick_2_.up()).getLightOpacity(world, p_updateTick_2_.up()) > 2) {
                world.setBlockState(p_updateTick_2_, BlockInit.HEAVY_DIRT.getDefaultState());
            } else if (world.getLightFromNeighbors(p_updateTick_2_.up()) >= 9) {
                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = p_updateTick_2_.add(p_updateTick_4_.nextInt(3) - 1, p_updateTick_4_.nextInt(5) - 3, p_updateTick_4_.nextInt(3) - 1);
                    if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !world.isBlockLoaded(blockpos)) {
                        return;
                    }

                    IBlockState iblockstate = world.getBlockState(blockpos.up());
                    IBlockState iblockstate1 = world.getBlockState(blockpos);
                    if (iblockstate1.getBlock() == BlockInit.HEAVY_DIRT && world.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity(world, p_updateTick_2_.up()) <= 2) {
                        world.setBlockState(blockpos, BlockInit.SURFACE_GRASS.getDefaultState());
                    }
                }
            }
        }
    }*/

    public Item getItemDropped(IBlockState state, Random rand, int chance) {
        return BlockInit.HEAVY_DIRT.getItemDropped(BlockInit.HEAVY_DIRT.getDefaultState(), rand, chance);
    }

    /*
    @Override
    public boolean canGrow(World world, BlockPos blockPos, IBlockState iBlockState, boolean b) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, IBlockState iBlockState) {
        return true;
    }

    @Override
    public void grow(World world, Random random, BlockPos blockPos, IBlockState iBlockState) {
        BlockPos blockpos = blockPos.up();

        label:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (world.getBlockState(blockpos1.down()).getBlock() != BlockInit.SURFACE_GRASS || world.getBlockState(blockpos1).isNormalCube()) {
                    continue label;
                }
            }

            if (world.isAirBlock(blockpos1)) {
                if (random.nextInt(8) == 0) {
                    world.getBiome(blockpos1).plantFlower(world, random, blockpos1);
                } else {
                    IBlockState iblockstate1 = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);
                    if (Blocks.TALLGRASS.canBlockStay(world, blockpos1, iblockstate1)) {
                        world.setBlockState(blockpos1, iblockstate1, 3);
                    }
                }
            }
        }
    }*/

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public int getColorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
    }

    /*
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable) {
        if(super.canSustainPlant(state, world, pos, direction, plantable)) {
            return true;
        }

        EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch(plantType) {
            case Beach:
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return hasWater;
            case Plains:
                return true;
            default:
                return false;
        }
    }*/
}
