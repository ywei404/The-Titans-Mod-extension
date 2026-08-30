package net.mrbt0907.thetitans.registries;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
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
import net.mrbt0907.thetitans.items.BaseArmor;
import net.mrbt0907.thetitans.items.BaseAxe;
import net.mrbt0907.thetitans.items.BaseHoe;
import net.mrbt0907.thetitans.items.BasePickaxe;
import net.mrbt0907.thetitans.items.BaseShovel;
import net.mrbt0907.thetitans.items.BaseSword;
import net.mrbt0907.thetitans.items.BaseTitanSword;
import net.mrbt0907.thetitans.items.ItemMaterial;
import net.mrbt0907.thetitans.items.ItemTeleporter;
import net.mrbt0907.thetitans.items.functions.GetItemAttributeModifiersImplements;
import net.mrbt0907.thetitans.items.functions.OnArmorTickImplements;
import net.mrbt0907.thetitans.util.ItemUtils;

import static net.mrbt0907.thetitans.util.ItemUtils.*;

public class ItemRegistry {
    private static RegistryEvent.Register<Item> registry;
    private static final List<Block> ITEM_BLOCKS = new ArrayList<Block>();

    public static final ItemMaterial ARMOR_MATERIAL_ADMINIUM = new ItemMaterial("adminium", 100000000, 100000, 100000, 100000, 100000, 100000.0F, 1000, null);
    public static final ItemMaterial ARMOR_MATERIAL_VOID = new ItemMaterial("void", 100000, 11, 17, 13, 9, 100.0F, 50, null);
    public static final ItemMaterial ARMOR_MATERIAL_HELLSITE = new ItemMaterial("hellsite", 50000, 10, 15, 12, 9, 20.0F, 40, null);
    public static final ItemMaterial ARMOR_MATERIAL_HARCADIUM = new ItemMaterial("harcadium", 20000, 9, 13, 10, 8, 5.0F, 30, null);
    public static final ItemMaterial ARMOR_MATERIAL_DEMONTIUM = new ItemMaterial("demontium", 20000, 9, 13, 10, 8, 5.0F, 30, null);
    public static final ItemMaterial ARMOR_MATERIAL_WOODEN = new ItemMaterial("wooden", 6, distributeArmor(8), 0.0F, 14, null);
    public static final ItemMaterial ARMOR_MATERIAL_STONE = new ItemMaterial("stone", 14, distributeArmor(14), 0.0F, 7, null);
    public static final ItemMaterial ARMOR_MATERIAL_OBSIDIAN = new ItemMaterial("obsidian", 41, distributeArmor(16), 0.5F, 9, null);
    public static final ItemMaterial ARMOR_MATERIAL_COPPER = new ItemMaterial("copper", 11, distributeArmor(13), 0.0F, 15, null);
    public static final ItemMaterial ARMOR_MATERIAL_SILVER = new ItemMaterial("silver", 9, distributeArmor(12), 0.0F, 20, null);
    public static final ItemMaterial ARMOR_MATERIAL_EMERALD = new ItemMaterial("emerald", 29, distributeArmor(19), 1.0F, 14, null);
    public static final ItemMaterial ARMOR_MATERIAL_NETHERITE = new ItemMaterial("netherite", 37, distributeArmor(21), 3.0F, 16, null);
    public static final ItemMaterial TOOL_MATERIAL_OMNI = new ItemMaterial("omni", Integer.MAX_VALUE, Integer.MAX_VALUE, Float.MAX_VALUE, 10000000.0F, 256);
    public static final ItemMaterial TOOL_MATERIAL_ADAMANTIUM = new ItemMaterial("adamantium", Integer.MAX_VALUE - 1, Integer.MAX_VALUE, 100000000000000.0F, 2500000.0F, 100);
    public static final ItemMaterial TOOL_MATERIAL_ADMINIUM = new ItemMaterial("adminium", Integer.MAX_VALUE - 2, 1000000000, 1000000000.0F, 999996.0F, 60);
    public static final ItemMaterial TOOL_MATERIAL_VOID = new ItemMaterial("void", Integer.MAX_VALUE - 25, 5000000, 4800.0F, 249996.0F, 50);
    public static final ItemMaterial TOOL_MATERIAL_TITANUS = new ItemMaterial("titanus", 20, 5000000, 4800.0F, 119996.0F, 50);
    public static final ItemMaterial TOOL_MATERIAL_HELLSITE = new ItemMaterial("hellsite", 19, 225000, 960.0F, 34996.0F, 40);
    public static final ItemMaterial TOOL_MATERIAL_HARCADIUM = new ItemMaterial("harcadium", 17, 75000, 120.0F, 14996.0F, 30);

    public static final Item ULTIMA_BLADE = new BaseTitanSword(TOOL_MATERIAL_OMNI);
    public static final Item OPTIMA_AXE = new BaseTitanSword(TOOL_MATERIAL_OMNI);
    public static final Item ADAMANTIUM_SWORD = new BaseTitanSword(TOOL_MATERIAL_ADAMANTIUM);
    public static final Item DEMONTIUM_INGOT = new Item();
    public static final Item HARCADIUM = new Item();
    public static final Item HARCADIUM_NUGGET = new Item();
    public static final Item HARCADIUM_WAFER = new Item();
    public static final Item HARCADIUM_WAFLET = new Item();
    public static final Item HELLSITE = new Item();
    public static final Item VOID_ITEM = new Item();
    public static final Item ADAMANTIUM = new Item();
    public static Item[] HARCADIUM_TOOLS;
    public static final Item HARCADIUM_ARROW = new Item();
    public static final Item HARCADIUM_BOW = new Item();
    public static Item[] HELLSITE_TOOLS;
    public static Item[] VOID_TOOLS;
    public static final Item VOID_ARROW = new Item();
    public static final Item VOID_BOW = new Item();
    public static Item[] ADMINIUM_TOOLS;

    public static final Item[] DEMONTIUM_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] HARCADIUM_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] HELLSITE_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] VOID_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] ADMINIUM_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] WOODEN_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] STONE_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] OBSIDIAN_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] COPPER_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] SILVER_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] EMERALD_ARMOR_SET = ItemUtils.generateEmptyArmorArray();
    public static final Item[] NETHERITE_ARMOR_SET = ItemUtils.generateEmptyArmorArray();

    public static final Item DIAMOND_STRING = new Item();
    public static final Item GROWTH_SERUM = new Item();
    public static final Item TELEPORTER = new ItemTeleporter();
    public static final Item TELEPORTER_2 = new Item();
    public static final Item GOOD_TURRET = new Item();
    public static final Item GOOD_TURRET_2 = new Item();
    public static final Item GOOD_TURRET_3 = new Item();
    public static final Item GOLDEN_POTATOE = new Item();
    public static final Item GOLDEN_BREAD = new Item();
    public static final Item GOLDEN_COOKIE = new Item();
    public static final Item GOLDEN_MELON = new Item();
    public static final Item GOLDEN_PUMPKIN_PIE = new Item();
    public static final Item DIAMOND_APPLE = new Item();
    public static final Item DIAMOND_POTATOE = new Item();
    public static final Item DIAMOND_BREAD = new Item();
    public static final Item DIAMOND_COOKIE = new Item();
    public static final Item DIAMOND_MELON = new Item();
    public static final Item DIAMOND_PUMPKIN_PIE = new Item();
    public static final Item PLEASANT_BLADE_SEED = new Item();
    public static final Item PLEASANT_BLADE_LEAF = new Item();
    public static final Item PLEASANT_BLADE_FLOWER = new Item();
    public static final Item PLEASANT_BLADE_BREW = new Item();
    public static final Item MALGRUM = new Item();
    public static final Item MALGRUM_SEEDS = new Item();
    public static final Item CHAFF = new Item();
    public static final Item WITHER_SKELETON_SPAWNER = new Item();
    public static final Item EGG_IRON_GOLEM_BETTER = new Item();
    public static final Item EVENT_SPAWN_ITEM = new Item();
    public static final Item REGULAR_TITANS_MOD_SPAWN_EGG = new Item();
    public static final Item EGG_TITAN = new Item();

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        TheTitans.debug("Registering items...");
        registry = event;

        add("ultima_blade", ULTIMA_BLADE, TheTitans.TAB_COMBAT);
        add("optima_axe", OPTIMA_AXE, TheTitans.TAB_COMBAT);
        add("adamantium_sword", ADAMANTIUM_SWORD, TheTitans.TAB_COMBAT);
        add("demontium_ingot", DEMONTIUM_INGOT, TheTitans.TAB_ITEMS);
        add("harcadium", HARCADIUM, TheTitans.TAB_ITEMS);
        add("harcadium_nugget", HARCADIUM_NUGGET, TheTitans.TAB_ITEMS);
        add("harcadium_wafer", HARCADIUM_WAFER, TheTitans.TAB_ITEMS);
        add("harcadium_waflet", HARCADIUM_WAFLET, TheTitans.TAB_ITEMS);
        add("hellsite", HELLSITE, TheTitans.TAB_ITEMS);
        add("void", VOID_ITEM, TheTitans.TAB_ITEMS);
        add("adamantium", ADAMANTIUM, TheTitans.TAB_ITEMS);
        HARCADIUM_TOOLS = addTools(TOOL_MATERIAL_HARCADIUM);
        add("harcadium_arrow", HARCADIUM_ARROW, TheTitans.TAB_COMBAT);
        add("harcadium_bow", HARCADIUM_BOW, TheTitans.TAB_COMBAT);
        HELLSITE_TOOLS = addTools(TOOL_MATERIAL_HELLSITE);
        VOID_TOOLS = addTools(TOOL_MATERIAL_VOID);
        add("void_arrow", VOID_ARROW, TheTitans.TAB_COMBAT);
        add("void_bow", VOID_BOW, TheTitans.TAB_COMBAT);
        ADMINIUM_TOOLS = addTools(TOOL_MATERIAL_ADMINIUM);

        addArmor(ARMOR_MATERIAL_DEMONTIUM, DEMONTIUM_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_HARCADIUM, HARCADIUM_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_HELLSITE, HELLSITE_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_VOID, VOID_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_ADMINIUM, ADMINIUM_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_WOODEN, WOODEN_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_STONE, STONE_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_OBSIDIAN, OBSIDIAN_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_COPPER, COPPER_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_SILVER, SILVER_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_EMERALD, EMERALD_ARMOR_SET);
        addArmor(ARMOR_MATERIAL_NETHERITE, NETHERITE_ARMOR_SET);

        add("diamond_string", DIAMOND_STRING, TheTitans.TAB_ITEMS);
        add("growth_serum", GROWTH_SERUM, TheTitans.TAB_ITEMS);
        add("teleporter", TELEPORTER, TheTitans.TAB_ITEMS);
        add("teleporter2", TELEPORTER_2, TheTitans.TAB_ITEMS);
        add("good_turret", GOOD_TURRET, TheTitans.TAB_ITEMS);
        add("good_turret2", GOOD_TURRET_2, TheTitans.TAB_ITEMS);
        add("good_turret3", GOOD_TURRET_3, TheTitans.TAB_ITEMS);
        add("golden_potatoe", GOLDEN_POTATOE, TheTitans.TAB_ITEMS);
        add("golden_bread", GOLDEN_BREAD, TheTitans.TAB_ITEMS);
        add("golden_cookie", GOLDEN_COOKIE, TheTitans.TAB_ITEMS);
        add("golden_melon", GOLDEN_MELON, TheTitans.TAB_ITEMS);
        add("golden_pumpkin_pie", GOLDEN_PUMPKIN_PIE, TheTitans.TAB_ITEMS);
        add("diamond_apple", DIAMOND_APPLE, TheTitans.TAB_ITEMS);
        add("diamond_potatoe", DIAMOND_POTATOE, TheTitans.TAB_ITEMS);
        add("diamond_bread", DIAMOND_BREAD, TheTitans.TAB_ITEMS);
        add("diamond_cookie", DIAMOND_COOKIE, TheTitans.TAB_ITEMS);
        add("diamond_melon", DIAMOND_MELON, TheTitans.TAB_ITEMS);
        add("diamond_pumpkin_pie", DIAMOND_PUMPKIN_PIE, TheTitans.TAB_ITEMS);
        add("pleasant_blade_seed", PLEASANT_BLADE_SEED, TheTitans.TAB_ITEMS);
        add("pleasant_blade_leaf", PLEASANT_BLADE_LEAF, TheTitans.TAB_ITEMS);
        add("pleasant_blade_flower", PLEASANT_BLADE_FLOWER, TheTitans.TAB_ITEMS);
        add("pleasant_blade_brew", PLEASANT_BLADE_BREW, TheTitans.TAB_ITEMS);
        add("malgrum", MALGRUM, TheTitans.TAB_ITEMS);
        add("malgrum_seeds", MALGRUM_SEEDS, TheTitans.TAB_ITEMS);
        add("chaff", CHAFF, TheTitans.TAB_ITEMS);
        add("egg_titan", EGG_TITAN, TheTitans.TAB_ITEMS);

        for (Block block : ITEM_BLOCKS)
            add(block.getRegistryName().getResourcePath(), new ItemBlock(block), null);

        registry = null;
        TheTitans.debug("Finished registering items");

        OnArmorTickImplements.addFunctionsToMap();

        TheTitans.debug("Added onArmorTick functions to map");

        GetItemAttributeModifiersImplements.addFunctionsToMap();

        TheTitans.debug("Added getItemAttributeModifiers functions to map");
    }

    private static Item[] addTools(ItemMaterial material) {
        Item[] tools = new Item[5];
        for (int i = 0; i < 5; i++)
            switch (i) {
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
                    tools[i] = material.getDamage() >= TOOL_MATERIAL_HARCADIUM.getDamage() ? new BaseTitanSword(material) : new BaseSword(material.getToolMaterial(), 0.0D);
                    add(material.getName() + "_sword", tools[i], TheTitans.TAB_COMBAT);
                    break;
            }
        return tools;
    }

    private static void addArmor(ItemMaterial material, Item[] armor) {
        for (int i = 0; i < 4; i++)
            switch (i) {
                case 0:
                    armor[i] = new BaseArmor(material, EntityEquipmentSlot.HEAD);
                    add(material.getName() + "_helmet", armor[i], TheTitans.TAB_COMBAT);
                    break;
                case 1:
                    armor[i] = new BaseArmor(material, EntityEquipmentSlot.CHEST);
                    add(material.getName() + "_chestplate", armor[i], TheTitans.TAB_COMBAT);
                    break;
                case 2:
                    armor[i] = new BaseArmor(material, EntityEquipmentSlot.LEGS);
                    add(material.getName() + "_leggings", armor[i], TheTitans.TAB_COMBAT);
                    break;
                case 3:
                    armor[i] = new BaseArmor(material, EntityEquipmentSlot.FEET);
                    add(material.getName() + "_boots", armor[i], TheTitans.TAB_COMBAT);
                    break;
            }
    }

    public static void add(Block block) {
        ITEM_BLOCKS.add(block);
    }

    private static void add(String name, Item item, CreativeTabs tab) {
        add(name, null, item, tab);
    }

    private static void add(String name, String ore_dict_name, Item item, CreativeTabs tab) {
        if (registry != null) {
            item.setRegistryName(name);
            item.setUnlocalizedName(name);

            if (ore_dict_name != null)
                OreDictionary.registerOre(ore_dict_name, item);

            if (tab != null && (item.getCreativeTab() == null || item instanceof ItemSword))
                item.setCreativeTab(tab);

            registry.getRegistry().register(item);

            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

            TheTitans.debug("Registered item " + item.getRegistryName().getResourceDomain() + ":" + item.getRegistryName().getResourcePath());
            return;
        }
        TheTitans.error("Registry event returned null");
    }
}
