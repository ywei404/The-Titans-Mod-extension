package net.mrbt0907.thetitans.event.dao.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;

public class BaseEntityEvent {
    public static class LivingModifyHealthEvent extends LivingEvent {
        private final float originalHealth;
        private float health;
        public LivingModifyHealthEvent(EntityLivingBase entity, float originalHealth, float health) {
            super(entity);
            this.originalHealth = originalHealth;
            this.health = health;
        }

        public float getOriginalHealth() {
            return originalHealth;
        }

        public float getHealth() {
            return health;
        }

        public void setHealth(float health) {
            this.health = health;
        }
    }
}
