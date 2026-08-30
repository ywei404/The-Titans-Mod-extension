package net.mrbt0907.thetitans.items.functions;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.Constants;
import net.mrbt0907.thetitans.attribute.dao.AttributeData;
import net.mrbt0907.thetitans.registries.ItemRegistry;
import net.mrbt0907.thetitans.util.AttributeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class GetItemAttributeModifiersImplements {
    public static final Map<Item, BiConsumer<EntityEquipmentSlot, Multimap<String, AttributeModifier>>> GET_ITEM_ATTRIBUTE_MODIFIER_IMPLEMENTS = new HashMap<>();

    public static void addFunctionsToMap() {
        GET_ITEM_ATTRIBUTE_MODIFIER_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[0], (entityEquipmentSlot, modifiers) -> {
            String uuidNamePrefix = ItemRegistry.HARCADIUM_ARMOR_SET[0].getUnlocalizedName();

            ArrayList<AttributeData> values = Lists.newArrayList(
                    AttributeData.of(EntityPlayer.REACH_DISTANCE, 1.0, Constants.AttributeModifierOperation.ADD),
                    AttributeData.of(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, 1.0, Constants.AttributeModifierOperation.ADD),
                    AttributeData.of(SharedMonsterAttributes.MAX_HEALTH, 0.09, Constants.AttributeModifierOperation.ADD_MULTIPLE),
                    AttributeData.of(SharedMonsterAttributes.ATTACK_DAMAGE, 0.09, Constants.AttributeModifierOperation.ADD_MULTIPLE),
                    AttributeData.of(SharedMonsterAttributes.ATTACK_SPEED, 0.09, Constants.AttributeModifierOperation.ADD_MULTIPLE)
            );

            AttributeUtils.addAttributeInstance(modifiers, uuidNamePrefix, values);
        });

        GET_ITEM_ATTRIBUTE_MODIFIER_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[1], (entityEquipmentSlot, modifiers) -> {
            String uuidNamePrefix = ItemRegistry.HARCADIUM_ARMOR_SET[1].getUnlocalizedName();

            ArrayList<AttributeData> values = Lists.newArrayList(
                    AttributeData.of(EntityPlayer.REACH_DISTANCE, 1.0, Constants.AttributeModifierOperation.ADD),
                    AttributeData.of(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, 1.0, Constants.AttributeModifierOperation.ADD),
                    AttributeData.of(SharedMonsterAttributes.MAX_HEALTH, 0.13, Constants.AttributeModifierOperation.ADD_MULTIPLE),
                    AttributeData.of(SharedMonsterAttributes.ATTACK_DAMAGE, 0.13, Constants.AttributeModifierOperation.ADD_MULTIPLE),
                    AttributeData.of(SharedMonsterAttributes.ATTACK_SPEED, 0.13, Constants.AttributeModifierOperation.ADD_MULTIPLE)
            );

            AttributeUtils.addAttributeInstance(modifiers, uuidNamePrefix, values);
        });

        GET_ITEM_ATTRIBUTE_MODIFIER_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[2], (entityEquipmentSlot, modifiers) -> {
            String uuidNamePrefix = ItemRegistry.HARCADIUM_ARMOR_SET[2].getUnlocalizedName();

            ArrayList<AttributeData> values = Lists.newArrayList(
                    AttributeData.of(EntityPlayer.REACH_DISTANCE, 1.0, Constants.AttributeModifierOperation.ADD),
                    AttributeData.of(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, 1.0, Constants.AttributeModifierOperation.ADD),
                    AttributeData.of(SharedMonsterAttributes.MAX_HEALTH, 0.10, Constants.AttributeModifierOperation.ADD_MULTIPLE),
                    AttributeData.of(SharedMonsterAttributes.ATTACK_DAMAGE, 0.10, Constants.AttributeModifierOperation.ADD_MULTIPLE),
                    AttributeData.of(SharedMonsterAttributes.ATTACK_SPEED, 0.10, Constants.AttributeModifierOperation.ADD_MULTIPLE)
            );

            AttributeUtils.addAttributeInstance(modifiers, uuidNamePrefix, values);
        });

        GET_ITEM_ATTRIBUTE_MODIFIER_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[3], (entityEquipmentSlot, modifiers) -> {
            String uuidNamePrefix = ItemRegistry.HARCADIUM_ARMOR_SET[3].getUnlocalizedName();

            ArrayList<AttributeData> values = Lists.newArrayList(
                    AttributeData.of(EntityPlayer.REACH_DISTANCE, 1.0, Constants.AttributeModifierOperation.ADD),
                    AttributeData.of(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, 1.0, Constants.AttributeModifierOperation.ADD),
                    AttributeData.of(SharedMonsterAttributes.MAX_HEALTH, 0.08, Constants.AttributeModifierOperation.ADD_MULTIPLE),
                    AttributeData.of(SharedMonsterAttributes.ATTACK_DAMAGE, 0.08, Constants.AttributeModifierOperation.ADD_MULTIPLE),
                    AttributeData.of(SharedMonsterAttributes.ATTACK_SPEED, 0.08, Constants.AttributeModifierOperation.ADD_MULTIPLE)
            );

            AttributeUtils.addAttributeInstance(modifiers, uuidNamePrefix, values);
        });
    }
}
