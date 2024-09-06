package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPillarMTR extends BlockRotatedPillar implements IHasModel {

    public static final PropertyEnum<BlockPillarMTR.EnumAxis> AXIS = PropertyEnum.<BlockPillarMTR.EnumAxis>create("axis", BlockPillarMTR.EnumAxis.class);

    public BlockPillarMTR(String name, Material material, MapColor mapColor, String harvestTool, int harvestLevel, float hardness, float lightValue){
        super(material, mapColor);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setHardness(hardness);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, BlockPillarMTR.EnumAxis.Y));
        //this.setSoundType(SoundType.STONE);
        this.setHarvestLevel(harvestTool, harvestLevel);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState();

        switch (meta & 12)
        {
            case 0:
                state = state.withProperty(AXIS, BlockPillarMTR.EnumAxis.Y);
                break;
            case 4:
                state = state.withProperty(AXIS, BlockPillarMTR.EnumAxis.X);
                break;
            case 8:
                state = state.withProperty(AXIS, BlockPillarMTR.EnumAxis.Z);
                break;
            default:
                state = state.withProperty(AXIS, BlockPillarMTR.EnumAxis.NONE);
        }

        return state;
    }

    @Override
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int meta = 0;

        switch (state.getValue(AXIS))
        {
            case X:
                meta |= 4;
                break;
            case Z:
                meta |= 8;
                break;
            case NONE:
                meta |= 12;
        }

        return meta;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AXIS});
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getStateFromMeta(meta).withProperty(AXIS, BlockPillarMTR.EnumAxis.fromFacingAxis(facing.getAxis()));
    }

    public static enum EnumAxis implements IStringSerializable
    {
        X("x"),
        Y("y"),
        Z("z"),
        NONE("none");

        private final String name;

        private EnumAxis(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockPillarMTR.EnumAxis fromFacingAxis(EnumFacing.Axis axis)
        {
            switch (axis)
            {
                case X:
                    return X;
                case Y:
                    return Y;
                case Z:
                    return Z;
                default:
                    return NONE;
            }
        }

        public String getName()
        {
            return this.name;
        }
    }

}
