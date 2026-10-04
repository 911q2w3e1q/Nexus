package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public final class AutoLadderHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoLadderHack()
	{
		super("AutoLadder", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 靠近梯子时自动爬
		boolean nearLadder = false;
		for(Direction d : Direction.values())
		{
			if(MC.level.getBlockState(MC.player.blockPosition()
				.relative(d)).is(net.minecraft.world.level.block.Blocks.LADDER))
			{
				nearLadder = true;
				break;
			}
		}
		
		if(nearLadder && MC.options.keyUp.isDown())
		{
			Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x, 0.3, v.z);
		}
	}
}
