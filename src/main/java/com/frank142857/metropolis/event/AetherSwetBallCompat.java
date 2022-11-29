package com.frank142857.metropolis.event;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.BlockInit;
import com.gildedgames.the_aether.items.*;
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
public class AetherSwetBallCompat {

    public AetherSwetBallCompat(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onItemUse(PlayerInteractEvent.RightClickBlock event){
        if(Loader.isModLoaded("aether_legacy")) {
            EntityPlayer player = event.getEntityPlayer();
            ItemStack stack = event.getItemStack();
            if(!event.getWorld().isRemote){
                World worldIn = event.getWorld();
                if(!stack.equals(ItemStack.EMPTY) && stack.getItem().equals(ItemsAether.swetty_ball)){
                    BlockPos pos = event.getPos();
                    if(worldIn.getBlockState(pos).equals(BlockInit.HEAVY_DIRT.getDefaultState())){
                        worldIn.setBlockState(pos, BlockInit.SURFACE_GRASS.getDefaultState());
                        if(!player.capabilities.isCreativeMode) stack.shrink(1);
                        event.setCancellationResult(EnumActionResult.SUCCESS);
                    }
                }
            }
            event.setCancellationResult(EnumActionResult.FAIL);
        }

    }
}
