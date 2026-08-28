package net.mrbt0907.thetitans.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import org.apache.commons.lang3.ObjectUtils;

public class EntityUtils {
    private EntityUtils() {
    }

    public static boolean checkEntityAndObjectsValidOnServer(Entity entity, Object... objects) {
        return entity != null && ObjectUtils.allNotNull(objects) && !entity.world.isRemote;
    }

    public static boolean isWearingFullSet(EntityPlayer player, Item[] armors) {
        if (!checkEntityAndObjectsValidOnServer(player, (Object[]) armors)
                || armors.length != 4) {
            return false;
        }

        return player.inventory.armorItemInSlot(EntityEquipmentSlot.HEAD.getIndex()).getItem() == armors[0]
                && player.inventory.armorItemInSlot(EntityEquipmentSlot.CHEST.getIndex()).getItem() == armors[1]
                && player.inventory.armorItemInSlot(EntityEquipmentSlot.LEGS.getIndex()).getItem() == armors[2]
                && player.inventory.armorItemInSlot(EntityEquipmentSlot.FEET.getIndex()).getItem() == armors[3];
    }
}
