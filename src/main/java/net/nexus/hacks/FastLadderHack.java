package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public final class FastLadderHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("爬梯速度", 0.5f, 0.2f, 1.5f, 0.1f);
	
	public FastLadderHack()
	{
		super("FastLadder", "移动");
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		boolean onLadder = false;
		for(Direction d : Direction.values())
		{
			if(MC.level.getBlockState(MC.player.blockPosition()
				.relative(d)).is(Blocks.LADDER))
			{
				onLadder = true;
				break;
			}
		}
		
		if(onLadder)
		{
			Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x, speed.value, v.z);
		}
	}
}
