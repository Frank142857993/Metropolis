package com.frank142857.metropolis.item;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.init.CreativeTabInit;
import com.frank142857.metropolis.init.ItemInit;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSilverWoodStick extends Item implements IHasModel {

    private final String name = "silver_wood_stick";

    public ItemSilverWoodStick(){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabInit.TAB_METROPOLIS);
        ItemInit.REGISTER_ITEMS.add(this);
    }

    @Override
    public void registerModel(){
        Metropolis.proxy.registerItemRenderer(this, 0, "inventory");
    }

    /*
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        int side = facing.getIndex();

        switch(side) {
            case 0:
            default:
                y--;
                break;
            case 1:
                y++;
                break;
            case 2:
                z--;
                break;
            case 3:
                z++;
                break;
            case 4:
                x--;
                break;
            case 5:
                x++;
                break;
        }

        if(!player.canPlayerEdit(new BlockPos(x, y, z), facing, player.getHeldItem(hand))) {
            return EnumActionResult.FAIL;
        }

        IBlockState location = worldIn.getBlockState(new BlockPos(x, y, z));

        if(location == Blocks.AIR.getDefaultState()) {
            if(false){
                //TODO add stuff: advancement check, replacing "false"
                BlockInit.BLOCK_MTR_PORTAL.trySpawnPortal(worldIn, new BlockPos(x, y, z), false);
            } else {
                if (!BlockInit.BLOCK_MTR_PORTAL.trySpawnPortal(worldIn, new BlockPos(x, y, z), true)){
                    player.sendStatusMessage(new TextComponentTranslation("item.teleportation_rod.denied", new Object[0]), true);
                }
            }
        }

        //TODO add more teleportation function to Teleportation Rod
        player.getHeldItem(hand).shrink(1);
        return EnumActionResult.SUCCESS;

    }*/

    /*
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("Achievement not completed");
    }
    */
}
