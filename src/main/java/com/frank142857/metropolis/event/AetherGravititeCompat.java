package com.frank142857.metropolis.event;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.block.BlockSlabMTR;
import com.frank142857.metropolis.init.BlockInit;
import com.gildedgames.the_aether.items.tools.*;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Metropolis.MODID)
public class AetherGravititeCompat {
    //Makes gravitite tools unable for pavings

    public AetherGravititeCompat(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onItemUse(PlayerInteractEvent.RightClickBlock event){
        if(Loader.isModLoaded("aether_legacy")) {
            EntityPlayer player = event.getEntityPlayer();
            ItemStack stack = event.getItemStack();
            if(!event.getWorld().isRemote){
                World worldIn = event.getWorld();
                if(!stack.equals(ItemStack.EMPTY) && stack.getItem() instanceof ItemGravititeTool){
                    BlockPos pos = event.getPos();
                    if(worldIn.getBlockState(pos).equals(BlockInit.STONE_PAVING.getDefaultState())
                            || worldIn.getBlockState(pos).equals(BlockInit.STONE_PAVING_SLAB_DOUBLE.getDefaultState())
                            || worldIn.getBlockState(pos).equals(BlockInit.STONE_PAVING_SLAB_HALF.getDefaultState())
                            || worldIn.getBlockState(pos).equals(BlockInit.STONE_PAVING_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF,BlockSlab.EnumBlockHalf.BOTTOM))
                            ){
                        event.setCanceled(true);
                    }
                }
            }
        }

    }
}
