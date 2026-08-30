package net.mrbt0907.thetitans.attribute;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BaseAttribute extends RangedAttribute {
    public static final Map<String, IAttribute> MOD_ATTRIBUTE_MAP = new HashMap<>();

    public BaseAttribute(@Nullable IAttribute parentIn, String unlocalizedNameIn, double defaultValue, double minimumValueIn, double maximumValueIn) {
        super(parentIn, unlocalizedNameIn, defaultValue, minimumValueIn, maximumValueIn);
        MOD_ATTRIBUTE_MAP.put(unlocalizedNameIn, this);
    }
}
