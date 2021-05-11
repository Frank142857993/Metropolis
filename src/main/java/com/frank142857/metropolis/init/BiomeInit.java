package com.frank142857.metropolis.init;

import com.frank142857.metropolis.world.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {
    public static final Biome CITY_OF_PRESENT = new BiomeCityOfPresent();
    public static final Biome LAND_OF_PAST = new BiomeLandOfPast();
    public static final Biome FOREST_OF_CRANE = new BiomeForestOfCrane();
    public static final Biome OVERGROUND_GARDEN = new BiomeOvergroundGarden();
    public static final Biome WHITE_DESERT = new BiomeWhiteDesert();
    public static final Biome LAKE_OF_SILENCE = new BiomeLakeOfSilence();

    public static void registerBiomes(){
        initBiome(CITY_OF_PRESENT, "City of Present",
                BiomeManager.BiomeType.WARM,
                BiomeDictionary.Type.HOT,
                BiomeDictionary.Type.PLAINS
        );
        initBiome(LAND_OF_PAST, "Land of Past",
                BiomeManager.BiomeType.WARM,
                BiomeDictionary.Type.PLAINS
        );
        initBiome(FOREST_OF_CRANE, "Forest of Crane",
                BiomeManager.BiomeType.WARM,
                BiomeDictionary.Type.PLAINS,
                BiomeDictionary.Type.RARE
        );
        initBiome(OVERGROUND_GARDEN, "Overground Garden",
                BiomeManager.BiomeType.WARM,
                BiomeDictionary.Type.DENSE,
                BiomeDictionary.Type.FOREST,
                BiomeDictionary.Type.RARE
        );
        initBiome(WHITE_DESERT, "White Desert",
                BiomeManager.BiomeType.ICY,
                BiomeDictionary.Type.DRY,
                BiomeDictionary.Type.SANDY
        );
        initBiome(LAKE_OF_SILENCE, "Lake Of Silence",
                BiomeManager.BiomeType.COOL,
                BiomeDictionary.Type.OCEAN
        );
    }

    private static Biome initBiome(Biome biome, String name, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types){
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);

        return biome;
    }
}
