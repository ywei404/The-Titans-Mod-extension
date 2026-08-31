package net.mrbt0907.thetitans.util;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.mrbt0907.thetitans.attribute.dao.AttributeData;
import net.mrbt0907.thetitans.items.functions.ItemAttributeInstanceUUID;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.function.BiPredicate;

public class AttributeUtils {
    private AttributeUtils() {
    }

    private static Multimap<UUID, Boolean> attributeInstanceToMap(
            Multimap<String, AttributeModifier> modifiers,
            String uuidNamePrefix,
            List<AttributeData> value,
            BiPredicate<String, AttributeModifier> function) {

        Multimap<UUID, Boolean> results = HashMultimap.create();

        if (ObjectUtils.allNotNull(modifiers, value)
                && StringUtils.isNotBlank(uuidNamePrefix)
                && !value.isEmpty()) {

            for (AttributeData attributeDoubleInteger : value) {
                String attributeName = attributeDoubleInteger.getAttribute().getName();
                String uuidName = uuidNamePrefix + "_" + attributeName;
                UUID uuid = ItemAttributeInstanceUUID.createUUID(uuidName);

                results.put(uuid, function.test(attributeName, new AttributeModifier(
                        uuid,
                        uuidName,
                        attributeDoubleInteger.getAmountIn(),
                        attributeDoubleInteger.getOperationIn()
                )));
            }
        }

        return results;
    }

    public static void addAttributeInstance(Multimap<String, AttributeModifier> modifiers,
                                            String uuidNamePrefix,
                                            List<AttributeData> value) {

        attributeInstanceToMap(modifiers, uuidNamePrefix, value, modifiers::put);
    }

    public static void addAttributeInstance(AbstractAttributeMap attributeMap,
                                            String uuidNamePrefix,
                                            List<AttributeData> value) {

        if (attributeMap != null) {
            Multimap<String, AttributeModifier> modifiers = HashMultimap.create();
            attributeInstanceToMap(modifiers, uuidNamePrefix, value, modifiers::put);
            attributeMap.applyAttributeModifiers(modifiers);
        }
    }

    public static void removeAttributeInstance(AbstractAttributeMap attributeMap,
                                               String uuidNamePrefix,
                                               List<AttributeData> value) {

        if (attributeMap != null) {
            Multimap<String, AttributeModifier> modifiers = HashMultimap.create();
            attributeInstanceToMap(modifiers, uuidNamePrefix, value, modifiers::put);
            attributeMap.removeAttributeModifiers(modifiers);
        }
    }
}
