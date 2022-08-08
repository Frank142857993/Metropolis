package com.frank142857.metropolis;

import com.frank142857.metropolis.proxy.CommonProxy;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(
        modid = Metropolis.MODID,
        name = Metropolis.NAME,
        version = Metropolis.VERSION,
        acceptedMinecraftVersions = Metropolis.ACCEPTED_MINECRAFT_VERSIONS,
        dependencies = Metropolis.DEPENDENCIES)

public class Metropolis
{
    public static final String MODID = "metropolis";
    public static final String NAME = "Metropolis";
    public static final String VERSION = "0.0.1";
    public static final String ACCEPTED_MINECRAFT_VERSIONS = "[1.12,1.13)";
    public static final String DEPENDENCIES = "";

    public static File config;

    private static Logger logger;

    @SidedProxy(clientSide = "com.frank142857.metropolis.proxy.ClientProxy", serverSide = "com.frank142857.metropolis.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("MTR mod added");
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    public void sop(Object obj){
        System.out.println(obj);
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }
}
