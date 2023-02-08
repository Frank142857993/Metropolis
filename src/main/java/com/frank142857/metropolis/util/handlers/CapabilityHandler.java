package com.frank142857.metropolis.util.handlers;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.CapabilityInit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {
    public static final ResourceLocation PORTAL = new ResourceLocation(Metropolis.MODID, "portal");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event){
        //if(!(event.getObject() instanceof EntityPlayerMP)) return;
        event.addCapability(PORTAL, new CapabilityInit());
    }
}
