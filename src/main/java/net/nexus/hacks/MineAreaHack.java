package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public final class MineAreaHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting radius = new Setting("半径", 3f, 1f, 8f, 1f);
	
	public MineAreaHack()
	{
		super("MineArea", "世界");
		addSetting(radius);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		tick++;
		if(tick < 2)
			return;
		tick = 0;
		
		BlockPos center = MC.player.blockPosition();
		for(int dx = -(int)radius.value; dx <= (int)radius.value; dx++)
			for(int dy = -2; dy <= 2; dy++)
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
