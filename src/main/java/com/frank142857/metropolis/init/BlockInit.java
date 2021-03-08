package com.frank142857.metropolis.init;

import com.frank142857.metropolis.block.*;
import com.frank142857.metropolis.block.base.BlockDirtMTR;
import com.frank142857.metropolis.block.base.BlockGrassMTR;
import com.frank142857.metropolis.block.base.BlockStoneMTR;
import com.frank142857.metropolis.block.decay.BlockRustedIron;
import com.frank142857.metropolis.block.decay.BlockWhiteMarble;
import com.frank142857.metropolis.block.fluids.*;
import com.frank142857.metropolis.block.ores.*;
import com.frank142857.metropolis.block.teleporter.BlockMtrPortal;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> REGISTER_BLOCKS = new ArrayList<Block>();

    public static final BlockMtrPortal BLOCK_MTR_PORTAL = new BlockMtrPortal();

    public static final BlockGrassMTR SURFACE_GRASS = new BlockGrassMTR();
    public static final BlockDirtMTR HEAVY_DIRT = new BlockDirtMTR();
    public static final BlockStoneMTR HARDENED_STONE = new BlockStoneMTR();
    public static final BlockMTR POLISHED_HARDENED_STONE = new BlockMTR(
            "polished_hardened_stone", Material.ROCK, MapColor.STONE, "pickaxe", 1, 4.5F, 0
    );
    public static final OreMTR IRON_ORE = new OreMTR("iron_ore", 1, 0);
    public static final OreMTR GOLD_ORE = new OreMTR("gold_ore", 2, 0);
    public static final OreMTR DIAMOND_ORE = new OreMTR("diamond_ore", 2, 0);
    public static final OreMTR REDSTONE_ORE = new OreMTR("redstone_ore", 2, 0);
    public static final OreMTR QUARTZ_ORE = new OreMTR("quartz_ore", 1, 0);
    public static final OreMTR ARGENTUM_ORE = new OreMTR("argentum_ore", 2, 4);
    public static final OreMTR DYNAMITE_ORE = new OreMTR("dynamite_ore", 2, 0);
    public static final OreMtrQuartz ORE_MTR_QUARTZ = new OreMtrQuartz();
    public static final OreArgentum ORE_ARGENTUM = new OreArgentum();
    public static final MetalBlocks METAL_BLOCKS = new MetalBlocks();
    public static final BlockRustedIron SLIGHTLY_RUSTED_IRON_BLOCK = new BlockRustedIron("_slightly_rusted", 5.0F);
    public static final BlockRustedIron PARTLY_RUSTED_IRON_BLOCK = new BlockRustedIron("_partly_rusted", 4.0F);
    public static final BlockRustedIron SEVERELY_RUSTED_IRON_BLOCK = new BlockRustedIron("_severely_rusted", 3.0F);
    public static final BlockRustedIron RUSTED_IRON_BLOCK = new BlockRustedIron("_rusted", 2.0F);
    public static final BlockWhiteMarble WHITE_MARBLE = new BlockWhiteMarble("", 8.0F);
    public static final BlockWhiteMarble SLIGHTLY_CRACKED_WHITE_MARBLE = new BlockWhiteMarble("_slightly_cracked", 6.0F);
    public static final BlockWhiteMarble SEVERELY_CRACKED_WHITE_MARBLE = new BlockWhiteMarble("_severely_cracked", 4.0F);
    public static final BlockWhiteMarble CRACKED_WHITE_MARBLE = new BlockWhiteMarble("_cracked", 2.0F);
    public static final BlockMTR UNDER_BRICK = new BlockMTR(
            "under_brick", Material.ROCK, MapColor.STONE, "pickaxe", 0, 3.0F, 0
    );
    public static final BlockAccelerateMTR SLIMY_UNDER_BRICK = new BlockAccelerateMTR(
            "slimy_under_brick", Material.ROCK, MapColor.GREEN_STAINED_HARDENED_CLAY, "pickaxe", 0, 3.0F, 0, 0.6D
    );
    public static final BlockMTR HARDENED_UNDER_BRICK = new BlockMTR(
            "hardened_under_brick", Material.ROCK, MapColor.SILVER, "pickaxe", 0, 5.0F, 0
    );
    public static final Block BLOCK_CRUDE = new BlockCrude();
}
