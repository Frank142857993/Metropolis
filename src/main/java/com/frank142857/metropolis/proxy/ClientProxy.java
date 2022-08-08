package com.frank142857.metropolis.proxy;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.event.*;
import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.util.interfaces.IPlantColor;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
        //TODO PlayerChangeDimension 引发服务器错误
        MinecraftForge.EVENT_BUS.register(new PlayerChangeDimension());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        for (Block block : BlockInit.REGISTER_BLOCKS) {
            if (block instanceof IPlantColor) {
                final IPlantColor i = (IPlantColor) block;
                if (Item.getItemFromBlock(block) != Items.AIR) {
                    Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> i.getColorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex), block);
                }
                Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(i::getColorMultiplier, block);
            }
        }

        super.postInit(event);
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String fileName, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Metropolis.MODID, fileName), id));
    }
}
