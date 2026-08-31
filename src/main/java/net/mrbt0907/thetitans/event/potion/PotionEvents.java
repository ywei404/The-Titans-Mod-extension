package net.mrbt0907.thetitans.event.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.registries.PotionRegistry;
import net.mrbt0907.thetitans.util.EntityUtils;
import net.mrbt0907.thetitans.util.PotionUtils;

@Mod.EventBusSubscriber
public class PotionEvents {
    @SubscribeEvent
    public static void absorptionRegenerationEvent(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();
        World world = entity.world;

        if (entity instanceof EntityLivingBase && !world.isRemote) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            PotionEffect potionEffect = entityLivingBase.getActivePotionEffect(PotionRegistry.ABSORPTION_REGENERATION);

            if (potionEffect != null) {
                int amplifier = potionEffect.getAmplifier();
                int interval = Math.max(1, 50 >> Math.min(amplifier, 5));

                if (world.getWorldTime() % interval == 0) {
                    EntityUtils.absorptionHeal(entityLivingBase, 1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void eatEnchantedGoldenApple(LivingEntityUseItemEvent.Finish event) {
        ItemStack resultStack = event.getResultStack();
        EntityLivingBase entityLiving = event.getEntityLiving();
        Item item = resultStack.getItem();

        if (!entityLiving.world.isRemote && item == Items.GOLDEN_APPLE && resultStack.getMetadata() == 1) {
            PotionUtils.addPotionEffect(entityLiving, new PotionEffect(PotionRegistry.ABSORPTION_REGENERATION, 2400, 1));
            PotionUtils.addPotionEffect(entityLiving, new PotionEffect(MobEffects.REGENERATION, 400, 4));
        }
    }
}
