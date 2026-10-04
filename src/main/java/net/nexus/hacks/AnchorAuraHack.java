package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.RespawnAnchorBlock;

public final class AnchorAuraHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting range = new Setting("范围", 4f, 2f, 8f, 1f);
	
	public AnchorAuraHack()
	{
		super("AnchorAura", "战斗");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		tick++;
		if(tick < 5)
			return;
		tick = 0;
		
		BlockPos center = MC.player.blockPosition();
		for(int dx = -(int)range.value; dx <= (int)range.value; dx++)
			for(int dy = -2; dy <= 2; dy++)
				for(int dz = -(int)range.value; dz <= (int)range.value; dz++)
				{
					BlockPos pos = center.offset(dx, dy, dz);
					if(MC.level.getBlockState(pos).getBlock()
						instanceof RespawnAnchorBlock)
					{
						MC.gameMode.destroyBlock(pos);
						return;
					}
				}
	}
}
