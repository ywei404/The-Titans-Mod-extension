package net.mrbt0907.thetitans;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;
import net.mrbt0907.thetitans.config.ConfigEntity.*;

@Config(modid = TheTitans.MOD_ID)
public class ConfigTitans
{
	@Name("Enable Debug Mode")
	@Comment("Enabling this option will set the mod into debug mode, allowing it to print useful information into the console for debugging purposes.")
	public static boolean debug_mode = false;

	@Name("Titans")
	@Comment("Change various settings of each titan in the titans mod.")
	public static Titans titan = new Titans();

	public static class AntiCheat
	{
        
	}

	public static class Titans
	{
		@Name("Witherzilla")
		@Comment("Settings related to the titan Witherzilla.")
		public static Witherzilla witherzilla = new Witherzilla();
	}
}