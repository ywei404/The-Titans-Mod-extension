package net.mrbt0907.thetitans.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.mrbt0907.thetitans.enchantment.functions.CanApplyTogether;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class BaseEnchantment extends Enchantment {
    public static final Map<String, Enchantment> MOD_ENCHANTMENT_MAP = new HashMap<>();

    private int minLevel;
    private int maxLevel;
    private Function<Integer, Integer> minEnchantabilityFunction;
    private Function<Integer, Integer> maxEnchantabilityFunction;
    public BaseEnchantment(String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
        this.setName(name);
        this.setRegistryName(name);
        MOD_ENCHANTMENT_MAP.put(this.getName(), this);
    }

    @Override
    public int getMinLevel() {
        return minLevel;
    }

    public BaseEnchantment setMinLevel(int minLevel){
        this.minLevel = minLevel;

        return this;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    public BaseEnchantment setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;

        return this;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return minEnchantabilityFunction.apply(enchantmentLevel);
    }

    public BaseEnchantment setMinEnchantabilityFunction(Function<Integer, Integer> minEnchantabilityFunction) {
        this.minEnchantabilityFunction = minEnchantabilityFunction;

        return this;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return maxEnchantabilityFunction.apply(enchantmentLevel);
    }

    public BaseEnchantment setMaxEnchantabilityFunction(Function<Integer, Integer> maxEnchantabilityFunction) {
        this.maxEnchantabilityFunction = maxEnchantabilityFunction;

        return this;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        BiPredicate<BaseEnchantment, Enchantment> enchantmentBiPredicate = CanApplyTogether.CAN_APPLY_TOGETHER_IMPLEMENTS.get(this);

        if (enchantmentBiPredicate != null){
            return enchantmentBiPredicate.test(this, ench);
        }

        return super.canApplyTogether(ench);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }

        BiPredicate<Enchantment, ItemStack> enchantmentBiPredicate = CanApplyTogether.CAN_APPLY_IMPLEMENTS.get(this);

        if (enchantmentBiPredicate != null){
            return enchantmentBiPredicate.test(this, stack);
        }

        return super.canApply(stack);
    }
}
