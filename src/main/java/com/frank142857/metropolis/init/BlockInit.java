package com.frank142857.metropolis.init;

import com.frank142857.metropolis.block.*;
import com.frank142857.metropolis.block.base.BlockDirtMTR;
import com.frank142857.metropolis.block.base.BlockGrassMTR;
import com.frank142857.metropolis.block.base.BlockStoneMTR;
import com.frank142857.metropolis.block.fluids.*;
import com.frank142857.metropolis.block.ores.*;
import com.frank142857.metropolis.block.teleporter.BlockMtrPortal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> REGISTER_BLOCKS = new ArrayList<Block>();

    public static final BlockMtrPortal BLOCK_MTR_PORTAL = new BlockMtrPortal();

    public static final BlockGrassMTR SURFACE_GRASS = new BlockGrassMTR();
    public static final BlockDirtMTR HEAVY_DIRT = new BlockDirtMTR();
    public static final BlockStoneMTR HARDENED_STONE = new BlockStoneMTR();
    public static final OresMTR ORES_MTR = new OresMTR();
    public static final OreMtrQuartz ORE_MTR_QUARTZ = new OreMtrQuartz();
    public static final OreArgentum ORE_ARGENTUM = new OreArgentum();
    public static final MetalBlocks METAL_BLOCKS = new MetalBlocks();
    public static final BlockSlimyBrick SLIMY_BRICK = new BlockSlimyBrick();

    public static final Block BLOCK_CRUDE = new BlockCrude();
}
