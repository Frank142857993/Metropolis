package com.frank142857.metropolis.item;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemCloudBottle extends Item implements IHasModel {

    private final String name = "cloud_bottle";

    public ItemCloudBottle(){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        ItemInit.REGISTER_ITEMS.add(this);
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(this, 0, "inventory");
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {

        ItemStack stack = player.getHeldItem(hand);

        RayTraceResult raytraceresult = this.rayTrace(worldIn, player, false);

        if (raytraceresult == null) {
            return new ActionResult(EnumActionResult.PASS, stack);
        } else {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {

                BlockPos pos = raytraceresult.getBlockPos();

                if (!worldIn.isBlockModifiable(player, pos) || !player.canPlayerEdit(pos.offset(raytraceresult.sideHit), raytraceresult.sideHit, stack))
                {
                    return new ActionResult(EnumActionResult.PASS, stack);
                }

                if (!(raytraceresult.sideHit == EnumFacing.UP)){
                    return new ActionResult(EnumActionResult.PASS, stack);
                }

                int x = pos.getX(), y = pos.getY(), z = pos.getZ();

                IBlockState location = worldIn.getBlockState(new BlockPos(x, y, z));

                if (!(location == Blocks.AIR.getDefaultState())) {
                    if(worldIn.getBlockState(new BlockPos(x, y + 1, z)).getBlock().isReplaceable(worldIn, new BlockPos(x, y + 1, z))){
                        worldIn.setBlockState(new BlockPos(x, y + 1, z), BlockInit.CLOUD.getDefaultState());
                        return new ActionResult(EnumActionResult.SUCCESS, this.turnBottleIntoItem(player.getHeldItem(hand), player, new ItemStack(Items.GLASS_BOTTLE)));
                    }
                }

            }
            return new ActionResult(EnumActionResult.PASS, stack);

        }
    }

    protected ItemStack turnBottleIntoItem(ItemStack stackIn, EntityPlayer player, ItemStack stack)
    {
        stackIn.shrink(1);

        if (stackIn.isEmpty())
        {
            return stack;
        }
        else
        {
            if (!player.inventory.addItemStackToInventory(stack))
            {
                player.dropItem(stack, false);
            }

            return stackIn;
        }
    }
}
