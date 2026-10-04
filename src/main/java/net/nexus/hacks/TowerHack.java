package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class TowerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public TowerHack()
	{
		super("Tower", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		BlockPos below = MC.player.blockPosition().below();
		if(!MC.level.getBlockState(below).isAir())
			return;
		
		int slot = -1;
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = MC.player.getInventory().getItem(i);
			if(stack != null && !stack.isEmpty())
			{
				slot = i;
				break;
			}
		}
		if(slot < 0)
			return;
		
		MC.player.getInventory().setSelectedSlot(slot);
		BlockHitResult hit = new BlockHitResult(Vec3.atCenterOf(below),
			Direction.UP, below, false);
		MC.gameMode.useItemOn(MC.player, InteractionHand.MAIN_HAND, hit);
		
		// 跳一下
		if(MC.player.onGround())
		{
			Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x, 0.42, v.z);
		}
	}
}
