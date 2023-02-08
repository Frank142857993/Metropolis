package com.frank142857.metropolis.world.city.features;

import com.frank142857.metropolis.util.interfaces.IBuilding;
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
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

import static com.frank142857.metropolis.world.city.ChunkGenFactory.fillMargin;

public class FlowerShop implements IBuilding {
    private static final ResourceLocation FLOWER_SHOP = new ResourceLocation("metropolis:flower_shop");

    private int chunkX;
    private int chunkZ;
    private int baseHeight;

    private Random rand;

    public FlowerShop(int chunkX, int chunkZ, int baseHeight, Random rand, EnumFacing facing){
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.baseHeight = baseHeight;
        this.rand = rand;
    }

    @Override
    public String getType() {
        return "flower_shop";
    }

    @Override
    public IBlockState getBaseBlock() {
        return Blocks.DOUBLE_STONE_SLAB.getDefaultState();
    }

    @Override
    public IBlockState[] getFillerBlocks() {
        return new IBlockState[0];
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
    public int getMinFloor() {
        return 0;
    }

    @Override
    public int getFloorVariation() {
        return 0;
    }

    @Override
    public int getFloorHeight() {
        return 0;
    }

    @Override
    public void setFillerBlock(IBlockState fillerBlock) {

    }

    @Override
    public void setFloorCount(int floorCount) {

    }

    @Override
    public void generate(World worldIn, ChunkPrimer primer) {
        int y = baseHeight;

        fillMargin(primer, 0, 0, 15, 15, y, Blocks.DOUBLE_STONE_SLAB.getDefaultState());

    }

    @Override
    public void addDetails(World worldIn){
        /*
        int x = chunkX * 16 + 2;
        int z = chunkZ * 16 + 2;
        BlockPos pos = new BlockPos(x, this.baseHeight + 1, z); //baseHeight

        WorldServer worldserver = (WorldServer) worldIn;
        MinecraftServer minecraftserver = worldIn.getMinecraftServer();
        TemplateManager templatemanager = worldserver.getStructureTemplateManager();
        Template template = templatemanager.getTemplate(minecraftserver, FLOWER_SHOP);
        //PlacementSettings placementsettings = (new PlacementSettings()).setChunk(chunkpos);

        Rotation[] rotations = Rotation.values();
        Rotation rotation = rotations[rand.nextInt(rotations.length)];
        BlockPos pos1 = template.getZeroPositionWithTransform(pos, Mirror.NONE, rotation);

        template.addBlocksToWorldChunk(worldIn, pos1, new PlacementSettings().setChunk(new ChunkPos(chunkX, chunkZ)).setRotation(rotation).setIgnoreEntities(true));
        //TODO Birch door -> silver door / glass door (closed); wooden walls -> quartz walls; torch -> lantern; silver saplings
        */
    }
}
