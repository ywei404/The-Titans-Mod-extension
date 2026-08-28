package net.mrbt0907.thetitans.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.energy.EnergyStorage;

public class PotionUtils {
    private PotionUtils() {
    }

    public static boolean addPotionEffect(EntityLivingBase entity, PotionEffect effect) {
        if (!EntityUtils.checkEntityAndObjectsValidOnServer(entity, effect)) {
            return false;
        }

        PotionEffect oldEffect = entity.getActivePotionEffect(effect.getPotion());

        entity.addPotionEffect(effect);

        PotionEffect newEffect = entity.getActivePotionEffect(effect.getPotion());

        if (newEffect == null) {
            return false;
        }

        if (oldEffect == null) {
            return newEffect.equals(effect);
        }

        return newEffect.getDuration() > oldEffect.getDuration()
                || newEffect.getAmplifier() > oldEffect.getAmplifier()
                || newEffect.equals(effect);
    }

    public static boolean addPotionEffect(EntityLivingBase entity, PotionEffect effect, int notUntilTicks) {
        if (!EntityUtils.checkEntityAndObjectsValidOnServer(entity, effect)) {
            return false;
        }

        PotionEffect oldEffect = entity.getActivePotionEffect(effect.getPotion());

        if (oldEffect == null) {
            return addPotionEffect(entity, effect);
        }

        if (oldEffect.getAmplifier() < effect.getAmplifier()) {
            return addPotionEffect(entity, effect);
        }

        if (oldEffect.getAmplifier() == effect.getAmplifier()
                && oldEffect.getDuration() <= notUntilTicks) {
            System.out.println(oldEffect.getDuration());

            return addPotionEffect(entity, effect);
        }

        return false;
    }

    public static boolean removePotionEffect(EntityLivingBase entity, Potion potion){
        if (!EntityUtils.checkEntityAndObjectsValidOnServer(entity, potion)) {
            return false;
        }

        if (entity.getActivePotionEffect(potion) == null) {
            return false;
        }

        entity.removePotionEffect(potion);

        return entity.getActivePotionEffect(potion) == null;
    }
}
