package com.frank142857.metropolis.capability.storage;

import com.frank142857.metropolis.capability.ICapabilityPortal;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class StoragePortal implements Capability.IStorage<ICapabilityPortal> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICapabilityPortal> capability, ICapabilityPortal instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("inPortal", instance.isInPortal());
        nbt.setInteger("ticksUntilTeleport", instance.getTicksUntilTeleport());
        return nbt;
    }

    @Override
    public void readNBT(Capability<ICapabilityPortal> capability, ICapabilityPortal instance, EnumFacing side, NBTBase nbt) {
        instance.setInPortal(((NBTTagCompound) nbt).getBoolean("inPortal"));
        instance.setTicksUntilTeleport(((NBTTagCompound) nbt).getInteger("ticksUntilTeleport"));
    }

}
