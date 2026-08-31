package net.mrbt0907.thetitans.registries;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.thetitans.potion.BasePotion;
import net.mrbt0907.thetitans.util.PotionUtils;

public class PotionRegistry {
    private static RegistryEvent.Register<Potion> registry;
    public static final Potion ABSORPTION_REGENERATION = new BasePotion("absorption_regeneration", false, PotionUtils.YELLOW);

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Potion> event) {
        TheTitans.debug("Registering potions...");
        registry = event;

        BasePotion.MOD_POTION_MAP.forEach((s, potion) -> registry.getRegistry().register(potion));
    }
}
