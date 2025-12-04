package net.mrbt0907.thetitans;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;

import net.mrbt0907.thetitans.registries.BlockRegistry;
import net.mrbt0907.thetitans.registries.ItemRegistry;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid=TheTitans.MOD_ID, name=TheTitans.MOD_NAME, version=TheTitans.VERSION, acceptedMinecraftVersions="[1.12.2]")
public class TheTitans 
{
	public static final String MOD_NAME = "The Titans Mod";
	public static final String MOD_ID = "thetitans";
	public static final String VERSION = "0.7.0-indev";
	public static final String CLIENT = "net.mrbt0907.thetitans.ClientProxy";
	public static final String SERVER = "net.mrbt0907.thetitans.CommonProxy";
	@SidedProxy(clientSide=CLIENT, serverSide=SERVER)
	public static CommonProxy proxy;
	@Mod.Instance
	public static TheTitans instance;
	//public static final NetworkReciever NETWORK = new NetworkReciever();
	private static Logger logger;
	
	public static final CreativeTabs TAB_BLOCKS = new CreativeTabs(MOD_ID + "_blocks") {@Override public ItemStack getTabIconItem() {return new ItemStack(BlockRegistry.harcadium_ore[0]);}};
	//public static final CreativeTabs TAB_ITEMS = new CreativeTabs(MOD_ID + "_items") {@Override public ItemStack getTabIconItem() {return new ItemStack(ItemRegistry.harcadium);}};
	//public static final CreativeTabs TAB_COMBAT = new CreativeTabs(MOD_ID + "_weapons") {@Override public ItemStack getTabIconItem() {return new ItemStack(ItemRegistry.harcadiumTools[4]);}};
	//public static final CreativeTabs TAB_TOOLS = new CreativeTabs(MOD_ID + "_tools") {@Override public ItemStack getTabIconItem() {return new ItemStack(ItemRegistry.harcadiumTools[0]);}};
	public static final CreativeTabs TAB_MOBS = new CreativeTabs(MOD_ID + "_mobs") {@Override public ItemStack getTabIconItem() {return new ItemStack(Blocks.SKULL, 1, 1);}};
	//public static final EnumCreatureType VOID = EnumHelper.addCreatureType("VOID", IEndMob.class, 20, Material.AIR, true, false);
	
	public static final int DIMENSION_VOID_ID = 312;
	public static final int DIMENSION_NOWHERE_ID = 313;
	//public static final DimensionType DIMENSION_VOID = DimensionType.register("The Void", "_void", DIMENSION_VOID_ID, WorldProviderVoid.class, false);
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
		info("Loading The Titans Mod...");
		debug("Pre-Initialization started");
		MinecraftForge.EVENT_BUS.register(this);
		//NetworkHandler.register(NETWORK);
		//MinecraftForge.EVENT_BUS.register(GameEventHandler.class);
		//MinecraftForge.EVENT_BUS.register(InternalEventHandler.class);
		MinecraftForge.EVENT_BUS.register(BlockRegistry.class);
		MinecraftForge.EVENT_BUS.register(ItemRegistry.class);
		//MinecraftForge.EVENT_BUS.register(BiomeRegistry.class);
		//RecipeRegistry.INSTANCE.init();
		
		//DimensionManager.registerDimension(DIMENSION_VOID_ID, DIMENSION_VOID);
		//DimensionManager.registerDimension(DIMENSION_NOWHERE_ID, DIMENSION_NOWHERE);
		
		proxy.preInit(event);
		debug("Pre-Initialization finished");
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		debug("Initialization started");
		proxy.init(event);
		debug("Initialization finished");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		debug("Post-Initialization started!");
		proxy.postInit(event);
		debug("Post-Initialization finished");
		info("Finished The Titans Mod!");
	}
	
	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent event)
	{
		if (event.getModID().equals(MOD_ID))
			ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
	}
	
	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		
	}
	
	public static void info(Object message)
	{
		logger.info(message);
	}
	
	public static void debug(Object message)
	{
		if (ConfigTitans.debug_mode)
			logger.info("[DEBUG] " + message);
	}
	
	public static void warn(Object message)
	{
		if (ConfigTitans.debug_mode)
			logger.warn(message);
	}

	public static void error(Object message)
	{
		if (message instanceof Throwable)
			((Throwable) message).printStackTrace();
		else
			new Exception(String.valueOf(message)).printStackTrace();
	}
	
	public static void fatal(Object message)
	{
		if (message instanceof Error)
			throw (Error) message;
		else
			throw new Error(String.valueOf(message));
	}
}