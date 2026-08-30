package net.mrbt0907.thetitans.attribute;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

import javax.annotation.Nullable;

public class BaseAttribute extends RangedAttribute {
    public BaseAttribute(@Nullable IAttribute parentIn, String unlocalizedNameIn, double defaultValue, double minimumValueIn, double maximumValueIn) {
        super(parentIn, unlocalizedNameIn, defaultValue, minimumValueIn, maximumValueIn);
    }
}
