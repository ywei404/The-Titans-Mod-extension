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

    /**
     * Create an RGB color.
     *
     * @param red   red component, 0-255
     * @param green green component, 0-255
     * @param blue  blue component, 0-255
     * @return RGB color in 0xRRGGBB format
     */
    public static Integer rgb(int red, int green, int blue) {
        return (red << 16) | (green << 8) | blue;
    }

    public static final Integer BLACK       = rgb(0, 0, 0);
    public static final Integer WHITE       = rgb(255, 255, 255);

    public static final Integer RED         = rgb(255, 0, 0);
    public static final Integer GREEN       = rgb(0, 255, 0);
    public static final Integer BLUE        = rgb(0, 0, 255);

    public static final Integer YELLOW      = rgb(255, 255, 0);
    public static final Integer CYAN        = rgb(0, 255, 255);
    public static final Integer MAGENTA     = rgb(255, 0, 255);

    public static final Integer ORANGE      = rgb(255, 165, 0);
    public static final Integer PURPLE      = rgb(128, 0, 128);
    public static final Integer PINK        = rgb(255, 192, 203);

    public static final Integer BROWN       = rgb(165, 42, 42);
    public static final Integer GRAY        = rgb(128, 128, 128);
    public static final Integer DARK_GRAY   = rgb(64, 64, 64);
    public static final Integer LIGHT_GRAY  = rgb(192, 192, 192);

    public static final Integer DARK_RED    = rgb(139, 0, 0);
    public static final Integer DARK_GREEN  = rgb(0, 100, 0);
    public static final Integer DARK_BLUE   = rgb(0, 0, 139);
    public static final Integer DARK_PURPLE = rgb(75, 0, 130);

    public static final Integer LIME        = rgb(50, 205, 50);
    public static final Integer AQUA        = rgb(0, 255, 255);
    public static final Integer NAVY        = rgb(0, 0, 128);
    public static final Integer GOLD        = rgb(255, 215, 0);
    public static final Integer SILVER      = rgb(192, 192, 192);

    /**
     * Get the red component.
     */
    public static int red(int color) {
        return (color >> 16) & 0xFF;
    }

    /**
     * Get the green component.
     */
    public static int green(int color) {
        return (color >> 8) & 0xFF;
    }

    /**
     * Get the blue component.
     */
    public static int blue(int color) {
        return color & 0xFF;
    }
}
