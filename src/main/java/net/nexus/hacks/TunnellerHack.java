package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public final class TunnellerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting height = new Setting("隧道高度", 2f, 1f, 4f, 1f);
	
	public TunnellerHack()
	{
		super("Tunneller", "世界");
		addSetting(height);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		tick++;
		if(tick < 4)
			return;
		tick = 0;
		
		// 沿玩家朝向挖 1x2 隧道
		double yaw = Math.toRadians(MC.player.getYRot());
		int dx = (int)Math.round(-Math.sin(yaw));
		int dz = (int)Math.round(Math.cos(yaw));
		BlockPos front = MC.player.blockPosition().offset(dx, 0, dz);
		
		for(int dy = 0; dy < (int)height.value; dy++)
		{
			BlockPos pos = front.offset(0, dy, 0);
			if(!MC.level.getBlockState(pos).isAir())
			{
				MC.gameMode.destroyBlock(pos);
				return;
			}
		}
	}
}
