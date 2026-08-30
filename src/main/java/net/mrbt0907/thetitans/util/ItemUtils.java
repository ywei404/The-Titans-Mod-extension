package net.mrbt0907.thetitans.util;

import net.minecraft.item.Item;
import net.mrbt0907.thetitans.items.ItemMaterial;

public class ItemUtils {
    private ItemUtils() {
    }

    public static Item[] generateEmptyArmorArray(){
        return new Item[4];
    }

    public static int[] distributeArmor(int totalArmor) {
        if (totalArmor < 4) {
            throw new IllegalArgumentException("totalArmor must be at least 4");
        }

        // 4 ~ 20: Vanilla-style distribution
        if (totalArmor <= 20) {
            switch (totalArmor) {
                case 4:  return new int[]{1, 1, 1, 1};
                case 5:  return new int[]{1, 2, 1, 1};
                case 6:  return new int[]{1, 2, 2, 1};
                case 7:  return new int[]{1, 3, 2, 1};
                case 8:  return new int[]{1, 3, 3, 1};
                case 9:  return new int[]{1, 4, 3, 1};
                case 10: return new int[]{2, 4, 3, 1};
                case 11: return new int[]{2, 4, 3, 2};
                case 12: return new int[]{2, 5, 3, 2};
                case 13: return new int[]{2, 5, 4, 2};
                case 14: return new int[]{2, 6, 4, 2};
                case 15: return new int[]{2, 6, 5, 2};
                case 16: return new int[]{2, 7, 5, 2};
                case 17: return new int[]{3, 7, 5, 2};
                case 18: return new int[]{3, 7, 5, 3};
                case 19: return new int[]{3, 7, 6, 3};
                case 20: return new int[]{3, 8, 6, 3};
                default: throw new AssertionError();
            }
        }

        /*
         * 20+:
         * Helmet : Chestplate : Leggings : Boots
         *    5   :      8      :    7    :   4
         */
        int extra = totalArmor - 20;

        int helmet = 3;
        int chestplate = 8;
        int leggings = 6;
        int boots = 3;

        // Base increments
        helmet += extra * 5 / 24;
        chestplate += extra * 8 / 24;
        leggings += extra * 7 / 24;
        boots += extra * 4 / 24;

        // Remaining points
        int used = helmet + chestplate + leggings + boots;
        int remaining = totalArmor - used;

        // Fractional remainders
        double helmetRemainder = extra * 5 / 24.0 - extra * 5 / 24;
        double chestRemainder = extra * 8 / 24.0 - extra * 8 / 24;
        double leggingsRemainder = extra * 7 / 24.0 - extra * 7 / 24;
        double bootsRemainder = extra * 4 / 24.0 - extra * 4 / 24;

        while (remaining > 0) {
            if (chestRemainder >= leggingsRemainder
                    && chestRemainder >= helmetRemainder
                    && chestRemainder >= bootsRemainder) {

                chestplate++;
                chestRemainder = -1;

            } else if (leggingsRemainder >= helmetRemainder
                    && leggingsRemainder >= bootsRemainder) {

                leggings++;
                leggingsRemainder = -1;

            } else if (helmetRemainder >= bootsRemainder) {

                helmet++;
                helmetRemainder = -1;

            } else {

                boots++;
                bootsRemainder = -1;
            }

            remaining--;
        }

        return new int[]{
                helmet,
                chestplate,
                leggings,
                boots
        };
    }
}
