package com.gilligancraft.gilligancraft.event.entity.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

@Cancelable
@Event.HasResult
public class UseSandRakeEvent extends PlayerEvent {
	/**
	 * This event is fired when a player attempts to use an ItemSandRake on a block, it can be canceled to completely
	 * prevent any further processing.
	 * 
	 * You can also set the result to ALLOW to mark the event as processed and damage the ItemSandRake.
	 * 
	 * setResult(ALLOW) is the same as the old setHandeled();
	 */

	public final ItemStack current;
	public final World world;
	public final int x;
	public final int y;
	public final int z;

	public UseSandRakeEvent(EntityPlayer player, ItemStack current, World world, int x, int y, int z) {
		super(player);
		this.current = current;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}