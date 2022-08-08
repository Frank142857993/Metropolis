package com.frank142857.metropolis.util.handlers;

import com.frank142857.metropolis.Metropolis;
import net.minecraft.client.audio.Sound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
    public static SoundEvent BLOCK_MTR_PORTAL_AMBIENT;
    public static SoundEvent BLOCK_MTR_PORTAL_AMBIENT_2;

    public static void registerSounds(){
        BLOCK_MTR_PORTAL_AMBIENT = registerSound("block.mtr_portal.ambient");
        BLOCK_MTR_PORTAL_AMBIENT_2 = registerSound("block.mtr_portal.ambient2");
    }

    private static SoundEvent registerSound(String name){
        ResourceLocation location = new ResourceLocation(Metropolis.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
