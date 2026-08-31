package net.mrbt0907.thetitans.registries;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.thetitans.enchantment.BaseEnchantment;
import net.mrbt0907.thetitans.enchantment.functions.CanApplyTogether;
import net.mrbt0907.thetitans.util.EntityUtils;

public class EnchantmentRegistry {
    private static RegistryEvent.Register<Enchantment> registry;

    public static final Enchantment HEALING = new BaseEnchantment("healing", Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.ARMOR, EntityUtils.getArmorEquipmentSlots())
            .setMinLevel(1).setMaxLevel(4)
            .setMinEnchantabilityFunction(level -> 5 + 15 * (level - 1))
            .setMaxEnchantabilityFunction(level -> 15 + 15 * (level - 1));

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Enchantment> event) {
        TheTitans.debug("Registering enchantments...");
        registry = event;
        registry.getRegistry().registerAll(BaseEnchantment.MOD_ENCHANTMENT_MAP.values().toArray(new Enchantment[]{}));

        CanApplyTogether.addFunctionsToMap();
        TheTitans.debug("Added canApply and canApplyTogether functions to map");
    }
}
