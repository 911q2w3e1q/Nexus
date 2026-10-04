package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public final class NukerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting radius = new Setting("半径", 2f, 1f, 6f, 1f);
	
	public NukerHack()
	{
		super("Nuker", "世界");
		addSetting(radius);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		BlockPos center = MC.player.blockPosition();
		for(int dx = -(int)radius.value; dx <= (int)radius.value; dx++)
			for(int dy = -(int)radius.value; dy <= (int)radius.value; dy++)
				for(int dz = -(int)radius.value; dz <= (int)radius.value; dz++)
				{
					BlockPos pos = center.offset(dx, dy, dz);
					if(MC.level.getBlockState(pos).isAir())
						continue;
					MC.gameMode.destroyBlock(pos);
					return;
				}
	}
}
