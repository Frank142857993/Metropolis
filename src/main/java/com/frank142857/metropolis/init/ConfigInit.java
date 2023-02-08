package com.frank142857.metropolis.init;

import com.frank142857.metropolis.Metropolis;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ConfigInit {
    public static Configuration config;

    //Dimension IDs
    @Config.RequiresWorldRestart
    public static int METROPOLIS = 8;

    //Width & length of city blocks
    @Config.RequiresWorldRestart
    public static int NEIGHBORHOOD_WIDTH = 4;

    @Config.RequiresWorldRestart
    public static int RIVER_DISTRIBUTION = 4;

    //Is portal enabled
    public static boolean PORTAL_ENABLED = true;

    //Set MTR dimension fog color into 0xcccccc
    public static boolean USING_FOG_COLOR = true;

    public static int DEATH_CHEST = 1;

    public static int BLOCK_DECAY = 1;

    public static void init(File file){

        config = new Configuration(file);
        String category = "general";

        config.addCustomCategoryComment(category, "Set the dimension ID for dimension Metropolis, default:8");
        METROPOLIS = config.getInt("Dimension Metropolis", category, 8, 2, 255, "Dim ID for Dimension Metropolis", "set_dim_id");

        config.addCustomCategoryComment(category, "Set the width & length for neighborhoods, min: 2, max: 16, default: 4");
        NEIGHBORHOOD_WIDTH = config.getInt("Neighborhood width & length", category, 4, 2, 16, "Neighborhood width", "neighborhood");

        config.addCustomCategoryComment(category, "Set the distribution of river, min: 2, max: 8, default: 4");
        RIVER_DISTRIBUTION = config.getInt("River distribution", category, 4, 2, 8, "River network", "river");

        config.addCustomCategoryComment(category, "Decide if MTR portal is enabled, default:true");
        PORTAL_ENABLED = config.getBoolean("Enable Portal", category, true, "Enable Metropolis Portal", "enable_portal");

        config.addCustomCategoryComment(category, "Decide if dimension sky & fog color is applied, default:true");
        USING_FOG_COLOR = config.getBoolean("Using dimension sky & fog color", category, true, "Stylize MTR dimension sky & fog color", "fog_color");

        config.addCustomCategoryComment(category, "Set the application scope of the death chest, default:1\n0 - Disabled; \n1 - MTR only; \n2 - Universal");
        DEATH_CHEST = config.getInt("Death Chest state", category, 1, 0, 2, "Death Chest state", "death_chest");

        config.addCustomCategoryComment(category, "Set the application scope of the decaying blocks, default:1\n0 - Disabled; \n1 - MTR only; \n2 - Universal");
        BLOCK_DECAY = config.getInt("Decaying Blocks state", category, 1, 0, 2, "Decaying Blocks state", "block_decay");

        config.setCategoryComment(category, "General configurations");
        config.save();
    }

    public static void registerConfig(FMLPreInitializationEvent event){
        Metropolis.config = new File(event.getModConfigurationDirectory() + "/" + "Metropolis");
        Metropolis.config.mkdirs();
        init(new File(Metropolis.config.getPath(), "Metropolis.cfg"));
    }
}
