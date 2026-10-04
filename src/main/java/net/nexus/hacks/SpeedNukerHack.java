package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public final class SpeedNukerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting radius = new Setting("半径", 2f, 1f, 6f, 1f);
	
	public SpeedNukerHack()
	{
		super("SpeedNuker", "世界");
		addSetting(radius);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 每 tick 挖一个（超快清空）
		BlockPos center = MC.player.blockPosition();
		int r = (int)radius.value;
		for(int dx = -r; dx <= r; dx++)
			for(int dy = -r; dy <= r; dy++)
				for(int dz = -r; dz <= r; dz++)
				{
					BlockPos pos = center.offset(dx, dy, dz);
					if(MC.level.getBlockState(pos).isAir())
						continue;
					MC.gameMode.destroyBlock(pos);
					return;
				}
	}
}
