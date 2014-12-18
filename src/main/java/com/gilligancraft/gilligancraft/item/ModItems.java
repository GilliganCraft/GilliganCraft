package com.gilligancraft.gilligancraft.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import com.gilligancraft.gilligancraft.lib.Constants;

import cpw.mods.fml.common.registry.GameRegistry;

public final class ModItems {

	public static ToolMaterial BAMBOO = EnumHelper.addToolMaterial("BAMBOO", 0, 119, 2.0F, 0.0F, 0);

	public static Item itemBambooSandRake;

	public static void init() {
		GameRegistry.registerItem(
				itemBambooSandRake = new ItemSandRake(BAMBOO).setUnlocalizedName(
						Constants.MOD_ID + "_" + "sandRakeBamboo").setTextureName(
						Constants.MOD_ID + ":" + "bamboo_sand_rake"), "sandRakeBamboo", Constants.MOD_ID);
		// TODO Replace sticks with bamboo when it has been added
		GameRegistry.addRecipe(new ItemStack(itemBambooSandRake),
				new Object[] { "AAA", " A ", " A ", 'A', Items.stick });
	}
}
