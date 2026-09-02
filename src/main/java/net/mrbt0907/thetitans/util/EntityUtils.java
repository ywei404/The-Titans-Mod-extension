package net.mrbt0907.thetitans.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.mrbt0907.thetitans.registries.AttributeRegistry;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class EntityUtils {
    public static final Set<String> GLOBAL_DAMAGE_SOURCE_SET = new HashSet<>();

    private EntityUtils() {
    }

    public static boolean checkEntityAndObjectsValidOnServer(Entity entity, Object... objects) {
        return entity != null && ObjectUtils.allNotNull(objects) && !entity.world.isRemote;
    }

    public static boolean isWearingFullSet(EntityPlayer player, Item[] armors) {
        if (!checkEntityAndObjectsValidOnServer(player, (Object[]) armors)
                || armors.length != 4) {
            return false;
        }

        return player.inventory.armorItemInSlot(EntityEquipmentSlot.HEAD.getIndex()).getItem() == armors[0]
                && player.inventory.armorItemInSlot(EntityEquipmentSlot.CHEST.getIndex()).getItem() == armors[1]
                && player.inventory.armorItemInSlot(EntityEquipmentSlot.LEGS.getIndex()).getItem() == armors[2]
                && player.inventory.armorItemInSlot(EntityEquipmentSlot.FEET.getIndex()).getItem() == armors[3];
    }

    public static void absorptionHeal(EntityLivingBase entity, float amount) {
        if (entity != null && !entity.world.isRemote && amount > 0) {
            IAttributeInstance attributeInstance = entity.getEntityAttribute(AttributeRegistry.MAX_ABSORPTION);

            if (attributeInstance != null) {
                double maxValue = attributeInstance.getAttributeValue();
                float value = entity.getAbsorptionAmount();
                entity.setAbsorptionAmount((float) Math.min(value + amount, maxValue));
            }
        }
    }

    public static EntityEquipmentSlot[] getHandsEquipmentSlots() {
        return new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};
    }

    public static EntityEquipmentSlot[] getArmorEquipmentSlots() {
        return new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
    }

    public static boolean shouldHeal(EntityLivingBase entity) {
        if (entity == null) {
            return false;
        }

        return entity.getHealth() > 0.0F && entity.getHealth() < entity.getMaxHealth();
    }

    public static DamageSource generateGlobalDamageSource(String damageType, Entity immediateSource, Entity trueSource) {
        if (StringUtils.isBlank(damageType)) {
            return null;
        }

        DamageSource damageSource;

        if (immediateSource == null) {
            damageSource = new DamageSource(damageType);
        } else if (trueSource != null) {
            damageSource = new EntityDamageSourceIndirect(damageType, immediateSource, trueSource);
        } else {
            damageSource = new EntityDamageSource(damageType, immediateSource);
        }

        GLOBAL_DAMAGE_SOURCE_SET.add(damageType);

        return damageSource;
    }
}
