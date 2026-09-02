package net.mrbt0907.thetitans.event.hook;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.mrbt0907.thetitans.event.dao.capability.BaseCapabilityEvents;
import net.mrbt0907.thetitans.event.dao.entity.BaseEntityEvent;

public class BaseEventHooks {
    public static float onLivingModifyHealth(EntityLivingBase entity, float originalHealth, float health) {
        BaseEntityEvent.LivingModifyHealthEvent event = new BaseEntityEvent.LivingModifyHealthEvent(entity, originalHealth, health);
        return (MinecraftForge.EVENT_BUS.post(event) ? 0 : event.getHealth());
    }

    public static float onLivingConsumeLifebloomCharge(EntityLivingBase sourceEntity, EntityLivingBase targetEntity, float amount) {
        BaseCapabilityEvents.LivingConsumeLifebloomChargeEvent event = new BaseCapabilityEvents.LivingConsumeLifebloomChargeEvent(sourceEntity, targetEntity, amount);
        return (MinecraftForge.EVENT_BUS.post(event) ? 0 : event.getAmount());
    }
}
