package com.frank142857.metropolis.util.handlers;

import com.frank142857.metropolis.Metropolis;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    @Config.RequiresMcRestart
    //Dimension IDs
    public static int METROPOLIS = 8;

    @Config.RequiresWorldRestart
    //If urban biomes generate in the Overworld
    public static boolean DO_CITIES_GENERATE = false;

    @Config.RequiresWorldRestart
    //If mtr ores generate in the Overworld
    public static boolean DO_MTR_ORES_GENERATE = false;

    //Is portal enabled
    public static boolean ENABLE_PORTAL = true;

    public static int DEATH_CHEST = 1;

    public static int BLOCK_DECAY = 1;

    public static void init(File file){

        config = new Configuration(file);
        String category;

        category = "DIM_ID";
        config.addCustomCategoryComment(category, "Set the dimension ID for dimension Metropolis, default:8");
        METROPOLIS = config.getInt("Dimension Metropolis", category, 8, 2, 255, "Dim ID for Dimension Metropolis", "set_dim_id");

        category = "BIOME";
        config.addCustomCategoryComment(category, "Decide if MTR areas generate in the Overworld, default:false");
        DO_CITIES_GENERATE = config.getBoolean("City areas generating", category, false, "MTR biomes generate in the Overworld", "do_cities_generate");

        category = "ORES";
        config.addCustomCategoryComment(category, "Decide if MTR ores generate in the Overworld, default:false");
        DO_MTR_ORES_GENERATE = config.getBoolean("Mtr ores generating", category, false, "Mtr ores generate in the Overworld", "do_mtr_ores_generate");

        category = "PORTAL";
        config.addCustomCategoryComment(category, "Decide if MTR portal is enabled, default:true");
        ENABLE_PORTAL = config.getBoolean("Enable Portal", category, true, "Enable Metropolis Portal", "enable_portal");

        category = "DEATH_CHEST";
        config.addCustomCategoryComment(category, "Set the application scope of the death chest, default:1\n0 - Disabled; \n1 - MTR only; \n2 - Universal");
        DEATH_CHEST = config.getInt("Death Chest state", category, 1, 0, 2, "Death Chest state", "death_chest");

        category = "BLOCK_DECAY";
        config.addCustomCategoryComment(category, "Set the application scope of the decaying blocks, default:1\n0 - Disabled; \n1 - MTR only; \n2 - Universal");
        BLOCK_DECAY = config.getInt("Decaying Blocks state", category, 1, 0, 2, "Decaying Blocks state", "block_decay");

        config.save();
    }

    public static void registerConfig(FMLPreInitializationEvent event){
        Metropolis.config = new File(event.getModConfigurationDirectory() + "/" + "Metropolis");
        Metropolis.config.mkdirs();
        init(new File(Metropolis.config.getPath(), "Metropolis.cfg"));
    }
}
