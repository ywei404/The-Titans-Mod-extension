package net.mrbt0907.thetitans.registries;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.thetitans.capability.entity.ILifebloomCharge;
import net.mrbt0907.thetitans.capability.entity.impl.LifebloomCharge;
import net.mrbt0907.thetitans.capability.entity.provider.LifebloomChargeProvider;
import net.mrbt0907.thetitans.capability.entity.storage.LifebloomChargeStorage;

@Mod.EventBusSubscriber
public class CapabilityRegistry {
    @CapabilityInject(ILifebloomCharge.class)
    public static Capability<ILifebloomCharge> LIFEBLOOM_CHARGE = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(ILifebloomCharge.class, new LifebloomChargeStorage(), LifebloomCharge::new);
    }

    @SubscribeEvent
    public static void attach(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();

        if (entity instanceof EntityPlayer) {
            event.addCapability(
                    new ResourceLocation(TheTitans.MODID, "lifebloom_charge"),
                    new LifebloomChargeProvider(((EntityPlayer) entity))
            );
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void watcher(LivingHealEvent event) {
        EntityLivingBase entityLiving = event.getEntityLiving();

        if (entityLiving instanceof EntityPlayer && !entityLiving.world.isRemote) {
            ILifebloomCharge lifebloomCharge = entityLiving.getCapability(LIFEBLOOM_CHARGE, null);

            if (lifebloomCharge != null) {
                System.out.println("Lifebloom Charge: " + lifebloomCharge.getCharge());
            }
        }
    }
}
