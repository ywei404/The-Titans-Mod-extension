package net.mrbt0907.thetitans.potion;

import net.minecraft.potion.Potion;

import java.util.HashMap;
import java.util.Map;

public class BasePotion extends Potion {
    public static final Map<String, Potion> MOD_POTION_MAP = new HashMap<>();

    public BasePotion(String name, boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        setPotionName(name);
        setRegistryName(name);
        MOD_POTION_MAP.put(this.getName(), this);
    }
}
