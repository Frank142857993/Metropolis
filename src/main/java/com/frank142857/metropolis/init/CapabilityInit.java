package com.frank142857.metropolis.init;

import com.frank142857.metropolis.capability.CapabilityPortal;
import com.frank142857.metropolis.capability.ICapabilityPortal;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityInit implements ICapabilitySerializable {

    @CapabilityInject(ICapabilityPortal.class)
    public static final Capability<ICapabilityPortal> PORTAL = null;

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == PORTAL;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == PORTAL ? PORTAL.<T> cast(PORTAL.getDefaultInstance()) : null;
    }


    @Override
    public NBTBase serializeNBT() {
        return PORTAL.getStorage().writeNBT(PORTAL, PORTAL.getDefaultInstance(), null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        PORTAL.getStorage().readNBT(PORTAL, PORTAL.getDefaultInstance(), null, nbt);
    }
}
