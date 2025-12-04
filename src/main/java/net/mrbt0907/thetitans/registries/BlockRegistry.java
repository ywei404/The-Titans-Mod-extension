package net.mrbt0907.thetitans.registries;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import net.mrbt0907.thetitans.TheTitans;
import net.mrbt0907.thetitans.blocks.BaseOre;
import net.mrbt0907.thetitans.blocks.BaseResource;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry
{
    private static RegistryEvent.Register<Block> registry;
    private static final Block[] BASE_BLOCKS = {Blocks.STONE, Blocks.END_STONE, Blocks.NETHERRACK, Blocks.OBSIDIAN, Blocks.BEDROCK};
    private static final float[][] BASE_BLOCK_RESISTS = {{3.0F, 10.0F}, {3.0F, 15.0F}, {0.4F, 0.0F}, {50.0F, 2000.0F}, {10000.0F, 6000000.0F}, {90000.0F, 54000000.0F}};

    public static Block[] harcadium_ore = new Block[0];

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> event)
    {
        TheTitans.debug("Registering blocks...");
        registry = event;

        //Vanilla changes
        Blocks.BEDROCK.setHarvestLevel("pickaxe", 21);
        Blocks.BEDROCK.setHardness(10000.0F);
        Blocks.BEDROCK.setCreativeTab(TheTitans.TAB_BLOCKS);

        //Titan ores
        harcadium_ore = addOre("harcadium", 400.0F, 5990.0F, 18);

        registry = null;
        TheTitans.debug("Finished registering blocks");
    }

    private static Block[] addOre(String id, float hardness, float blast_resistance, int harvest_level, Block... blocksToRemove)
    {
        return addOre(id, hardness, blast_resistance, harvest_level, false, blocksToRemove);
    }

    private static Block[] addOre(String id, float hardness, float blast_resistance, int harvest_level, boolean removeBlock, Block... blocksToRemove)
    {
        int size = BASE_BLOCKS.length;

        if (size == 0)
        {
            TheTitans.error("Failed to register ore block as base_blocks returned null. Skippng...");
            return null;
        }
        else if (registry == null)
        {
            TheTitans.error("Failed to register ore block as the registry was not initialized yet. Skippng...");
            return null;
        }

        List<Block> final_blocks = new ArrayList<Block>();

        Block a;
        Block b;
        Block[] c = new Block[size];
        float[] d;
        boolean attempt;

        if (removeBlock)
            a = null;
        else
        {
            a = new BaseResource();
            add(id + "_block", a);
        }
        final_blocks.add(a);


        for (int i = 0; i < size; i++)
        {
            b = BASE_BLOCKS[i];
            d = BASE_BLOCK_RESISTS[i];
            attempt = true;

            for (Block rem : blocksToRemove)
                if (rem.equals(b))
                {
                    attempt = false;
                    break;
                }

            if (attempt)
            {
                a = new BaseOre(d[0] + hardness, d[1] + blast_resistance, harvest_level, 0);
                add(id + "_ore_" + b.getRegistryName().getResourcePath(), a);
                final_blocks.add(a);
            }
        }
        return final_blocks.toArray(c);
    }

    private static void add(String registry_name, Block block)
    {
        add(registry_name, null, block, true);
    }

    @SuppressWarnings("unused")
    private static void add(String registry_name, Block block, boolean creative_tab)
    {
        add(registry_name, null, block, creative_tab);
    }

    @SuppressWarnings("unused")
    private static void add(String registry_name, String ore_dict_name, Block block)
    {
        add(registry_name, ore_dict_name, block, true);
    }

    private static void add(String registry_name, String ore_dict_name, Block block, boolean creative_tab)
    {
        if (registry != null)
        {
            block.setRegistryName(registry_name);
            block.setUnlocalizedName(registry_name);

            if (ore_dict_name != null)
                OreDictionary.registerOre(ore_dict_name, block);

            if (creative_tab)
                block.setCreativeTab(TheTitans.TAB_BLOCKS);

            registry.getRegistry().register(block);

            ItemRegistry.add(block);
            TheTitans.debug("Registered block " + block.getRegistryName().getResourceDomain() +  ":" + block.getRegistryName().getResourcePath());
            return;
        }

        TheTitans.error("Registry event returned null");
    }
}