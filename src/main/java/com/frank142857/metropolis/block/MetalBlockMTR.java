package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.item.variants.ItemBlockVariants;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import com.frank142857.metropolis.util.interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class MetalBlockMTR extends Block implements IHasModel, IMetaName {

    private final String name = "metal_block";

    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<MetalBlockMTR.EnumType>create("variant", EnumType.class);

    public MetalBlockMTR(){
        super(Material.IRON, MapColor.IRON);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.STEEL));
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setHarvestLevel("pickaxe", 1);
        this.setHardness(6.0F);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlockVariants(this).setRegistryName(name));
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        if(worldIn.getBlockState(pos).equals(this.getStateFromMeta(2))) return 4;
        else return 0;
    }

    @Override
    public void registerModel(){
        for(int i = 0; i < EnumType.values().length; i++){
            Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), i, "metal_block_" + EnumType.values()[i].getName(), "inventory");
        }
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return 0;
    }

    public static enum EnumType implements IStringSerializable {
        STEEL(0, "steel", "steel"),
        BLUE_STEEL(1, "blue_steel", "blue_steel"),
        ARGENTUM(2, "argentum", "argentum"),
        DYNAMITE(3, "dynamite", "dynamite");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name, nameU;

        private EnumType(int meta, String name){
            this(meta, name, name);
        }

        private EnumType(int meta, String name, String nameU){
            this.meta = meta;
            this.name = name;
            this.nameU = nameU;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getMeta() {
            return this.meta;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static EnumType byMetaData(int meta){
            return META_LOOKUP[meta];
        }

        static {
            for(EnumType enumType : values()){
                META_LOOKUP[enumType.getMeta()] = enumType;
            }
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumType)state.getValue(VARIANT)).getMeta();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumType)state.getValue(VARIANT)).getMeta();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetaData(meta));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult result, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> stacks) {
        for(EnumType variant : EnumType.values()){
            stacks.add(new ItemStack(this, 1, variant.getMeta()));
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    @Override
    public String getSpecialName(ItemStack stack){
        return EnumType.values()[stack.getItemDamage()].getName();
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean isBeaconBase(IBlockAccess access, BlockPos pos1, BlockPos pos2) {
        return true;
    }
}
