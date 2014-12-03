package com.gilligancraft.gilligancraft;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import com.gilligancraft.gilligancraft.lib.Constants;

import cpw.mods.fml.client.config.GuiConfig;

public class GilliganCraftConfigGUI extends GuiConfig {

	public GilliganCraftConfigGUI(GuiScreen parent) {
		super(parent, new ConfigElement(GilliganCraft.configFile.getCategory(Configuration.CATEGORY_GENERAL))
				.getChildElements(), Constants.MOD_ID, false, false, Constants.MOD_NAME + ": "
				+ GuiConfig.getAbridgedConfigPath(GilliganCraft.configFile.toString()));
	}
}
