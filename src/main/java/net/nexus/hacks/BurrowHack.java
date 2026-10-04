package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class BurrowHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public BurrowHack()
	{
		super("Burrow", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || !MC.player.onGround())
			return;
		
		if(cooldown > 0)
		{
			cooldown--;
			return;
		}
		
		// find obsidian in inventory
		int slot = -1;
		for(int i = 0; i < 36; i++)
		{
			ItemStack stack = MC.player.getInventory().getItem(i);
			if(stack != null && stack.is(Items.OBSIDIAN))
			{
				slot = i;
				break;
			}
		}
		
		if(slot < 0)
			return;
		
		// select the slot and place at feet
		if(slot < 9)
			MC.player.getInventory().setSelectedSlot(slot);
		else
			MC.player.getInventory().setSelectedSlot(0);
		
		BlockPos feet = MC.player.blockPosition();
		BlockHitResult hit = new BlockHitResult(Vec3.atCenterOf(feet),
			Direction.UP, feet, false);
		MC.gameMode.useItemOn(MC.player, InteractionHand.MAIN_HAND, hit);
		cooldown = 10;
	}
}
