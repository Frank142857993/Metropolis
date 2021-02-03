package com.frank142857.metropolis.init;

import com.frank142857.metropolis.block.*;
import com.frank142857.metropolis.block.fluids.*;
import com.frank142857.metropolis.block.ores.*;
import com.frank142857.metropolis.block.teleporter.BlockMtrPortal;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> REGISTER_BLOCKS = new ArrayList<Block>();

    public static final OreArgentum ORE_ARGENTUM = new OreArgentum();
    public static final OreMtrQuartz ORE_MTR_QUARTZ = new OreMtrQuartz();

    public static final MetalBlocks METAL_BLOCKS = new MetalBlocks();

    public static final Block BLOCK_CRUDE = new BlockCrude();

    public static final BlockMtrPortal BLOCK_MTR_PORTAL = new BlockMtrPortal();
}
