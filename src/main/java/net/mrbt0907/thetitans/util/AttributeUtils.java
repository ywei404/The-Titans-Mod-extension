package net.mrbt0907.thetitans.util;

import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.mrbt0907.thetitans.attribute.dao.AttributeData;
import net.mrbt0907.thetitans.items.functions.ItemAttributeInstanceUUID;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class AttributeUtils {
    private AttributeUtils() {
    }

    public static void addAttributeInstance(Multimap<String, AttributeModifier> modifiers,
                                            String uuidNamePrefix,
                                            List<AttributeData> value) {
        if (ObjectUtils.allNotNull(modifiers, uuidNamePrefix, value) && StringUtils.isNotBlank(uuidNamePrefix)) {
            for (AttributeData attributeDoubleInteger : value) {
                String attributeName = attributeDoubleInteger.getAttribute().getName();
                String uuidName = uuidNamePrefix + "_" + attributeName;

                modifiers.put(attributeName, new AttributeModifier(
                        ItemAttributeInstanceUUID.createUUID(uuidName),
                        uuidName,
                        attributeDoubleInteger.getAmountIn(),
                        attributeDoubleInteger.getOperationIn()
                ));
            }
        }
    }
}
