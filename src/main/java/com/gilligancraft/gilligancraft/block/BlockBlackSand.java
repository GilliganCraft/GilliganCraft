package com.gilligancraft.gilligancraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBlackSand extends BlockFalling {

	public BlockBlackSand() {
		// Values set to match vanilla sand
		setStepSound(Block.soundTypeSand);
		setHardness(0.5F);
		setHarvestLevel("shovel", 0);
		setCreativeTab(CreativeTabs.tabBlock);
	}
}
