package com.frank142857.metropolis.world.gen.structure;

import com.frank142857.metropolis.init.BiomeInit;
import com.frank142857.metropolis.world.gen.structure.pieces.ComponentStructureDevHousePieces;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MapGenDevHouse extends MapGenStructure {

    private static final List<Biome> BIOMELIST = Arrays.<Biome>asList(BiomeInit.METROPOLIS);
    /** the maximum distance between dev houses */
    private int maxDistanceBetweenHouses;
    /** the minimum distance between dev houses */
    private final int minDistanceBetweenHouses;

    public MapGenDevHouse(){
        this.maxDistanceBetweenHouses = 4;
        this.minDistanceBetweenHouses = 1;
    }

    public MapGenDevHouse(Map<String, String> map){
        this();
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            if (((String)entry.getKey()).equals("distance"))
            {
                this.maxDistanceBetweenHouses = MathHelper.getInt(entry.getValue(), this.maxDistanceBetweenHouses, 2);
            }
        }
    }

    @Override
    public String getStructureName() {
        return "DevHouse";
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, this.maxDistanceBetweenHouses, 1, 14357617, false, 100, findUnexplored);
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.maxDistanceBetweenHouses - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.maxDistanceBetweenHouses - 1;
        }

        int k = chunkX / this.maxDistanceBetweenHouses;
        int l = chunkZ / this.maxDistanceBetweenHouses;
        Random random = this.world.setRandomSeed(k, l, 14357617);
        k = k * this.maxDistanceBetweenHouses;
        l = l * this.maxDistanceBetweenHouses;
        k = k + random.nextInt(this.maxDistanceBetweenHouses - 1);
        l = l + random.nextInt(this.maxDistanceBetweenHouses - 1);

        if (i == k && j == l)
        {
            Biome biome = this.world.getBiomeProvider().getBiome(new BlockPos(i * 16 + 4, 0, j * 16 + 4));

            if (biome == null)
            {
                return false;
            }

            for (Biome biome1 : BIOMELIST)
            {
                if (biome == biome1)
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new MapGenDevHouse.Start(this.world, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        public Start()
        {
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);

            ComponentStructureDevHousePieces.House house = new ComponentStructureDevHousePieces.House(random, chunkX * 16, chunkZ * 16);
            this.components.add(house);

            this.updateBoundingBox();
        }
    }
}
