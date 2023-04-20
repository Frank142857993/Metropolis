package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.util.interfaces.*;
import com.frank142857.metropolis.world.city.ChunkGenFactory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class Sculpture implements IHouse {
    private static final ResourceLocation RELIC_PORTAL = new ResourceLocation("metropolis:portal");

    private int chunkX;
    private int chunkZ;
    private int baseHeight;
    private int floorCount;

    private Random rand;

    private IBlockState baseBlock = Blocks.QUARTZ_BLOCK.getDefaultState();
    private IBlockState fillerBlock = Blocks.QUARTZ_BLOCK.getDefaultState();

    public Sculpture(int chunkX, int chunkZ, int baseHeight, Random rand){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
        this.rand = rand;
    }

    @Override
    public String getType() {
        return "sculpture";
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
        Template template = templatemanager.getTemplate(minecraftserver, RELIC_PORTAL);

        Rotation[] rotations = Rotation.values();
        Rotation rotation = rotations[rand.nextInt(rotations.length)];
        BlockPos pos1 = template.getZeroPositionWithTransform(pos, Mirror.NONE, rotation);

        template.addBlocksToWorldChunk(world, pos1, new PlacementSettings().setChunk(new ChunkPos(chunkX, chunkZ)).setRotation(rotation).setIgnoreEntities(false));
    }
}
