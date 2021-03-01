package com.frank142857.metropolis.block;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSlimyBrick extends Block implements IHasModel {
    protected static final AxisAlignedBB SLIMY_BRICK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

    private final String name = "slimy_brick";

    public BlockSlimyBrick(){
        super(Material.ROCK, MapColor.GREEN_STAINED_HARDENED_CLAY);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(3.0F);
        BlockInit.REGISTER_BLOCKS.add(this);
        ItemInit.REGISTER_ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModel() {
        Metropolis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState p_getCollisionBoundingBox_1_, IBlockAccess p_getCollisionBoundingBox_2_, BlockPos p_getCollisionBoundingBox_3_) {
        return SLIMY_BRICK_AABB;
    }

    public void onEntityCollidedWithBlock(World p_onEntityCollidedWithBlock_1_, BlockPos p_onEntityCollidedWithBlock_2_, IBlockState p_onEntityCollidedWithBlock_3_, Entity p_onEntityCollidedWithBlock_4_) {
        p_onEntityCollidedWithBlock_4_.motionX *= 0.6D;
        p_onEntityCollidedWithBlock_4_.motionZ *= 0.6D;
    }
}
