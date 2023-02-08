package com.frank142857.metropolis.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class CapabilityPortal implements ICapabilityPortal {
    private boolean isInPortal = false;
    private int ticksUntilTeleport = 0;

    @Override
    public boolean isInPortal() {
        return isInPortal;
    }

    @Override
    public void setInPortal(boolean inPortal) {
        isInPortal = inPortal;
    }

    @Override
    public int getTicksUntilTeleport() {
        return this.ticksUntilTeleport;
    }

    @Override
    public void setTicksUntilTeleport(int ticksUntilTeleport) {
        this.ticksUntilTeleport = ticksUntilTeleport;
    }
}
