package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public final class SpiderHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public SpiderHack()
	{
		super("Spider", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 贴着墙时向上爬
		boolean againstWall = false;
		for(Direction d : Direction.values())
		{
			if(d == Direction.DOWN || d == Direction.UP)
				continue;
			if(MC.level.getBlockState(
				MC.player.blockPosition().relative(d)).isSolid())
			{
				againstWall = true;
				break;
			}
		}
		
		if(againstWall)
		{
			Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x, 0.4, v.z);
		}
	}
}
