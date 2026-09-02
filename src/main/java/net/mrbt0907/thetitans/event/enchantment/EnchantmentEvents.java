package net.mrbt0907.thetitans.event.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.thetitans.items.functions.ItemAttributeInstanceUUID;
import net.mrbt0907.thetitans.registries.EnchantmentRegistry;
import net.mrbt0907.thetitans.util.AttributeUtils;
import net.mrbt0907.thetitans.util.EnchantmentUtils;
import net.mrbt0907.thetitans.util.EntityUtils;
import net.mrbt0907.thetitans.util.UUIDUtils;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Mod.EventBusSubscriber
public class EnchantmentEvents {
    @SubscribeEvent
    public static void regenerateAbsorptionAmount(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();
        World world = entity.world;

        if (entity instanceof EntityLivingBase && !world.isRemote) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            Iterable<ItemStack> armor = entityLivingBase.getArmorInventoryList();
            Enchantment enchantment = EnchantmentRegistry.HEALING;
            int totalLevel = 0;

            for (ItemStack stack : armor) {
                totalLevel += EnchantmentUtils.getEnchantmentLevel(enchantment, stack);
            }

            if (totalLevel > 0) {
                int interval = Math.max(1, 80 / totalLevel);

                if (world.getWorldTime() % interval == 0 && EntityUtils.shouldHeal(entityLivingBase)) {
                    entityLivingBase.heal(1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void addVibrantMaxHealth(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entityLivingBase = event.getEntityLiving();

        if (!entityLivingBase.world.isRemote) {
            Enchantment enchantment = EnchantmentRegistry.VIBRANT;

            Arrays.stream(EntityEquipmentSlot.values())
                    .filter(entityEquipmentSlot -> entityEquipmentSlot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
                    .forEach(entityEquipmentSlot -> {
                        ItemStack itemStack = entityLivingBase.getItemStackFromSlot(entityEquipmentSlot);
                        int level = EnchantmentUtils.getEnchantmentLevel(enchantment, itemStack);
                        String name = enchantment.getName() + "." + entityEquipmentSlot.getName();

                        itemStack.addAttributeModifier(
                                SharedMonsterAttributes.MAX_HEALTH.getName(),
                                new AttributeModifier(ItemAttributeInstanceUUID.createUUID(name), name, 4 * level, Constants.AttributeModifierOperation.ADD),
                                entityEquipmentSlot
                        );
                    });
        }
    }
}
