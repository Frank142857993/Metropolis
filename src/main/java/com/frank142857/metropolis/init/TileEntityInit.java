package com.frank142857.metropolis.init;

import com.frank142857.metropolis.tileentity.TileEntityFlowerPotMTR;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityInit {
    public static void register(){
        GameRegistry.registerTileEntity(TileEntityFlowerPotMTR.class, "metropolis:evergrowth_flower_pot");
    }
}
