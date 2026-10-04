package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class BridgeHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public BridgeHack()
	{
		super("Bridge", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 前方脚下放方块
		double yaw = Math.toRadians(MC.player.getYRot());
		int dx = (int)Math.round(-Math.sin(yaw));
		int dz = (int)Math.round(Math.cos(yaw));
		BlockPos below = MC.player.blockPosition().below().offset(dx, 0, dz);
		
		if(!MC.level.getBlockState(below).isAir())
			return;
		
		int slot = -1;
		for(int i = 0; i < 9; i++)
		{
			var stack = MC.player.getInventory().getItem(i);
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
	}
}
