package com.frank142857.metropolis.block;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public abstract class BlockSlabMTR extends BlockSlab {
    static Block half;
    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.<Variant>create("variant",Variant.class);

    public BlockSlabMTR(String name, Material material, BlockSlab half){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.useNeighborBrightness = !this.isDouble();

        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
        if(!this.isDouble()) state = state.withProperty(HALF,EnumBlockHalf.BOTTOM);
        this.half = half;
        BlockInit.REGISTER_BLOCKS.add(this);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        if (this == BlockInit.FOUNDATION_STONE_SLAB_DOUBLE || this == BlockInit.FOUNDATION_STONE_SLAB_HALF) return Item.getItemFromBlock(BlockInit.FOUNDATION_STONE_SLAB_HALF);
        return Item.getItemFromBlock(half);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state){
        return new ItemStack(half);
    }

    public IBlockState getStateFromMeta(int meta){
        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
        if(!this.isDouble()) state = state.withProperty(HALF, ((meta & 8) != 0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
        return state;
    }

    public int getMetaFromState(IBlockState state){
        int meta = 0;
        if(!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) meta |= 8;
        return meta;
    }

    protected BlockStateContainer createBlockState(){
        if(!this.isDouble()) return new BlockStateContainer(this, new IProperty[] {VARIANT, HALF});
        else return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

    public String getUnlocalizedName(int meta){
        return super.getUnlocalizedName();
    }

    public IProperty<?> getVariantProperty(){
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack){
        return Variant.DEFAULT;
    }

    public static enum Variant implements IStringSerializable{
        DEFAULT;
        public String getName(){
            return "default";
        }
    }
}
