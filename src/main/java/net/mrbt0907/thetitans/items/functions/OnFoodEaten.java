package net.mrbt0907.thetitans.items.functions;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.mrbt0907.thetitans.registries.ItemRegistry;
import net.mrbt0907.thetitans.registries.PotionRegistry;
import org.apache.logging.log4j.util.TriConsumer;

public class OnFoodEaten {
    public static final Multimap<ItemFood, TriConsumer<ItemStack, World, EntityPlayer>> ON_FOOD_EATEN_IMPLEMENTS = HashMultimap.create();

    public static void addFunctionsToMap() {
        ON_FOOD_EATEN_IMPLEMENTS.put(ItemRegistry.DIAMOND_APPLE, (itemStack, world, player) -> {
            if (itemStack.getMetadata() > 0) {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 800, 5));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 12000, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 12000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 4800, 7));
                player.addPotionEffect(new PotionEffect(PotionRegistry.ABSORPTION_REGENERATION, 4800, 2));

            } else {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 2));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 4800, 1));
            }
        });
    }
}
