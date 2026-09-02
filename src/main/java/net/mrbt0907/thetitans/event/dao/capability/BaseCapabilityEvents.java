package net.mrbt0907.thetitans.event.dao.capability;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;

public class BaseCapabilityEvents {
    public static class LivingConsumeLifebloomChargeEvent extends LivingEvent {
        private final EntityLivingBase targetEntity;
        private float amount;
        public LivingConsumeLifebloomChargeEvent(EntityLivingBase sourceEntity, EntityLivingBase targetEntity, float amount) {
            super(sourceEntity);
            this.targetEntity = targetEntity;
            this.amount = amount;
        }

        public EntityLivingBase getSourceEntity() {
            return getEntityLiving();
        }

        public EntityLivingBase getTargetEntity() {
            return targetEntity;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }
}
