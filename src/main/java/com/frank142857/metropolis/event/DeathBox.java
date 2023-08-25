package com.frank142857.metropolis.event;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.ConfigInit;
import com.frank142857.metropolis.init.DimensionInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Metropolis.MODID)
public class DeathBox {

    public DeathBox(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void onPlayerDead(LivingDeathEvent event){
        Entity entity = event.getEntity();
        World worldIn = entity.getEntityWorld();
        BlockPos deadPos = new BlockPos(entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ());
        ItemStack[] playerStacks = new ItemStack[36], chestStacks = new ItemStack[27];

        for(int i = 0; i < chestStacks.length; i++){
            chestStacks[i] = ItemStack.EMPTY;
        }

        boolean b1 = false;
        int size;

        switch(ConfigInit.DEATH_CHEST){
            case 0:
                break;
            case 2:
                b1 = true;
                break;
            default:
                b1 = worldIn.provider.getDimension() == DimensionInit.metropolis.getId();
                break;
        }

        if (worldIn.getGameRules().getBoolean("keepInventory")) {
            b1 = false;
        }

        if (entity instanceof EntityPlayer){

            if (entity.getName().equals("Frank142857") || entity.getName().equals("frank142857")){
                EntityItem item = new EntityItem(worldIn, deadPos.getX(), deadPos.getY(), deadPos.getZ(), ItemStack.EMPTY);
                Item hoe = Items.DIAMOND_HOE;
                hoe.setMaxDamage(1);
                ((EntityItem) item).setItem(new ItemStack(hoe));
                if(!worldIn.isRemote) worldIn.spawnEntity(item);
            }

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

                    //size = worldIn.getGameRules().getBoolean("keepInventory") ? playerStacks.length : 9;
                    size = playerStacks.length;

                    for(int i1 = 0, i2 = 0; i1 < size; i1++){
                        playerStacks[i1] = ((EntityPlayer) entity).inventory.getStackInSlot(i1);
                        if (!playerStacks[i1].isEmpty() && i2 < chestStacks.length){
                            chestStacks[i2] = playerStacks[i1];
                            ((EntityPlayer) entity).inventory.setInventorySlotContents(i1, ItemStack.EMPTY);
                            i2++;
                        }
                    }

                    ((EntityPlayer) entity).inventory.dropAllItems();


                    for(int slot = 0; slot < ((TileEntityChest) chest).getSizeInventory(); slot++){
                        ((TileEntityChest) chest).setInventorySlotContents(slot, chestStacks[slot]);
                    }
                }
            }
        }
    }
}
