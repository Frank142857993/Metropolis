package com.frank142857.metropolis.world.dimension.metropolis;

import com.frank142857.metropolis.init.BiomeInit;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesMetropolis extends GenLayer {

    private Biome[] allowedBiomes = {
            BiomeInit.METROPOLIS,
            BiomeInit.LATTICED_REALM
            //Biomes.PLAINS //TODO More modified biomes
    };

    public GenLayerBiomesMetropolis(long seed)
    {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth)
    {
        int[] dest = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; dz++)
        {
            for (int dx = 0; dx < width; dx++)
            {
                this.initChunkSeed(dx + x, dz + z);
                dest[(dx + dz * width)] = Biome.getIdForBiome(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
            }
        }
        return dest;
    }
}