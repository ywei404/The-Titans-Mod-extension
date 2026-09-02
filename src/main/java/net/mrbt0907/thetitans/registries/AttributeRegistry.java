package net.mrbt0907.thetitans.registries;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.attribute.BaseAttribute;

@Mod.EventBusSubscriber
public class AttributeRegistry {
    public static final IAttribute MAX_ABSORPTION = new BaseAttribute(null, "titan.maxAbsorption", 0.0, 0.0, 1024.0);
    public static final IAttribute MAX_LIFEBLOOM_RATIO = new BaseAttribute(null, "titan.maxLifebloom", 5.0, 0.0, 1024.0);
    public static final IAttribute LIFEBLOOM_CHARGE_EFFICIENCY = new BaseAttribute(null, "titan.lifebloomChargeEfficiency", 1.0, 0.0, 1024.0);

    @SubscribeEvent
    public static void register(EntityEvent.EntityConstructing event) {
        Entity entity = event.getEntity();

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

            BaseAttribute.MOD_ATTRIBUTE_MAP.forEach((name, iAttribute) -> {
                entityLivingBase.getAttributeMap().registerAttribute(iAttribute);
//                System.out.println("Register mod attribute " + name);
            });

        }
    }

    @SubscribeEvent
    public static void watcher(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof EntityPlayer && !entity.world.isRemote) {
            EntityPlayer entityPlayer = (EntityPlayer) entity;

            BaseAttribute.MOD_ATTRIBUTE_MAP.forEach((s, iAttribute) -> {
                IAttributeInstance attributeInstance = entityPlayer.getEntityAttribute(iAttribute);

                if (attributeInstance != null) {
//                    System.out.println(attributeInstance.getAttribute().getName() + ":" + attributeInstance.getAttributeValue());
                }
            });
        }
    }
}
