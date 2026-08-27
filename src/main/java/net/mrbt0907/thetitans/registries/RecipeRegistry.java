package net.mrbt0907.thetitans.registries;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.util.registry.AbstractRecipeRegistry;

public class RecipeRegistry extends AbstractRecipeRegistry
{
	public static final RecipeRegistry INSTANCE = new RecipeRegistry(TheTitans.MODID);
	
	private RecipeRegistry(String modid)
	{
		super(modid);
	}

	@Override
	public void init() {}
	
	@Override
	public void register()
	{
		addShapedRecipe(BlockRegistry.harcadium_ore[0], "hb_to_hi", "000,000,000", ItemRegistry.HARCADIUM);
		addShapelessRecipe(ItemRegistry.HARCADIUM, 9, "hi_to_hb", BlockRegistry.harcadium_ore[0]);
		addShapedRecipe(ItemRegistry.HARCADIUM, "hi_to_hn", "000,000,000", ItemRegistry.HARCADIUM_NUGGET);
		addShapelessRecipe(ItemRegistry.HARCADIUM_NUGGET, 9, "hn_to_hi", ItemRegistry.HARCADIUM);
		addShapedRecipe(ItemRegistry.HARCADIUM_NUGGET, "hn_to_hw", "000,000,000", ItemRegistry.HARCADIUM_WAFER);
		addShapelessRecipe(ItemRegistry.HARCADIUM_WAFER, 9, "hw_to_hn", ItemRegistry.HARCADIUM_NUGGET);
		addShapedRecipe(ItemRegistry.HARCADIUM_WAFER, "hw_to_hwl", "000,000,000", ItemRegistry.HARCADIUM_WAFLET);
		addShapelessRecipe(ItemRegistry.HARCADIUM_WAFLET, 9, "hwl_to_hw", ItemRegistry.HARCADIUM_WAFER);

		String material = "harcadium";
		Item item = ItemRegistry.HARCADIUM;
		
		addShapedRecipe(ItemRegistry.HARCADIUM_TOOLS[0], material + "_pickaxe", "000, 1 , 1 ", item, Items.DIAMOND);
		addShapedRecipe(ItemRegistry.HARCADIUM_TOOLS[1], material + "_axe", "00,01, 1", item, Items.DIAMOND);
		addShapedRecipe(ItemRegistry.HARCADIUM_TOOLS[2], material + "_shovel", "0,1,1", item, Items.DIAMOND);
		addShapedRecipe(ItemRegistry.HARCADIUM_TOOLS[3], material + "_hoe", "00, 1, 1", item, Items.DIAMOND);
		addShapedRecipe(ItemRegistry.HARCADIUM_TOOLS[4], material + "_sword", "0,0,1", item, Items.DIAMOND);
		addShapedRecipe(ItemRegistry.HARCADIUM_ARMOR_SET[0], material + "_helmet", "000,0 0", item);
		addShapedRecipe(ItemRegistry.HARCADIUM_ARMOR_SET[1], material + "_chestplate", "0 0,000,000", item);
		addShapedRecipe(ItemRegistry.HARCADIUM_ARMOR_SET[2], material + "_leggings", "000,0 0,0 0", item);
		addShapedRecipe(ItemRegistry.HARCADIUM_ARMOR_SET[3], material + "_boots", "0 0,0 0", item);
	}
}
