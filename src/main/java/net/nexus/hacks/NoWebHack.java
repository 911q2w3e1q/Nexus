package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;

public final class NoWebHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public NoWebHack()
	{
		super("NoWeb", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 在蜘蛛网中保持速度（简化：移除网的减速效果）
		if(MC.level.getBlockState(MC.player.blockPosition())
			.is(Blocks.COBWEB))
		{
			net.minecraft.world.phys.Vec3 v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(v.x * 1.5, v.y, v.z * 1.5);
		}
	}
}
