package net.mrbt0907.thetitans.capability.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.capabilities.Capability;

public interface ILifebloomCharge {

    float getCharge();

    void setCharge(float charge);

    void addCharge(float amount);

    void consumeCharge(float amount);

    float getMaxCharge();
}
