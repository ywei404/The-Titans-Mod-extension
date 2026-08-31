package net.mrbt0907.thetitans.event.food;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.mrbt0907.thetitans.registries.PotionRegistry;
import net.mrbt0907.thetitans.util.PotionUtils;

@Mod.EventBusSubscriber
public class FoodEvents {
    @SubscribeEvent
    public static void eatEnchantedGoldenApple(LivingEntityUseItemEvent.Finish event) {
        ItemStack resultStack = event.getResultStack();
        EntityLivingBase entityLiving = event.getEntityLiving();
        Item item = resultStack.getItem();

        if (!entityLiving.world.isRemote && item == Items.GOLDEN_APPLE && resultStack.getMetadata() > 0) {
            PotionUtils.addPotionEffect(entityLiving, new PotionEffect(PotionRegistry.ABSORPTION_REGENERATION, 2400, 1));
            PotionUtils.addPotionEffect(entityLiving, new PotionEffect(MobEffects.REGENERATION, 400, 4));
        }
    }
}
