package com.frank142857.metropolis.world.gen.feature;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenSmallFeatures extends WorldGenerator {
    private static final ResourceLocation FLOWER_SHOP = new ResourceLocation("metropolis:flower_shop");

    /*
    public WorldGenSmallFeatures(String featureType){
    TODO for more structures
    }*/

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        WorldServer worldserver = (WorldServer) worldIn;
        MinecraftServer minecraftserver = worldIn.getMinecraftServer();
        TemplateManager templatemanager = worldserver.getStructureTemplateManager();
        Template template = templatemanager.get(minecraftserver, FLOWER_SHOP);

        Rotation[] rotations = Rotation.values();
        Rotation rotation = rotations[rand.nextInt(rotations.length)];
        BlockPos pos1 = template.getZeroPositionWithTransform(position, Mirror.NONE, rotation);

        template.addBlocksToWorld(worldIn, pos1, new PlacementSettings().setRotation(rotation).setIgnoreEntities(true));

        return true;
    }
}
