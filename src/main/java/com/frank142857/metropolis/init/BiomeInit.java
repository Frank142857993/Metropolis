package com.frank142857.metropolis.init;

import com.frank142857.metropolis.world.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {
    public static final Biome METROPOLIS = new BiomeMetropolis();
    public static final Biome LATTICED_REALM = new BiomeLatticedRealm();

    public static void registerBiomes(){
        initBiome(METROPOLIS, "overgrown_garden",
                BiomeManager.BiomeType.COOL,
                BiomeDictionary.Type.PLAINS
        );
        initBiome(LATTICED_REALM, "latticed_realm",
                BiomeManager.BiomeType.COOL,
                BiomeDictionary.Type.PLAINS
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
