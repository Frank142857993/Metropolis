package com.frank142857.metropolis.util.handlers;

import com.frank142857.metropolis.Metropolis;
import com.frank142857.metropolis.init.ConfigInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Metropolis.MODID)
public class ConfigHandler {

    public ConfigHandler(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void onSaveConfigs(ConfigChangedEvent.OnConfigChangedEvent event){
        System.out.println("Configs have changed");
        if(event.getModID().equals(Metropolis.MODID)){
            ConfigInit.config.save();
            ConfigInit.init(ConfigInit.config.getConfigFile());
        }
    }
}
