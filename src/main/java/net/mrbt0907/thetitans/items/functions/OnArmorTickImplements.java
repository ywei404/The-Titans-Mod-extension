package net.mrbt0907.thetitans.items.functions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.mrbt0907.thetitans.registries.ItemRegistry;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.HashMap;
import java.util.Map;

public class OnArmorTickImplements {
    public static final Map<Item, TriConsumer<World, EntityPlayer, ItemStack>> ON_ARMOR_TICK_IMPLEMENTS = new HashMap<>();

    public static void addFunctionsToMap() {
        ON_ARMOR_TICK_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[0], (world, entityPlayer, itemStack) -> {
            entityPlayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 600, 0));
        });
    }
}
