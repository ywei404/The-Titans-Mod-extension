package net.mrbt0907.thetitans.event.capability;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.capability.entity.ILifebloomCharge;
import net.mrbt0907.thetitans.registries.CapabilityRegistry;

@Mod.EventBusSubscriber
public class CapabilityEvents {
    @SubscribeEvent
    public static void chargeLifebloom(LivingHealEvent event){
        EntityLivingBase entityLiving = event.getEntityLiving();
        float amount = event.getAmount();

        if (entityLiving instanceof EntityPlayer && amount > 0){
            amount = Math.min(amount, entityLiving.getMaxHealth() - entityLiving.getHealth());

            ILifebloomCharge capability = entityLiving.getCapability(CapabilityRegistry.LIFEBLOOM_CHARGE, null);
            capability.addCharge(amount);
        }
    }
}
