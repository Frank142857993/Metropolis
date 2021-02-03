package com.frank142857.metropolis.world.dimension.metropolis;

import com.frank142857.metropolis.init.DimensionInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderMetropolis extends WorldProvider {

    @Override
    protected void init(){
        this.biomeProvider = new BiomeProviderMetropolis(this.world.getSeed());
        this.nether = false;
    }

    @Override
    public DimensionType getDimensionType(){
        return DimensionInit.dimensionType;
    }

    @Override
    public IChunkGenerator createChunkGenerator(){
        return new ChunkGeneratorMetropolis(this.world, this.world.getSeed());
    }

    @Override
    public void setAllowedSpawnTypes(boolean hostile, boolean animal){
        super.setAllowedSpawnTypes(false, true);
    }


    @SuppressWarnings("deprecation")
    @Override
    public void resetRainAndThunder() {

        WorldInfo worldInfo = ObfuscationReflectionHelper.getPrivateValue(DerivedWorldInfo.class, (DerivedWorldInfo) world.getWorldInfo(), "theWorldInfo", "field_76115_a");

        worldInfo.setRainTime(0);
        worldInfo.setRaining(false);
        worldInfo.setThunderTime(0);
        worldInfo.setThundering(false);

        if(this.world.getGameRules().getBoolean("doDaylightCycle"))
        {
            long i = worldInfo.getWorldTime() + 24000L;
            worldInfo.setWorldTime(i - i % 24000L);

        }
    }

    @Override
    public WorldProvider.WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos){
        return WorldSleepResult.ALLOW;
    }

    @Override
    public boolean canRespawnHere(){
        return false;
    }

    @Override
    public boolean isSurfaceWorld(){
        return true;
    }

    @Override
    public boolean shouldMapSpin(String entityName, double x, double z, double rotation) {
        return false;
    }

    @Override
    public float getCloudHeight(){
        return 255.0F;
    }

    @Override
    public boolean hasSkyLight(){
        return true;
    }

    @Override
    public boolean doesWaterVaporize(){
        return false;
    }

    @SideOnly(Side.CLIENT)
    public double getVoidFogYFactor(){
        return 0.03125D;
    }

}
