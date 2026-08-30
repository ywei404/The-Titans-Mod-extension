package net.mrbt0907.thetitans.attribute.dao;

import net.minecraft.entity.ai.attributes.IAttribute;

public class AttributeData {
    private final IAttribute attribute;
    private final double amountIn;
    private final int operationIn;

    private AttributeData(IAttribute attribute, double amountIn, int operationIn) {
        this.attribute = attribute;
        this.amountIn = amountIn;
        this.operationIn = operationIn;
    }

    public static AttributeData of(IAttribute attribute, double amountIn, int operationIn){
        return new AttributeData(attribute, amountIn, operationIn);
    }

    public IAttribute getAttribute() {
        return attribute;
    }

    public double getAmountIn() {
        return amountIn;
    }

    public int getOperationIn() {
        return operationIn;
    }
}
