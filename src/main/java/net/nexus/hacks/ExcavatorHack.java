package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public final class ExcavatorHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting radius = new Setting("半径", 1f, 1f, 5f, 1f);
	public final Setting depth = new Setting("深度", 1f, 1f, 6f, 1f);
	
	public ExcavatorHack()
	{
		super("Excavator", "世界");
		addSetting(radius);
		addSetting(depth);
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
		
		BlockPos center = MC.player.blockPosition();
		for(int dy = -1; dy >= -(int)depth.value; dy--)
			for(int dx = -(int)radius.value; dx <= (int)radius.value; dx++)
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
