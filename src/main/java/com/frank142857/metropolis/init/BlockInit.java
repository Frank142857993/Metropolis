package com.frank142857.metropolis.init;

import com.frank142857.metropolis.block.*;
import com.frank142857.metropolis.block.base.*;
import com.frank142857.metropolis.block.teleporter.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.*;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> REGISTER_BLOCKS = new ArrayList<Block>();

    public static final BlockMtrPortal BLOCK_MTR_PORTAL = new BlockMtrPortal();

    public static final BlockGrassMTR SURFACE_GRASS = new BlockGrassMTR();
    public static final BlockDirtMTR HEAVY_DIRT = new BlockDirtMTR();
    public static final BlockStoneMTR FOUNDATION_STONE = new BlockStoneMTR();
    public static final BlockMTR POLISHED_FOUNDATION_STONE = (BlockMTR) new BlockMTR(
            "polished_foundation_stone", Material.ROCK, MapColor.STONE, "pickaxe", 1, 4.5F, 0
    ).setResistance(10.0F);
    public static final BlockMTR STONE_PAVING = (BlockMTR) new BlockMTR(
            "stone_paving", Material.ROCK, MapColor.QUARTZ, "pickaxe", 2, 6.5F, 0
    ).setResistance(25.0F);

    public static final MetalBlockMTR DYNAMITE_BLOCK = new MetalBlockMTR("dynamite_block", Material.IRON, 2);

    public static final OreMTR IRON_ORE = new OreMTR("iron_ore", 1, 0);
    public static final OreMTR GOLD_ORE = new OreMTR("gold_ore", 2, 0);
    public static final OreMTR DIAMOND_ORE = new OreMTR("diamond_ore", 2, 0);
    public static final OreMTR REDSTONE_ORE = new OreMTR("redstone_ore", 2, 0);
    public static final OreMTR QUARTZ_ORE = new OreMTR("quartz_ore", 1, 0);
    public static final OreMTR DYNAMITE_ORE = new OreMTR("dynamite_ore", 2, 0);

    public static final BlockMTR UNDER_BRICK = new BlockMTR(
            "under_brick", Material.ROCK, MapColor.STONE, "pickaxe", 0, 3.0F, 0
    );
    public static final BlockAccelerateMTR SLIMY_UNDER_BRICK = new BlockAccelerateMTR(
            "slimy_under_brick", Material.ROCK, MapColor.GREEN_STAINED_HARDENED_CLAY, "pickaxe", 0, 3.0F, 0, 0.6D
    );
    public static final BlockMTR HARDENED_UNDER_BRICK = new BlockMTR(
            "hardened_under_brick", Material.ROCK, MapColor.SILVER, "pickaxe", 0, 5.0F, 0
    );
    public static final BlockCeilingLight CEILING_LIGHT = new BlockCeilingLight();
}
