package com.gilligancraft.gilligancraft;

import net.minecraftforge.common.config.Configuration;

import com.gilligancraft.gilligancraft.block.ModBlocks;
import com.gilligancraft.gilligancraft.item.ModItems;
import com.gilligancraft.gilligancraft.lib.Constants;
import com.gilligancraft.gilligancraft.world.gen.feature.WorldGeneratorGilligancraft;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION, guiFactory = "com.gilligancraft.gilligancraft.GilliganCraftGuiFactory")
public class GilliganCraft {

	public static Configuration configFile;
	public static Boolean modDebug;
	public static Boolean allowIncorrectDecorations;
	public static Boolean cleanIncorrectDecorations;

	@Mod.Instance(Constants.MOD_ID)
	public static GilliganCraft instance;

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.modID.equals(Constants.MOD_ID)) {
			syncConfig();
		}
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		configFile = new Configuration(event.getSuggestedConfigurationFile());
		// Initialize the entire configuration here
		modDebug = configFile.get(Configuration.CATEGORY_GENERAL, "modDebug", false,
				"Enable debugging.\nFor development purposes only (increased logging).").getBoolean(false);
		allowIncorrectDecorations = configFile.get(Configuration.CATEGORY_GENERAL, "allowIncorrectDecorations", false,
				"Allow incorrect decorations during world generation.\nSet to true for fastest world generation.")
				.getBoolean(false);
		cleanIncorrectDecorations = configFile
				.get(Configuration.CATEGORY_GENERAL,
						"cleanIncorrectDecorations",
						true,
						"Replace invalid decorations during world generation with air (true), or allow them to drop as entities (false).\n*Only effective if allowInvalidDecorations is false")
				.getBoolean(true);

		// Done with configuration init
		// Save configuration to file
		configFile.save();
		ModBlocks.init();
		ModItems.init();
		GameRegistry.registerWorldGenerator(new WorldGeneratorGilligancraft(), 1);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(instance);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	private void syncConfig() {
		if (configFile.hasChanged()) {
			configFile.save();
		}
	}
}
