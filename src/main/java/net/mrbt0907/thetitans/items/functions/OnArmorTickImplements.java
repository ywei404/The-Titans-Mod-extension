package net.mrbt0907.thetitans.items.functions;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.mrbt0907.thetitans.enchantment.dao.EnchantmentData;
import net.mrbt0907.thetitans.registries.ItemRegistry;
import net.mrbt0907.thetitans.util.EntityUtils;
import net.mrbt0907.thetitans.util.PotionUtils;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.*;
import java.util.function.Consumer;

public class OnArmorTickImplements {
    public static final Multimap<Item, TriConsumer<World, EntityPlayer, ItemStack>> ON_ARMOR_TICK_IMPLEMENTS = HashMultimap.create();

    public static final Multimap<List<Item>, TriConsumer<World, EntityPlayer, ItemStack>> ON_FULL_SET_ARMOR_TICK_IMPLEMENTS = HashMultimap.create();


    public static void addFunctionsToMap() {
        final Consumer<ItemStack> harcadiumEnchantments = itemStack -> {
            EnchantmentData.of(Enchantments.PROTECTION, 4).addEnchantment(itemStack);
            EnchantmentData.of(Enchantments.FIRE_PROTECTION, 4).addEnchantment(itemStack);
            EnchantmentData.of(Enchantments.PROJECTILE_PROTECTION, 4).addEnchantment(itemStack);
            EnchantmentData.of(Enchantments.BLAST_PROTECTION, 4).addEnchantment(itemStack);
            EnchantmentData.of(Enchantments.UNBREAKING, 3).addEnchantment(itemStack);
            EnchantmentData.of(Enchantments.MENDING, 1).addEnchantment(itemStack);
            EnchantmentData.of(Enchantments.THORNS, 3).addEnchantment(itemStack);
        };

        ON_ARMOR_TICK_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[0], (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.NIGHT_VISION, 800), 300);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.WATER_BREATHING, 800), 1);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.BLINDNESS);

            harcadiumEnchantments.accept(itemStack);

            EnchantmentData.of(Enchantments.RESPIRATION, 3).addEnchantment(itemStack);
            EnchantmentData.of(Enchantments.AQUA_AFFINITY, 1).addEnchantment(itemStack);
        });

        ON_ARMOR_TICK_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[1], (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.HASTE, 800, 3), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.RESISTANCE, 800, 3), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.STRENGTH, 800, 9), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.FIRE_RESISTANCE, 800), 1);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.WEAKNESS);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.MINING_FATIGUE);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.WITHER);

            harcadiumEnchantments.accept(itemStack);
        });

        ON_ARMOR_TICK_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[2], (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.LUCK, 800, 4), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.ABSORPTION, 800, 3), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.REGENERATION, 800, 1), 1);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.NAUSEA);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.HUNGER);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.POISON);

            harcadiumEnchantments.accept(itemStack);
        });

        ON_ARMOR_TICK_IMPLEMENTS.put(ItemRegistry.HARCADIUM_ARMOR_SET[3], (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.JUMP_BOOST, 800, 3), 1);
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.SPEED, 800, 3), 1);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.SLOWNESS);
            PotionUtils.removePotionEffect(entityPlayer, MobEffects.LEVITATION);

            harcadiumEnchantments.accept(itemStack);

            EnchantmentData.of(Enchantments.FEATHER_FALLING, 4).addEnchantment(itemStack);
            EnchantmentData.of(Enchantments.DEPTH_STRIDER, 3).addEnchantment(itemStack);
        });

        ON_FULL_SET_ARMOR_TICK_IMPLEMENTS.put(Arrays.asList(ItemRegistry.HARCADIUM_ARMOR_SET), (world, entityPlayer, itemStack) -> {
            if (EntityUtils.isWearingFullSet(entityPlayer, ItemRegistry.HARCADIUM_ARMOR_SET)){
                entityPlayer.extinguish();
                PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.REGENERATION, 800, 4), 1);
                PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.ABSORPTION, 2400, 19), 1);
                PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.HEALTH_BOOST, 6000, 9), 1);
                PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.SATURATION, 800, 0), 1);

                EnchantmentData.getAllEnchantmentData(itemStack).forEach(enchantmentData -> {
                    Enchantment enchantment = enchantmentData.getEnchantment();

                    if (enchantment.isCurse()){
                        EnchantmentData.removeEnchantments(itemStack, enchantment);
                    }
                });
            }
        });

        final TriConsumer<World, EntityPlayer, ItemStack> woodenArmorFireFunction = (world, entityPlayer, itemStack) -> {
            if (!entityPlayer.world.isRemote
                    && entityPlayer.isBurning()
                    && entityPlayer.getActivePotionEffect(MobEffects.FIRE_RESISTANCE) == null){
                entityPlayer.setFire(15); // the fire on entity wearing wooden armor would not be extinguished by itself
            }

            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.HUNGER, 800, 0), 700);
        };

        for (Item item : ItemRegistry.WOODEN_ARMOR_SET) {
            ON_ARMOR_TICK_IMPLEMENTS.put(item, woodenArmorFireFunction);
        }

        final TriConsumer<World, EntityPlayer, ItemStack> stoneArmorFireFunction = (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.SLOWNESS, 800, 0), 700);
        };

        for (Item item : ItemRegistry.STONE_ARMOR_SET) {
            ON_ARMOR_TICK_IMPLEMENTS.put(item, stoneArmorFireFunction);
        }

        final TriConsumer<World, EntityPlayer, ItemStack> obsidianArmorFireFunction = (world, entityPlayer, itemStack) -> {
            PotionUtils.addPotionEffect(entityPlayer, new PotionEffect(MobEffects.SLOWNESS, 800, 0), 700);
        };

        for (Item item : ItemRegistry.OBSIDIAN_ARMOR_SET) {
            ON_ARMOR_TICK_IMPLEMENTS.put(item, obsidianArmorFireFunction);
        }
    }
}
