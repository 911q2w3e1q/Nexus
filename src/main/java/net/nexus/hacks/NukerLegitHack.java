package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public final class NukerLegitHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting radius = new Setting("半径", 3f, 1f, 6f, 1f);
	
	public NukerLegitHack()
	{
		super("NukerLegit", "世界");
		addSetting(radius);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 合法核爆：只挖准心指向方块周围（模拟正常挖矿速度）
		if(MC.hitResult instanceof net.minecraft.world.phys.BlockHitResult bhr)
		{
			BlockPos center = bhr.getBlockPos();
			tick++;
			if(tick < 4)
				return;
			tick = 0;
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
}
