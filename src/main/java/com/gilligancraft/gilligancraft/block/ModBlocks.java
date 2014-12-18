package com.gilligancraft.gilligancraft.block;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.gilligancraft.gilligancraft.lib.Constants;

import cpw.mods.fml.common.registry.GameRegistry;

public final class ModBlocks {
	public static Block blockBlackSand;
	public static Block blockFarmsand;

	public static void init() {
		GameRegistry.registerBlock(
				blockBlackSand = new BlockBlackSand().setBlockName(Constants.MOD_ID + "_" + "blackSand")
						.setBlockTextureName(Constants.MOD_ID + ":" + "black_sand"), "blackSand");
		GameRegistry.registerBlock(
				blockFarmsand = new BlockFarmsand().setBlockName(Constants.MOD_ID + "_" + "farmsand")
						.setBlockTextureName(Constants.MOD_ID + ":" + "farmsand"), "farmsand");

		GameRegistry.addShapelessRecipe(new ItemStack(blockBlackSand, 2), new Object[] { Blocks.sand, Blocks.dirt });
	}
}
