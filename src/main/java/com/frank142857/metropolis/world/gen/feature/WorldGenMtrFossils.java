package com.frank142857.metropolis.world.gen.feature;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenMtrFossils extends WorldGenerator {

    private static final ResourceLocation STRUCTURE_FOSSIL = new ResourceLocation("metropolis:fossils/fossil");
    private static final ResourceLocation STRUCTURE_FOSSIL_DIAMOND = new ResourceLocation("metropolis:fossils/fossil_diamond");
    private static final ResourceLocation[] FOSSILS = new ResourceLocation[] {STRUCTURE_FOSSIL};
    private static final ResourceLocation[] FOSSILS_DIAMOND = new ResourceLocation[] {STRUCTURE_FOSSIL_DIAMOND};

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        Random random = worldIn.getChunkFromBlockCoords(position).getRandomWithSeed(987234911L);
        MinecraftServer minecraftserver = worldIn.getMinecraftServer();
        Rotation[] arotation = Rotation.values();
        Rotation rotation = arotation[random.nextInt(arotation.length)];
        int i = random.nextInt(FOSSILS.length);
        TemplateManager templatemanager = worldIn.getSaveHandler().getStructureTemplateManager();
        Template template = templatemanager.getTemplate(minecraftserver, FOSSILS[i]);
        Template template1 = templatemanager.getTemplate(minecraftserver, FOSSILS_DIAMOND[i]);
        ChunkPos chunkpos = new ChunkPos(position);
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(structureboundingbox).setRandom(random);
        BlockPos blockpos = template.transformedSize(rotation);
        int j = random.nextInt(16 - blockpos.getX());
        int k = random.nextInt(16 - blockpos.getZ());


        /*int l = 256;

        for (int i1 = 0; i1 < blockpos.getX(); ++i1)
        {
            for (int j1 = 0; j1 < blockpos.getX(); ++j1)
            {
                l = Math.min(l, worldIn.getHeight(position.getX() + i1 + j, position.getZ() + j1 + k));
            }
        }*/

        int k1 = Math.max(64 - random.nextInt(10), 40);
        BlockPos blockpos1 = template.getZeroPositionWithTransform(position.add(j, k1, k), Mirror.NONE, rotation);
        placementsettings.setIntegrity(0.9F);
        template.addBlocksToWorld(worldIn, blockpos1, placementsettings, 20);
        placementsettings.setIntegrity(0.1F);
        template1.addBlocksToWorld(worldIn, blockpos1, placementsettings, 20);
        return true;
    }
}
