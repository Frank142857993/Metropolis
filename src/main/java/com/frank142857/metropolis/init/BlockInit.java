package com.frank142857.metropolis.init;

import com.frank142857.metropolis.block.*;
import com.frank142857.metropolis.block.base.*;
import com.frank142857.metropolis.block.teleporter.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.*;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> REGISTER_BLOCKS = new ArrayList<Block>();

    //portal
    public static final BlockMtrPortal BLOCK_MTR_PORTAL = new BlockMtrPortal();

    //base blocks
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
    public static final BlockMTR RAW_MARBLE = (BlockMTR) new BlockMTR(
            "raw_marble", Material.ROCK, MapColor.QUARTZ, "pickaxe", 0, 3.0F, 0
    ).setResistance(6.0F);
    public static final BlockMTR MARBLE = (BlockMTR) new BlockMTR(
            "marble", Material.ROCK, MapColor.QUARTZ, "pickaxe", 0, 3.0F, 0
    ).setResistance(6.0F);
    public static final BlockMTR MARBLE_BRICK = (BlockMTR) new BlockMTR(
            "marble_brick", Material.ROCK, MapColor.QUARTZ, "pickaxe", 0, 3.0F, 0
    ).setResistance(6.0F);
    public static final BlockPillarMTR MARBLE_PILLAR = (BlockPillarMTR) new BlockPillarMTR(
            "marble_pillar", Material.ROCK, MapColor.QUARTZ, "pickaxe", 0, 3.0F, 0
    ).setResistance(6.0F);
    public static final BlockCloud CLOUD = new BlockCloud();
    /*
    public static final BlockMTR FIGURE = (BlockMTR) new BlockMTR(
            "figure", Material.ROCK, MapColor.STONE, "pickaxe", 0, 3.0F, 0
    ).setResistance(6.0F); //TODO Figure
    */

    public static final BlockTrasnslucentVirtual HOLOGRAM_RED = (BlockTrasnslucentVirtual) new BlockTrasnslucentVirtual("hologram_red", MapColor.STONE, 3.0F, 4, SoundType.STONE).setResistance(10.0F);
    public static final BlockTrasnslucentVirtual HOLOGRAM_GREEN = (BlockTrasnslucentVirtual) new BlockTrasnslucentVirtual("hologram_green", MapColor.STONE, 3.0F, 4, SoundType.STONE).setResistance(10.0F);
    public static final BlockTrasnslucentVirtual HOLOGRAM_BLUE = (BlockTrasnslucentVirtual) new BlockTrasnslucentVirtual("hologram_blue", MapColor.STONE, 3.0F, 4, SoundType.STONE).setResistance(10.0F);

    //metal & ores
    public static final OreMTR IRON_ORE = new OreMTR("iron_ore", 1, 0);
    public static final OreMTR GOLD_ORE = new OreMTR("gold_ore", 2, 0);
    public static final OreMTR DIAMOND_ORE = new OreMTR("diamond_ore", 2, 0);
    public static final OreMTR REDSTONE_ORE = new OreMTR("redstone_ore", 2, 0);
    public static final OreMTR QUARTZ_ORE = new OreMTR("quartz_ore", 1, 0);
    public static final OreMTR SHADOW_METAL_ORE = new OreMTR("shadow_metal_ore", 2, 0);

    public static final MetalBlockMTR SHADOW_METAL_BLOCK = new MetalBlockMTR("shadow_metal_block", Material.IRON, 2);

    //wood
    public static final BlockLogMTR SILVER_WOOD = new BlockLogMTR();
    public static final BlockPlanksMTR SILVER_PLANKS = (BlockPlanksMTR) new BlockPlanksMTR().setResistance(6.0F);
    public static final BlockLeavesMTR SILVER_LEAVES = new BlockLeavesMTR();
    public static final BlockFloweringLeaves FLOWERING_SILVER_LEAVES = new BlockFloweringLeaves();
    public static final BlockSaplingMTR SILVER_SAPLING = new BlockSaplingMTR();
    public static final BlockSilverBookshelf SILVER_BOOKSHELF = new BlockSilverBookshelf();
    public static final BlockFenceMTR SILVER_WOOD_FENCE = new BlockFenceMTR("silver_wood_fence", 4.5F, 6.0F);
    public static final BlockFenceGateMTR SILVER_WOOD_FENCE_GATE = new BlockFenceGateMTR("silver_wood_fence_gate", 4.5F, 6.0F);

    //plants
    public static final BlockBushMTR GLOWING_HERBS = (BlockBushMTR) new BlockBushMTR().setLightLevel(0.067F);
    public static final BlockFlowerMTR FLUORESCENT_FLOWER_RED = (BlockFlowerMTR) new BlockFlowerMTR("fluorescent_flower_red", MapColor.RED).setLightLevel(0.667F);
    public static final BlockFlowerMTR FLUORESCENT_FLOWER_GREEN = (BlockFlowerMTR) new BlockFlowerMTR("fluorescent_flower_green", MapColor.GREEN).setLightLevel(0.667F);
    public static final BlockFlowerMTR FLUORESCENT_FLOWER_BLUE = (BlockFlowerMTR) new BlockFlowerMTR("fluorescent_flower_blue", MapColor.BLUE).setLightLevel(0.667F);


    public static final BlockFlowerPotMTR EVERGROWTH_FLOWER_POT = new BlockFlowerPotMTR();

    //decoration
    public static final BlockCloudGlass CLOUD_GLASS = new BlockCloudGlass();
    public static final BlockCloudGlassPane CLOUD_GLASS_PANE = new BlockCloudGlassPane();
    public static final BlockDoorMTR CLOUD_GLASS_DOOR = new BlockDoorMTR("cloud_glass_door", Material.GLASS);

    //misc
    public static final BlockCeilingLight CEILING_LIGHT = new BlockCeilingLight();

    //virtual
    public static final BlockVirtual VIRTUAL_FOUNDATION_STONE = (BlockVirtual) new BlockVirtual("virtual_foundation_stone", MapColor.STONE, 4.5F, 0, SoundType.STONE).setResistance(10.0F);
    public static final BlockVirtual VIRTUAL_POLISHED_FOUNDATION_STONE = (BlockVirtual) new BlockVirtual("virtual_polished_foundation_stone", MapColor.STONE, 4.5F, 0, SoundType.STONE).setResistance(10.0F);
    public static final BlockTrasnslucentVirtual VIRTUAL_CLOUD_GLASS = (BlockTrasnslucentVirtual) new BlockTrasnslucentVirtual("virtual_cloud_glass", MapColor.AIR, 0.3F, 0, SoundType.GLASS);
    public static final BlockPaneVirtual VIRTUAL_CLOUD_GLASS_PANE = new BlockPaneVirtual("virtual_cloud_glass_pane", 0.3F, 0, SoundType.GLASS);

    //slab
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
    public static final BlockSlab MARBLE_SLAB_DOUBLE = new BlockDoubleSlabMTR(
            "marble_slab_double", Material.ROCK, "pickaxe", 0, 3.0F, BlockInit.MARBLE_SLAB_HALF
    );
    public static final BlockSlab MARBLE_SLAB_HALF = new BlockHalfSlabMTR(
            "marble_slab_half", Material.ROCK, "pickaxe", 0, 3.0F, BlockInit.MARBLE_SLAB_HALF, BlockInit.MARBLE_SLAB_DOUBLE
    );

    //stairs
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
    public static final BlockStairsMTR MARBLE_STAIRS = new BlockStairsMTR(
            "marble_stairs", MARBLE.getDefaultState(), 3.0F, 6.0F, SoundType.STONE, "pickaxe", 0
    );
}
