package net.mrbt0907.thetitans.event.attribute;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.attribute.dao.AttributeData;
import net.mrbt0907.thetitans.registries.AttributeRegistry;
import net.mrbt0907.thetitans.util.AttributeUtils;

@Mod.EventBusSubscriber
public class AttributeEvents {
    @SubscribeEvent
    public static void setMaxAbsorptionWhenAddingAbsorption(PotionEvent.PotionAddedEvent event) {
        PotionEffect potionEffect = event.getPotionEffect();
        Potion potion = potionEffect.getPotion();
        EntityLivingBase entityLiving = event.getEntityLiving();

        if (potion == MobEffects.ABSORPTION && !entityLiving.world.isRemote) {
            AbstractAttributeMap attributeMap = entityLiving.getAttributeMap();
            int level = potionEffect.getAmplifier() + 1;

            AttributeUtils.addAttributeInstance(attributeMap,
                    potion.getName(),
                    Lists.newArrayList(AttributeData.of(AttributeRegistry.MAX_ABSORPTION, 4.0 * level, Constants.AttributeModifierOperation.ADD)));
        }
    }

//    @SubscribeEvent
//    public static void setMaxAbsorptionWhenRemovingAbsorption(PotionEvent.PotionRemoveEvent event) {
//        PotionEffect potionEffect = event.getPotionEffect();
//        Potion potion = potionEffect.getPotion();
//
//        if (potion == MobEffects.ABSORPTION) {
//            EntityLivingBase entityLiving = event.getEntityLiving();
//            AbstractAttributeMap attributeMap = entityLiving.getAttributeMap();
//            int level = potionEffect.getAmplifier() + 1;
//
//            AttributeUtils.addAttributeInstance(attributeMap,
//                    potion.getName(),
//                    Lists.newArrayList(AttributeData.of(AttributeRegistry.MAX_ABSORPTION, 4.0 * level, Constants.AttributeModifierOperation.ADD)));
//        }
//    }

    public static void restrictAbsorptionAmount(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof EntityLivingBase && !entity.world.isRemote) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            IAttributeInstance attributeInstance = entityLivingBase.getEntityAttribute(AttributeRegistry.MAX_ABSORPTION);

            if (attributeInstance != null) {
                double maxValue = attributeInstance.getAttributeValue();
                float value = entityLivingBase.getAbsorptionAmount();

                if (value > maxValue) {
                    entityLivingBase.setAbsorptionAmount((float) maxValue);
                }
            }
        }
    }
}
