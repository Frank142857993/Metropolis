package com.frank142857.metropolis.init;

import com.frank142857.metropolis.world.biomes.BiomeCityOfPresent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {
    public static final Biome CITY_OF_PRESENT = new BiomeCityOfPresent();

    public static void registerBiomes(){
        initBiome(CITY_OF_PRESENT, "City of Present", BiomeManager.BiomeType.WARM, BiomeDictionary.Type.PLAINS);
    }

    private static Biome initBiome(Biome biome, String name, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types){
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        System.out.println("Biome City of Present registered");
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);

        System.out.println("Biome City of Present added");
        return biome;
    }
}
