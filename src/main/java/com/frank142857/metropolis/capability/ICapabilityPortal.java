package com.frank142857.metropolis.capability;

public interface ICapabilityPortal {
    public boolean isInPortal();

    public void setInPortal(boolean inPortal);

    public int getTicksUntilTeleport();

    public void setTicksUntilTeleport(int ticksUntilTeleport);
}
