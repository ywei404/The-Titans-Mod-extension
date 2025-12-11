package net.mrbt0907.thetitans.registries;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSword;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.thetitans.item.*;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry
{
    private static RegistryEvent.Register<Item> registry;
    private static final List<Block> item_blocks = new ArrayList<Block>();

    public static final ItemMaterial toolMaterialHarcadium = new ItemMaterial("harcadium", 17, 75000, 120.0F, 14996.0F, 30);

    public static final Item harcadium = new Item();
    public static Item[] harcadiumTools;

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event)
    {
        TheTitans.debug("Registering items...");
        registry = event;

        for (Block block : item_blocks)
            add(block.getRegistryName().getResourcePath(), new ItemBlock(block), null);

        add("harcadium", harcadium, TheTitans.TAB_ITEMS);
        harcadiumTools = addTools(toolMaterialHarcadium);

        registry = null;
        TheTitans.debug("Finished registering items");
    }

    private static Item[] addTools(ItemMaterial material)
    {
        Item[] tools = new Item[5];
        for (int i = 0; i < 5; i++)
            switch(i)
            {
                case 0:
                    tools[i] = new BasePickaxe(material);
                    add(material.getName() + "_pickaxe", tools[i], TheTitans.TAB_TOOLS);
                    break;
                case 1:
                    tools[i] = new BaseAxe(material);
                    add(material.getName() + "_axe", tools[i], TheTitans.TAB_TOOLS);
                    break;
                case 2:
                    tools[i] = new BaseShovel(material);
                    add(material.getName() + "_spade", tools[i], TheTitans.TAB_TOOLS);
                    break;
                case 3:
                    tools[i] = new BaseHoe(material);
                    add(material.getName() + "_hoe", tools[i], TheTitans.TAB_TOOLS);
                    break;
                case 4:
                    //tools[i] = material.getDamage() >= toolMaterialHarcadium.getDamage() ? new BaseTitanSword(material) : new BaseSword(material.getToolMaterial(), 0.0D);
                    //	add(material.getName() + "_sword", tools[i], TheTitans.TAB_COMBAT);
                    break;
            }
        return tools;
    }

    public static void add(Block block)
    {
        item_blocks.add(block);
    }

    private static void add(String name, Item item, CreativeTabs tab)
    {
        add(name, null, item, tab);
    }

    private static void add(String name, String ore_dict_name, Item item, CreativeTabs tab)
    {
        if (registry != null)
        {
            item.setRegistryName(name);
            item.setUnlocalizedName(name);

            if (ore_dict_name != null)
                OreDictionary.registerOre(ore_dict_name, item);

            if (tab != null && (item.getCreativeTab() == null || item instanceof ItemSword))
                item.setCreativeTab(tab);

            registry.getRegistry().register(item);

            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

            TheTitans.debug("Registered item " + item.getRegistryName().getResourceDomain() +  ":" + item.getRegistryName().getResourcePath());
            return;
        }
        TheTitans.error("Registry event returned null");
    }
}
