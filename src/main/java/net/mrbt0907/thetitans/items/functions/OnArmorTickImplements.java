package net.mrbt0907.thetitans.items.functions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.mrbt0907.thetitans.registries.ItemRegistry;
import net.mrbt0907.thetitans.util.EntityUtils;
import net.mrbt0907.thetitans.util.PotionUtils;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnArmorTickImplements {
    public static final Map<Item, TriConsumer<World, EntityPlayer, ItemStack>> ON_ARMOR_TICK_IMPLEMENTS = new HashMap<>();

    public static final Map<List<Item>, TriConsumer<World, EntityPlayer, ItemStack>> ON_FULL_SET_ARMOR_TICK_IMPLEMENTS = new HashMap<>();


    public static void addFunctionsToMap() {
        ON_ARMOR_TICK_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[0], (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.NIGHT_VISION, 800), 300);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.WATER_BREATHING, 800), 1);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.BLINDNESS);
        });

        ON_ARMOR_TICK_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[1], (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.HASTE, 800, 3), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.RESISTANCE, 800, 3), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.STRENGTH, 800, 9), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.FIRE_RESISTANCE, 800), 1);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.WEAKNESS);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.MINING_FATIGUE);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.WITHER);
        });

        ON_ARMOR_TICK_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[2], (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.LUCK, 800, 4), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.ABSORPTION, 800, 3), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.REGENERATION, 800, 1), 1);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.NAUSEA);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.HUNGER);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.POISON);
        });

        ON_ARMOR_TICK_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[3], (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.JUMP_BOOST, 800, 3), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.SPEED, 800, 3), 1);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.SLOWNESS);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.LEVITATION);
        });

        ON_FULL_SET_ARMOR_TICK_IMPLEMENTS.put(Arrays.asList(ItemRegistry.HARCADIUM_ARMOR_SET), new TriConsumer<World, EntityPlayer, ItemStack>() {
            @Override
            public void accept(World world, EntityPlayer entityPlayer, ItemStack itemStack) {
                if (EntityUtils.isWearingFullSet(entityPlayer, ItemRegistry.HARCADIUM_ARMOR_SET)){
                    entityPlayer.extinguish();
                    PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.REGENERATION, 800, 4), 1);
                    PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.ABSORPTION, 2400, 19), 1);
                    PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.HEALTH_BOOST, 6000, 9), 1);
                    PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.SATURATION, 800, 0), 1);
                }
            }
        });
    }
}
