package com.frank142857.metropolis.util.handlers;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.DimensionInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Metropolis.MODID)
public class EventHandler {

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void onPlayerDead(LivingDeathEvent event){
        Entity entity = event.getEntity();
        World worldIn = entity.getEntityWorld();
        BlockPos deadPos = new BlockPos(entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ());
        ItemStack[] playerStacks = new ItemStack[36], chestStacks = new ItemStack[36];

        for(int i = 0; i < chestStacks.length; i++){
            chestStacks[i] = ItemStack.EMPTY;
        }

        boolean b1 = false;
        int size;

        switch(ConfigHandler.DEATH_CHEST){
            case 0:
                break;
            case 1:
                b1 = worldIn.provider.getDimension() == DimensionInit.metropolis.getId();
                break;
            case 2:
                b1 = true;
                break;
        }

        if (entity instanceof EntityPlayer){
            if(b1){

                worldIn.setBlockState(deadPos, Blocks.CHEST.getDefaultState());

                TileEntity chest = worldIn.getTileEntity(deadPos);

                if (chest instanceof TileEntityChest){

                    //debug
                    System.out.println("Chest detected");

                    if(entity.getName().endsWith("s") || entity.getName().endsWith("S")){
                        ((TileEntityChest) chest).setCustomName(entity.getName() + "' belongings");
                    } else {
                        ((TileEntityChest) chest).setCustomName(entity.getName() + "'s belongings");
                    }

                    size = worldIn.getGameRules().getBoolean("keepInventory") ? playerStacks.length : 9;

                    for(int i1 = 0, i2 = 0; i1 < size; i1++){
                        playerStacks[i1] = ((EntityPlayer) entity).inventory.getStackInSlot(i1);
                        if (!playerStacks[i1].isEmpty()){
                            chestStacks[i2] = playerStacks[i1];
                            i2++;
                        }
                    }

                    ((EntityPlayer) entity).inventory.clear();


                    for(int slot = 0; slot < ((TileEntityChest) chest).getSizeInventory(); slot++){
                        ((TileEntityChest) chest).setInventorySlotContents(slot, chestStacks[slot]);
                    }
                }
            }
        }
    }
}
