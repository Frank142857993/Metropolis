package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.init.BlockInit;
import com.frank142857.metropolis.util.interfaces.*;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
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

import static com.frank142857.metropolis.world.city.ChunkGenFactory.*;

public class HouseNormal implements IHouse {
    private static final ResourceLocation NORMAL = new ResourceLocation("metropolis:buildings/house/house_normal");

    private int chunkX;
    private int chunkZ;
    private int baseHeight;

    private Random rand;

    public HouseNormal(int chunkX, int chunkZ, int baseHeight, Random rand, EnumFacing facing){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
        this.rand = rand;
    }

    @Override
    public String getType() {
        return "house";
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
        int x = chunkX * 16;
        int z = chunkZ * 16;
        int y = this.baseHeight;
        /*
        for(int dx = 0; dx < 16; dx++){
            for(int dz = 0; dz < 16; dz++){
                if(dx==0 || dz==0 || dx==15 || dz==15) world.setBlockState(new BlockPos(x + dx, y, z + dz), Blocks.DOUBLE_STONE_SLAB.getDefaultState());
            }
        }*/

        x += 1;
        z += 1;

        BlockPos pos = new BlockPos(x, this.baseHeight + 1, z); //baseHeight

        WorldServer worldserver = (WorldServer) world;
        MinecraftServer minecraftserver = world.getMinecraftServer();
        TemplateManager templatemanager = worldserver.getStructureTemplateManager();
        Template base = templatemanager.getTemplate(minecraftserver, NORMAL);

        Rotation[] rotations = Rotation.values();
        Rotation rotation = rotations[rand.nextInt(rotations.length)];
        BlockPos pos1 = base.getZeroPositionWithTransform(pos, Mirror.NONE, rotation);

        base.addBlocksToWorldChunk(world, pos1, new PlacementSettings().setChunk(new ChunkPos(chunkX, chunkZ)).setRotation(rotation).setIgnoreEntities(false));
    }

}
