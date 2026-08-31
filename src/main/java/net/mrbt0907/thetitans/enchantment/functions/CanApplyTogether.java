package net.mrbt0907.thetitans.enchantment.functions;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.mrbt0907.thetitans.enchantment.BaseEnchantment;
import net.mrbt0907.thetitans.registries.EnchantmentRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

import static net.minecraft.init.Enchantments.*;

public class CanApplyTogether {
    public static final Map<Enchantment, BiPredicate<Enchantment, ItemStack>> CAN_APPLY_IMPLEMENTS = new HashMap<>();
    public static final Map<Enchantment, BiPredicate<BaseEnchantment, Enchantment>> CAN_APPLY_TOGETHER_IMPLEMENTS = new HashMap<>();


    public static void addFunctionsToMap() {
        CAN_APPLY_TOGETHER_IMPLEMENTS.put(EnchantmentRegistry.HEALING, (baseEnchantment, enchantment) -> !Lists.newArrayList(
                PROTECTION,
                BLAST_PROTECTION,
                PROJECTILE_PROTECTION,
                FIRE_PROTECTION
        ).contains(enchantment));
    }
}
