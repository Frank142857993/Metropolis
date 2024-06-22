package com.frank142857.metropolis.world.city.features;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class FlowerShopGenerator {

    private static final ResourceLocation FLOWER_SHOP = new ResourceLocation("metropolis:flower_shop");

    public void generate(World world, int chunkX, int chunkZ, int baseHeight, Random rand){
        int x = chunkX * 16;
        int z = chunkZ * 16;
        int y = baseHeight;
        x += 2;
        z += 2;

        BlockPos pos = new BlockPos(x, y + 1, z); //baseHeight

        WorldServer worldserver = (WorldServer) world;
        MinecraftServer minecraftserver = world.getMinecraftServer();
        TemplateManager templatemanager = worldserver.getStructureTemplateManager();
        Template template = templatemanager.getTemplate(minecraftserver, FLOWER_SHOP);
        //PlacementSettings placementsettings = (new PlacementSettings()).setChunk(chunkpos);

        Rotation[] rotations = Rotation.values();
        Rotation rotation = rotations[rand.nextInt(rotations.length)];
        BlockPos pos1 = template.getZeroPositionWithTransform(pos, Mirror.NONE, rotation);

        template.addBlocksToWorldChunk(world, pos1, new PlacementSettings().setChunk(new ChunkPos(chunkX, chunkZ)).setRotation(rotation).setIgnoreEntities(false));
        //TODO Birch door -> silver door / glass door (closed); wooden walls -> quartz walls; torch -> lantern; silver saplings

    }
}
