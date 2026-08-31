package net.mrbt0907.thetitans.event.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.registries.EnchantmentRegistry;

@Mod.EventBusSubscriber
public class EnchantmentEvents {
    @SubscribeEvent
    public static void absorptionRegenerationEvent(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();
        World world = entity.world;

        if (entity instanceof EntityLivingBase && !world.isRemote) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            Iterable<ItemStack> armor = entityLivingBase.getArmorInventoryList();
            Enchantment enchantment = EnchantmentRegistry.HEALING;
            int maxLevel = 0;

            for (ItemStack stack : armor) {
                maxLevel = Math.max(maxLevel, EnchantmentHelper.getEnchantmentLevel(enchantment, stack));
                // TODO: replace with method in EnchantmentData class
            }

            if (maxLevel > 0) {
                int interval = Math.max(1, 80 >> Math.min(maxLevel - 1, enchantment.getMaxLevel() - 1));

                if (world.getWorldTime() % interval == 0) {
                    entityLivingBase.heal(1.0F);
                }
            }
        }
    }
}
