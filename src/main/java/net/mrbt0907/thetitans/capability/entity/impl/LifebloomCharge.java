package net.mrbt0907.thetitans.capability.entity.impl;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.mrbt0907.thetitans.capability.entity.ILifebloomCharge;

public class LifebloomCharge implements ILifebloomCharge {

    private final EntityLivingBase entity;
    private float charge;

    public LifebloomCharge(EntityLivingBase entity) {
        this.entity = entity;
    }

    public LifebloomCharge() {
        this.entity = null;
    }

    @Override
    public float getCharge() {
        return charge;
    }

    @Override
    public void setCharge(float charge) {
        this.charge = MathHelper.clamp(
                charge,
                0.0F,
                getMaxCharge()
        );
    }

    @Override
    public void addCharge(float amount) {
        setCharge(this.charge + amount);
    }

    @Override
    public void consumeCharge(float amount) {
        setCharge(this.charge - amount);
    }

    @Override
    public float getMaxCharge() {
        return entity.getMaxHealth() * 5.0F;
    }
}
