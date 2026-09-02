package net.mrbt0907.thetitans.capability.entity.provider;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.mrbt0907.thetitans.capability.entity.ILifebloomCharge;
import net.mrbt0907.thetitans.capability.entity.impl.LifebloomCharge;
import net.mrbt0907.thetitans.registries.CapabilityRegistry;

public class LifebloomChargeProvider
        implements ICapabilitySerializable<NBTTagCompound> {

    private final ILifebloomCharge instance;

    public LifebloomChargeProvider(EntityLivingBase entity){
        this.instance = new LifebloomCharge(entity);
    }

    @Override
    public <T> T getCapability(
            Capability<T> capability,
            EnumFacing facing) {

        if (capability == CapabilityRegistry.LIFEBLOOM_CHARGE) {
            return CapabilityRegistry.LIFEBLOOM_CHARGE.cast(instance);
        }

        return null;
    }

    @Override
    public boolean hasCapability(
            Capability<?> capability,
            EnumFacing facing) {

        return capability == CapabilityRegistry.LIFEBLOOM_CHARGE;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) CapabilityRegistry.LIFEBLOOM_CHARGE
                .getStorage()
                .writeNBT(
                        CapabilityRegistry.LIFEBLOOM_CHARGE,
                        instance,
                        null
                );
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        CapabilityRegistry.LIFEBLOOM_CHARGE
                .getStorage()
                .readNBT(
                        CapabilityRegistry.LIFEBLOOM_CHARGE,
                        instance,
                        null,
                        nbt
                );
    }
}
