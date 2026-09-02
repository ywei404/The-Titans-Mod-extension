package net.mrbt0907.thetitans.event.hook;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.mrbt0907.thetitans.event.dao.entity.BaseEntityEvent;

public class BaseEventHooks {
    public static float onLivingModifyHealth(EntityLivingBase entity, float originalHealth, float health)
    {
        BaseEntityEvent.LivingModifyHealthEvent event = new BaseEntityEvent.LivingModifyHealthEvent(entity, originalHealth, health);
        return (MinecraftForge.EVENT_BUS.post(event) ? 0 : event.getHealth());
    }
}
