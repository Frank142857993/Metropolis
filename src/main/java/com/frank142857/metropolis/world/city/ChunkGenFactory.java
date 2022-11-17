package com.frank142857.metropolis.world.city;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkGenFactory {

    public static void fill(ChunkPrimer primer, int x0, int y0, int z0, int xt, int yt, int zt, IBlockState block){
        for(int y = y0; y <= yt; y++){
            fillLayer(primer, x0, z0, xt, zt, y, block);
        }
    }

    public static void fillLayer(ChunkPrimer primer, int x0, int z0, int xt, int zt, int y, IBlockState block){
        for(int x = x0; x <= xt; x++){
            for(int z = z0; z <= zt; z++){
                primer.setBlockState(x, y, z, block);
            }
        }
    }

    public static void fillMargin(ChunkPrimer primer, int x0, int z0, int xt, int zt, int y, IBlockState block){
        for(int x = x0; x <= xt; x++){
            for(int z = z0; z <= zt; z++){
                if ((x == x0 || x == xt) || (z == z0 || z == zt)) {
                    primer.setBlockState(x, y, z, block);
                }
            }
        }
    }

    public static void fillWalls(ChunkPrimer primer, int x0, int y0, int z0, int xt, int yt, int zt, IBlockState block){
        for(int y = y0; y <= yt; y++){
            fillMargin(primer, x0, z0, xt, zt, y, block);
        }
    }

    public static void fillMarginPattern(ChunkPrimer primer, int x0, int y0, int z0, IBlockState[] sequence){
        int x = x0, y = y0, z = z0;
        int j = 1;
        for (int i1 = 0; i1 < 4 * sequence.length; i1++){
            if (i1 >= 2 * sequence.length) j = -1;
            primer.setBlockState(x, y, z, sequence[i1 % sequence.length]);

            if (i1 % (2 * sequence.length) < sequence.length){
                z += j;
            } else {
                x += j;
            }
        }
    }
}
