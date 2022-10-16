package com.frank142857.metropolis.client.gui.handlers;

import com.frank142857.metropolis.client.gui.GuiPortal;
import net.minecraft.util.Timer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class GuiOverlayHandler {

    public static GuiPortal portal = new GuiPortal();

    @SubscribeEvent
    public static void onRenderGui(final RenderGameOverlayEvent.Pre event){
        if(event.getType() == RenderGameOverlayEvent.ElementType.ALL){
            portal.renderGameOverlay(new Timer(20.0F).renderPartialTicks);
        }
    }
}
