package com.gilligancraft.gilligancraft;

import com.gilligancraft.gilligancraft.block.ModBlocks;
import com.gilligancraft.gilligancraft.lib.Constants;
import com.gilligancraft.gilligancraft.world.gen.feature.WorldGeneratorGilligancraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION)
public class GilliganCraft {
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
		GameRegistry.registerWorldGenerator(new WorldGeneratorGilligancraft(),
				1);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
