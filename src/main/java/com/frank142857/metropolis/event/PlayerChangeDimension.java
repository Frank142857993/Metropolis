package com.frank142857.metropolis.event;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.client.GuiWorldLoading;
import com.frank142857.metropolis.init.DimensionInit;
import com.frank142857.metropolis.world.city.Building;
import com.frank142857.metropolis.world.city.BuildingType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Metropolis.MODID)
public class PlayerChangeDimension {
    private final Minecraft client = Minecraft.getMinecraft();
    private int lastDimension = 0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.phase == TickEvent.Phase.END && event.player == client.player){
            lastDimension = event.player.dimension;
        }
    }

    @SubscribeEvent
    public void onOpenGUI(GuiOpenEvent event){
        if(event.getGui() instanceof GuiDownloadTerrain && client.player != null){
            int dimID = DimensionInit.metropolis.getId();
            if(client.player.dimension == dimID || lastDimension == dimID){
                GuiWorldLoading gui = new GuiWorldLoading();
                gui.setState(client.player.dimension == dimID);
                event.setGui(gui);
                System.out.println("Gui displayed");//debug
            }
        }
    }
}
