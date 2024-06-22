package com.frank142857.metropolis.init;

import com.frank142857.metropolis.block.*;
import com.frank142857.metropolis.block.base.*;
import com.frank142857.metropolis.block.teleporter.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.*;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> REGISTER_BLOCKS = new ArrayList<Block>();

    public static final BlockMtrPortal BLOCK_MTR_PORTAL = new BlockMtrPortal();

    public static final BlockGrassMTR SURFACE_GRASS = new BlockGrassMTR();
    public static final BlockGrassUpsideDown UPSIDE_DOWN_SURFACE_GRASS = new BlockGrassUpsideDown();

    public static final BlockDirtMTR HEAVY_DIRT = new BlockDirtMTR();
    public static final BlockStoneMTR FOUNDATION_STONE = new BlockStoneMTR();
    public static final BlockMTR POLISHED_FOUNDATION_STONE = (BlockMTR) new BlockMTR(
            "polished_foundation_stone", Material.ROCK, MapColor.STONE, "pickaxe", 1, 4.5F, 0
    ).setResistance(10.0F);
    public static final BlockMTR STONE_PAVING = (BlockMTR) new BlockMTR(
            "stone_paving", Material.ROCK, MapColor.STONE, "pickaxe", 2, 6.5F, 0
    ).setResistance(25.0F);
    public static final BlockMTR BLACK_BRICK = (BlockMTR) new BlockMTR( //BLACK BRICK 青砖
            "black_brick", Material.ROCK, MapColor.STONE, "pickaxe", 0, 3.0F, 0
    ).setResistance(6.0F);
    public static final BlockAccelerateMTR MOSSY_BLACK_BRICK = new BlockAccelerateMTR(
            "mossy_black_brick", Material.ROCK, MapColor.GREEN_STAINED_HARDENED_CLAY, "pickaxe", 0, 3.0F, 0, 0.6D
    );


    public static final MetalBlockMTR SHADOW_METAL_BLOCK = new MetalBlockMTR("shadow_metal_block", Material.IRON, 2);

    public static final OreMTR IRON_ORE = new OreMTR("iron_ore", 1, 0);
    public static final OreMTR GOLD_ORE = new OreMTR("gold_ore", 2, 0);
    public static final OreMTR DIAMOND_ORE = new OreMTR("diamond_ore", 2, 0);
    public static final OreMTR REDSTONE_ORE = new OreMTR("redstone_ore", 2, 0);
    public static final OreMTR QUARTZ_ORE = new OreMTR("quartz_ore", 1, 0);
    public static final OreMTR SHADOW_METAL_ORE = new OreMTR("shadow_metal_ore", 2, 0);

    public static final BlockLogMTR SILVER_WOOD = new BlockLogMTR();
    public static final BlockPlanksMTR SILVER_PLANKS = (BlockPlanksMTR) new BlockPlanksMTR().setResistance(6.0F);
    public static final BlockLeavesMTR SILVER_LEAVES = new BlockLeavesMTR();
    public static final BlockFloweringLeaves FLOWERING_SILVER_LEAVES = new BlockFloweringLeaves();
    public static final BlockSaplingMTR SILVER_SAPLING = new BlockSaplingMTR();

    public static final BlockCeilingLight CEILING_LIGHT = new BlockCeilingLight();

    public static final BlockSlab FOUNDATION_STONE_SLAB_DOUBLE = new BlockDoubleSlabMTR(
            "foundation_stone_slab_double", Material.ROCK, "pickaxe", 1, 4.5F, BlockInit.FOUNDATION_STONE_SLAB_HALF
    );
    public static final BlockSlab FOUNDATION_STONE_SLAB_HALF = new BlockHalfSlabMTR(
            "foundation_stone_slab_half", Material.ROCK, "pickaxe", 1, 4.5F, BlockInit.FOUNDATION_STONE_SLAB_HALF, BlockInit.FOUNDATION_STONE_SLAB_DOUBLE
    );
    public static final BlockSlab POLISHED_FOUNDATION_STONE_SLAB_DOUBLE = new BlockDoubleSlabMTR(
            "polished_foundation_stone_slab_double", Material.ROCK, "pickaxe", 1, 4.5F, BlockInit.POLISHED_FOUNDATION_STONE_SLAB_HALF
    );
    public static final BlockSlab POLISHED_FOUNDATION_STONE_SLAB_HALF = new BlockHalfSlabMTR(
            "polished_foundation_stone_slab_half", Material.ROCK, "pickaxe", 1, 4.5F, BlockInit.POLISHED_FOUNDATION_STONE_SLAB_HALF, BlockInit.POLISHED_FOUNDATION_STONE_SLAB_DOUBLE
    );
    public static final BlockSlab BLACK_BRICK_SLAB_DOUBLE = new BlockDoubleSlabMTR(
            "black_brick_slab_double", Material.ROCK, "pickaxe", 0, 3.0F, BlockInit.BLACK_BRICK_SLAB_HALF
    );
    public static final BlockSlab BLACK_BRICK_SLAB_HALF = new BlockHalfSlabMTR(
            "black_brick_slab_half", Material.ROCK, "pickaxe", 0, 3.0F, BlockInit.BLACK_BRICK_SLAB_HALF, BlockInit.BLACK_BRICK_SLAB_DOUBLE
    );
    public static final BlockSlab STONE_PAVING_SLAB_DOUBLE = new BlockDoubleSlabMTR(
            "stone_paving_slab_double", Material.ROCK, "pickaxe", 2, 6.5F, BlockInit.STONE_PAVING_SLAB_HALF
    );
    public static final BlockSlab STONE_PAVING_SLAB_HALF = new BlockHalfSlabMTR(
            "stone_paving_slab_half", Material.ROCK, "pickaxe", 2, 6.5F, BlockInit.STONE_PAVING_SLAB_HALF, BlockInit.STONE_PAVING_SLAB_DOUBLE
    );
    public static final BlockSlab SILVER_WOOD_SLAB_DOUBLE = new BlockDoubleSlabMTR(
            "silver_wood_slab_double", Material.ROCK, "pickaxe", 1, 4.5F, BlockInit.SILVER_WOOD_SLAB_HALF
    );
    public static final BlockSlab SILVER_WOOD_SLAB_HALF = new BlockHalfSlabMTR(
            "silver_wood_slab_half", Material.ROCK, "pickaxe", 1, 4.5F, BlockInit.SILVER_WOOD_SLAB_HALF, BlockInit.SILVER_WOOD_SLAB_DOUBLE
    );

    public static final BlockStairsMTR FOUNDATION_STONE_STAIRS = new BlockStairsMTR(
            "foundation_stone_stairs", FOUNDATION_STONE.getDefaultState(), 4.5F, 10.0F, SoundType.STONE, "pickaxe", 1
    );
    public static final BlockStairsMTR POLISHED_FOUNDATION_STONE_STAIRS = new BlockStairsMTR(
            "polished_foundation_stone_stairs", POLISHED_FOUNDATION_STONE.getDefaultState(), 4.5F, 10.0F, SoundType.STONE, "pickaxe", 1
    );
    public static final BlockStairsMTR BLACK_BRICK_STAIRS = new BlockStairsMTR(
            "black_brick_stairs", BLACK_BRICK.getDefaultState(), 3.0F, 6.0F, SoundType.STONE, "pickaxe", 0
    );
    public static final BlockStairsMTR SILVER_WOOD_STAIRS = new BlockStairsMTR(
            "silver_wood_stairs", SILVER_PLANKS.getDefaultState(), 4.5F, 6.0F, SoundType.STONE, "pickaxe", 1
    );

    public static final BlockFenceMTR SILVER_WOOD_FENCE = new BlockFenceMTR("silver_wood_fence", 4.5F, 6.0F);

    public static final BlockFenceGateMTR SILVER_WOOD_FENCE_GATE = new BlockFenceGateMTR("silver_wood_fence_gate", 4.5F, 6.0F);

    public static final BlockSilverBookshelf SILVER_BOOKSHELF = new BlockSilverBookshelf();
}
