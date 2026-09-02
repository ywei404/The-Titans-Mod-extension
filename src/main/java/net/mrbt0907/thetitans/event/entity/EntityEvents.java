package net.mrbt0907.thetitans.event.entity;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.event.dao.entity.BaseEntityEvent;

@Mod.EventBusSubscriber
public class EntityEvents {
    @SubscribeEvent
    public static void test(BaseEntityEvent.LivingModifyHealthEvent event) {
        System.out.println("Original Health: " + event.getOriginalHealth());
        System.out.println("Health: " + event.getHealth());
        System.out.println("EntityLiving: " + event.getEntityLiving());
    }
}
