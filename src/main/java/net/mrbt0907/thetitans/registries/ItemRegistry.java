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

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry
{
    private static RegistryEvent.Register<Item> registry;
    private static final List<Block> item_blocks = new ArrayList<Block>();

    public static final Item harcadium = new Item();

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event)
    {
        TheTitans.debug("Registering items...");
        registry = event;

        for (Block block : item_blocks)
            add(block.getRegistryName().getResourcePath(), new ItemBlock(block), null);

        add("harcadium", harcadium, TheTitans.TAB_ITEMS);

        registry = null;
        TheTitans.debug("Finished registering items");
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
