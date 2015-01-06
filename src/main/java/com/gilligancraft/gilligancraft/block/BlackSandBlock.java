package com.gilligancraft.gilligancraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;

import com.gilligancraft.gilligancraft.lib.Constants;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlackSandBlock extends BlockFalling {
	public String name = "BlackSandBlock";

	public BlackSandBlock() {
		setStepSound(Block.soundTypeSand);
		setHardness(super.blockHardness);
		setCreativeTab(CreativeTabs.tabBlock);
		setBlockName(Constants.MOD_ID + "_" + name);
		setBlockTextureName(Constants.MOD_ID + ":" + name);
		GameRegistry.registerBlock(this, name);
	}
}
