package net.mrbt0907.thetitans.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.thetitans.items.functions.OnArmorTickImplements;
import org.apache.logging.log4j.util.TriConsumer;

public class BaseArmor extends ItemArmor {

	public BaseArmor(ItemMaterial material, EntityEquipmentSlot equipment_slot) 
	{
		super(material.getArmorMaterial(), 0, equipment_slot);
		this.setCreativeTab(TheTitans.TAB_COMBAT);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return TheTitans.MODID + ":textures/armor/" + (slot.equals(EntityEquipmentSlot.LEGS) ? "overlay/" : "") + getArmorMaterial().getName().toLowerCase() + ".png";
    }

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		super.onArmorTick(world, player, itemStack);

		if (!world.isRemote){
            TriConsumer<World, EntityPlayer, ItemStack> onArmorTickImplement = OnArmorTickImplements.ON_ARMOR_TICK_IMPLEMENTS.get(this);

			if (onArmorTickImplement != null){
				onArmorTickImplement.accept(world, player, itemStack);
			}
		}
	}
}
