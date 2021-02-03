package com.frank142857.metropolis.util.handlers;

import com.frank142857.metropolis.block.teleporter.BlockMtrPortal;
import com.frank142857.metropolis.init.*;
import com.frank142857.metropolis.util.interfaces.IHasModel;
import com.frank142857.metropolis.world.gen.WorldGenMtrOres;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegistry(Register<Item> event){
        event.getRegistry().register(new ItemBlock(BlockInit.BLOCK_MTR_PORTAL).setRegistryName(BlockMtrPortal.name));
        event.getRegistry().registerAll(ItemInit.REGISTER_ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegistry(Register<Block> event){
        event.getRegistry().registerAll(BlockInit.REGISTER_BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event){
        for(Item item : ItemInit.REGISTER_ITEMS){
            if(item instanceof IHasModel){
                ((IHasModel)item).registerModel();
            }
        }

        for(Block block : BlockInit.REGISTER_BLOCKS){
            if(block instanceof IHasModel){
                ((IHasModel) block).registerModel();
            }
        }
    }

    public static void addSmelting(Item input, Item output, float xp){
        addSmelting(input, new ItemStack(output), xp);
    }

    public static void addSmelting(Item input, ItemStack output, float xp){
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void registerSmeltingRecipe(){
        addSmelting(Item.getItemFromBlock(BlockInit.ORE_ARGENTUM), new ItemStack(ItemInit.INGOTS, 1, 3), 0.7F);
        addSmelting(Item.getItemFromBlock(BlockInit.ORE_MTR_QUARTZ), Items.QUARTZ, 0.2F);
        //addSmelting(Items.IRON_INGOT, ItemInit.NUGGET_STEEL, 0.15F);
    }

    public static void worldGenRegistries(){
        GameRegistry.registerWorldGenerator(new WorldGenMtrOres(), 0);
    }
}