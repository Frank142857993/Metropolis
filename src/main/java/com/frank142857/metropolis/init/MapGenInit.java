package com.frank142857.metropolis.init;

import com.frank142857.metropolis.world.gen.structure.MapGenDevHouse;
import com.frank142857.metropolis.world.gen.structure.pieces.*;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class MapGenInit {
    public static void registerMapGenPieces(){
        MapGenStructureIO.registerStructure(MapGenDevHouse.Start.class, "DevHouse");
        ComponentStructureDevHousePieces.registerDevHousePieces();
    }
}
