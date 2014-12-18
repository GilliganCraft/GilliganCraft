package com.gilligancraft.gilligancraft.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;

import com.gilligancraft.gilligancraft.GilliganCraft;

public class WorldGenBlackSand extends WorldGenerator {
	private Block blockType;
	/** The maximum radius used when generating a patch of blocks. */
	private int radius;
	private boolean allowIncorrectDecorations;
	private boolean cleanIncorrectDecorations;

	public WorldGenBlackSand(Block blockType, int radius, boolean allowIncorrectDecorations,
			boolean cleanIncorrectDecorations) {
		this.blockType = blockType;
		this.radius = radius;
		this.allowIncorrectDecorations = allowIncorrectDecorations;
		this.cleanIncorrectDecorations = cleanIncorrectDecorations;
	}

	// By default, we'll do things the "right" way.
	// If it is too slow, the user config provides options for alternative generation decisions
	public WorldGenBlackSand(Block blockType, int radius) {
		this(blockType, radius, GilliganCraft.allowIncorrectDecorations, GilliganCraft.cleanIncorrectDecorations);
	}

	public boolean generate(World currentWorld, Random randomGenerator, int x, int y, int z) {
		if (currentWorld.getBlock(x, y, z).getMaterial() != Material.water) {
			return false;
		} else {
			int l = randomGenerator.nextInt(this.radius - 2) + 2;
			byte b0 = 2;

			for (int i1 = x - l; i1 <= x + l; ++i1) {
				for (int j1 = z - l; j1 <= z + l; ++j1) {
					int k1 = i1 - x;
					int l1 = j1 - z;

					if (k1 * k1 + l1 * l1 <= l * l) {
						for (int i2 = y - b0; i2 <= y + b0; ++i2) {
							Block block = currentWorld.getBlock(i1, i2, j1);

							if (block == Blocks.dirt || block == Blocks.grass) {
								/*
								 * If we're okay with incorrect decorations (like vanilla does), such as grass on sand
								 * (which works during decoration but is invalid afterwards) This is the fastest option
								 */
								if (this.allowIncorrectDecorations) {
									// Create the block
									currentWorld.setBlock(i1, i2, j1, this.blockType, 0, 2);
								} else {
									// Clean up incorrect decorations. This is the slowest option.
									if (this.cleanIncorrectDecorations) {
										// Create the block
										currentWorld.setBlock(i1, i2, j1, this.blockType, 0, 2);

										// Get the block directly above the block we just placed
										block = currentWorld.getBlock(i1, i2 + 1, j1);

										// If it is a plant, we have to clean/remove it
										if (block instanceof IPlantable) {
											currentWorld.setBlockToAir(i1, i2 + 1, j1);
											if (GilliganCraft.modDebug) {
												System.out.println("[" + i1 + "," + i2 + 1 + "," + j1 + "] : "
														+ block.getUnlocalizedName() + " --> air");
											}
										}
									} else {
										/*
										 * We don't care about invalid generation (a la vanilla MC). Only modification
										 * here is the last flag, which is 3 instead of 2. 2 = Send change to Client, 1
										 * = Update Block. We want both, so set flag = 3. We don't want random
										 * decorations (grass, flowers, etc.) on this block, but we're not going to
										 * clean them up. This will simply pop the decoration and leave a floating
										 * entity on the ground.
										 */
										currentWorld.setBlock(i1, i2, j1, this.blockType, 0, 3);
									}
								}
							}
						}
					}
				}
			}
			return true;
		}
	}
}