package net.mrbt0907.thetitans.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.mrbt0907.thetitans.TheTitans;

public class DamageSources
{
	public static final DamageSource GAMMA_BOLT = DamageSource.LIGHTNING_BOLT.setFireDamage().setDamageBypassesArmor().setDamageIsAbsolute();
	public static final DamageSource LIGHTNING_BOLT = DamageSource.LIGHTNING_BOLT.setMagicDamage().setDamageBypassesArmor();

	public static final String LIFEBLOOM_DAMAGE_NAME = TheTitans.MODID + ":lifebloom";
	public static DamageSource causeTitanDamage(Entity source)
	{
		return causeTitanDamage(source, true);
	}
	
	public static DamageSource causeTitanDamage(Entity source, boolean bypass)
	{
		return bypass ? new EntityDamageSource("titan_damage", source).setDamageBypassesArmor().setDamageAllowedInCreativeMode().setDamageIsAbsolute() : new EntityDamageSource("titan_damage", source);
	}
}
