package net.mrbt0907.thetitans.capability.entity.storage;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.mrbt0907.thetitans.capability.entity.ILifebloomCharge;

public class LifebloomChargeStorage
        implements Capability.IStorage<ILifebloomCharge> {

    public static final String NBT_TAG_COMPOUND_KEY = "lifebloom_charge_value";

    @Override
    public NBTBase writeNBT(
            Capability<ILifebloomCharge> capability,
            ILifebloomCharge instance,
            EnumFacing side) {

        NBTTagCompound compound = new NBTTagCompound();
        compound.setFloat(NBT_TAG_COMPOUND_KEY, instance.getCharge());

        return compound;
    }

    @Override
    public void readNBT(
            Capability<ILifebloomCharge> capability,
            ILifebloomCharge instance,
            EnumFacing side,
            NBTBase nbt) {

        instance.setCharge(((NBTTagCompound) nbt).getFloat(NBT_TAG_COMPOUND_KEY));
    }
}
