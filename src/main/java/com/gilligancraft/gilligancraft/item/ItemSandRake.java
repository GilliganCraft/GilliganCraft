package com.gilligancraft.gilligancraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.gilligancraft.gilligancraft.block.ModBlocks;
import com.gilligancraft.gilligancraft.event.entity.player.UseSandRakeEvent;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSandRake extends Item {
	protected Item.ToolMaterial theToolMaterial;

	public ItemSandRake(Item.ToolMaterial toolMaterial) {
		theToolMaterial = toolMaterial;
		maxStackSize = 1;
		setMaxDamage(toolMaterial.getMaxUses());
		setCreativeTab(CreativeTabs.tabTools);
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it doesn't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int blockX, int blockY,
			int blockZ, int blockSide, float pointX, float pointY, float pointZ) {
		if (!entityPlayer.canPlayerEdit(blockX, blockY, blockZ, blockSide, itemStack)) {
			return false;
		} else {
			UseSandRakeEvent event = new UseSandRakeEvent(entityPlayer, itemStack, world, blockX, blockY, blockZ);
			if (MinecraftForge.EVENT_BUS.post(event)) {
				return false;
			}

			if (event.getResult() == Result.ALLOW) {
				itemStack.damageItem(1, entityPlayer);
				return true;
			}

			Block block = world.getBlock(blockX, blockY, blockZ);
			if (blockSide != 0 && world.getBlock(blockX, blockY + 1, blockZ).isAir(world, blockX, blockY + 1, blockZ)
					&& (block == ModBlocks.blockBlackSand)) {
				Block sand = Blocks.sand;
				Block farmSand = ModBlocks.blockFarmsand;
				world.playSoundEffect((double) ((float) blockX + 0.5F), (double) ((float) blockY + 0.5F),
						(double) ((float) blockZ + 0.5F), sand.stepSound.getStepResourcePath(),
						(sand.stepSound.getVolume() + 1.0F) / 2.0F, sand.stepSound.getPitch() * 0.8F);

				if (world.isRemote) {
					return true;
				} else {
					world.setBlock(blockX, blockY, blockZ, farmSand);
					itemStack.damageItem(1, entityPlayer);
					return true;
				}
			} else {
				return false;
			}
		}
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	/**
	 * Returns the name of the material this tool is made from as it is declared in Item.ToolMaterial (meaning diamond
	 * would return "EMERALD")
	 */
	public String getToolMaterialName() {
		return this.theToolMaterial.toString();
	}
}