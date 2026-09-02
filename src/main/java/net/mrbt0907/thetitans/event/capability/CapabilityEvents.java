package net.mrbt0907.thetitans.event.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.capability.entity.ILifebloomCharge;
import net.mrbt0907.thetitans.event.hook.BaseEventHooks;
import net.mrbt0907.thetitans.registries.AttributeRegistry;
import net.mrbt0907.thetitans.registries.CapabilityRegistry;
import net.mrbt0907.thetitans.util.DamageSources;
import net.mrbt0907.thetitans.util.EntityUtils;

@Mod.EventBusSubscriber
public class CapabilityEvents {
    @SubscribeEvent
    public static void chargeLifebloom(LivingHealEvent event) {
        EntityLivingBase entityLiving = event.getEntityLiving();
        float amount = event.getAmount();

        if (entityLiving instanceof EntityPlayer && !entityLiving.world.isRemote && amount > 0) {
            amount = Math.min(amount, entityLiving.getMaxHealth() - entityLiving.getHealth());
            ILifebloomCharge lifebloomCharge = entityLiving.getCapability(CapabilityRegistry.LIFEBLOOM_CHARGE, null);

            if (lifebloomCharge == null) {
                return;
            }

            float chargeEfficiency = 1.0F;
            IAttributeInstance attributeInstance = entityLiving.getEntityAttribute(AttributeRegistry.LIFEBLOOM_CHARGE_EFFICIENCY);

            if (attributeInstance != null) {
                chargeEfficiency = (float) attributeInstance.getAttributeValue();
            }

            lifebloomCharge.addCharge(amount * chargeEfficiency);
        }
    }

    @SubscribeEvent
    public static void breakLifebloomAndHurt(LivingHurtEvent event) {
        EntityLivingBase targetEntity = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity sourceEntity = source.getImmediateSource();
        String damageType = source.getDamageType();
        EntityDamageSource entityDamageSource = null;

        if (source instanceof EntityDamageSource) {
            entityDamageSource = ((EntityDamageSource) source);
        }

        if (sourceEntity instanceof EntityPlayer
                && !targetEntity.world.isRemote
                && !DamageSources.LIFEBLOOM_DAMAGE_NAME.equals(damageType)
                && (entityDamageSource == null || !entityDamageSource.getIsThornsDamage())) {

            ILifebloomCharge lifebloomCharge = sourceEntity.getCapability(CapabilityRegistry.LIFEBLOOM_CHARGE, null);

            if (lifebloomCharge == null) {
                return;
            }

            float charge = lifebloomCharge.getCharge();

            if (charge > 0.0F) {
                charge = BaseEventHooks.onLivingConsumeLifebloomCharge((EntityLivingBase) sourceEntity, targetEntity, charge);
                lifebloomCharge.consumeCharge(charge);
                targetEntity.attackEntityFrom(EntityUtils.generateGlobalDamageSource(DamageSources.LIFEBLOOM_DAMAGE_NAME, sourceEntity, null), charge);
            }

        }
    }
}
