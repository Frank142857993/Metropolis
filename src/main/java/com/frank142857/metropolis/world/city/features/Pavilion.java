package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.util.interfaces.*;
import com.frank142857.metropolis.world.city.ChunkGenFactory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class Pavilion implements IHouse {
    private static final ResourceLocation PAVILION = new ResourceLocation("metropolis:pavilion");

    private int chunkX;
    private int chunkZ;
    private int baseHeight;
    private int floorCount;

    private IBlockState baseBlock = Blocks.QUARTZ_BLOCK.getDefaultState();
    private IBlockState fillerBlock = Blocks.QUARTZ_BLOCK.getDefaultState();

    public Pavilion(int chunkX, int chunkZ, int baseHeight, Random rand){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
    }

    @Override
    public String getType() {
        return "pavilion";
    }

    @Override
    public ChunkPos getChunkPos() {
        return new ChunkPos(chunkX, chunkZ);
    }

    @Override
    public int getBaseHeight() {
        return baseHeight;
    }

    @Override
    public void generate(World world) {
        int x = chunkX * 16 + 2;
        int z = chunkZ * 16 + 2;
        int y = this.baseHeight + 1;

        BlockPos pos = new BlockPos(x, y, z);
        WorldServer worldserver = (WorldServer) world;
        MinecraftServer minecraftserver = world.getMinecraftServer();
        TemplateManager templatemanager = worldserver.getStructureTemplateManager();
        Template template = templatemanager.getTemplate(minecraftserver, PAVILION);

        template.addBlocksToWorldChunk(world, pos, new PlacementSettings().setChunk(new ChunkPos(chunkX, chunkZ)).setIgnoreEntities(false));
    }

}
