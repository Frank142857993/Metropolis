package com.frank142857.metropolis.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidBaseMTR extends Fluid {
    protected static int mapColor = 0xFFFFFFFF;
    protected static float overlayAlpha = 0.2F;
    protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
    protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
    protected static Material material = Material.WATER;
    protected boolean bucketEnabled = false;

    public FluidBaseMTR(String fluidName, ResourceLocation still, ResourceLocation flowing)
    {
        super(fluidName, still, flowing);
    }

    public FluidBaseMTR(String fluidName, ResourceLocation still, ResourceLocation flowing, int mapColor)
    {
        this(fluidName, still, flowing);
        setColor(mapColor);
    }

    public FluidBaseMTR(String fluidName, ResourceLocation still, ResourceLocation flowing, int mapColor, float overlayAlpha)
    {
        this(fluidName, still, flowing, mapColor);
        setAlpha(overlayAlpha);
    }

    @Override
    public int getColor()
    {
        return mapColor;
    }

    @Override
    public FluidBaseMTR setColor(int parColor)
    {
        mapColor = parColor;
        return this;
    }

    public float getAlpha()
    {
        return overlayAlpha;
    }

    public FluidBaseMTR setAlpha(float parOverlayAlpha)
    {
        overlayAlpha = parOverlayAlpha;
        return this;
    }

    public FluidBaseMTR setHasBucket(boolean parEnableBucket)
    {
        bucketEnabled = parEnableBucket;
        return this;
    }

    public boolean isBucketEnabled()
    {
        return bucketEnabled;
    }

    @Override
    public FluidBaseMTR setEmptySound(SoundEvent parSound)
    {
        emptySound = parSound;
        return this;
    }

    @Override
    public SoundEvent getEmptySound()
    {
        return emptySound;
    }

    @Override
    public FluidBaseMTR setFillSound(SoundEvent parSound)
    {
        fillSound = parSound;
        return this;
    }

    @Override
    public SoundEvent getFillSound()
    {
        return fillSound;
    }

    public FluidBaseMTR setMaterial(Material parMaterial)
    {
        material = parMaterial;
        return this;
    }

    public Material getMaterial()
    {
        return material;
    }

    @Override
    public boolean doesVaporize(FluidStack fluidStack)
    {
        if (block == null)
            return false;
        return block.getDefaultState().getMaterial() == getMaterial();
    }
}
