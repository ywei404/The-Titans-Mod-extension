package net.mrbt0907.thetitans.items;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.thetitans.items.functions.OnArmorTickImplements;
import net.mrbt0907.thetitans.util.EntityUtils;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.ArrayList;
import java.util.List;

public class BaseArmor extends ItemArmor {

    public BaseArmor(ItemMaterial material, EntityEquipmentSlot equipment_slot) {
        super(material.getArmorMaterial(), 0, equipment_slot);
        this.setCreativeTab(TheTitans.TAB_COMBAT);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return TheTitans.MODID + ":textures/armor/" + (slot.equals(EntityEquipmentSlot.LEGS) ? "overlay/" : "") + getArmorMaterial().getName().toLowerCase() + ".png";
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (!world.isRemote) {
            TriConsumer<World, EntityPlayer, ItemStack> onArmorTickImplement = OnArmorTickImplements.ON_ARMOR_TICK_IMPLEMENTS.get(this);

            if (onArmorTickImplement != null) {
                onArmorTickImplement.accept(world, player, itemStack);
            }

            List<Item> armors = new ArrayList<>();

            armors.add(player.inventory.armorItemInSlot(EntityEquipmentSlot.HEAD.getIndex()).getItem());
            armors.add(player.inventory.armorItemInSlot(EntityEquipmentSlot.CHEST.getIndex()).getItem());
            armors.add(player.inventory.armorItemInSlot(EntityEquipmentSlot.LEGS.getIndex()).getItem());
            armors.add(player.inventory.armorItemInSlot(EntityEquipmentSlot.FEET.getIndex()).getItem());

            TriConsumer<World, EntityPlayer, ItemStack> onFullSetArmorTickImplement = OnArmorTickImplements.ON_FULL_SET_ARMOR_TICK_IMPLEMENTS.get(armors);

            if (onFullSetArmorTickImplement != null) {
                onFullSetArmorTickImplement.accept(world, player, itemStack);
            }
        }

        super.onArmorTick(world, player, itemStack);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        return super.getItemAttributeModifiers(equipmentSlot);
    }
}
