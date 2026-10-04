package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

public final class BaseFinderHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int scanTick;
	
	public BaseFinderHack()
	{
		super("BaseFinder", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 每 5 秒扫描一次周围 48 格，提示玩家建筑（箱子/工作台聚簇）
		scanTick++;
		if(scanTick < 100)
			return;
		scanTick = 0;
		
		BlockPos p = MC.player.blockPosition();
		int found = 0;
		BlockPos last = null;
		for(int dx = -48; dx <= 48; dx++)
			for(int dy = -24; dy <= 24; dy++)
				for(int dz = -48; dz <= 48; dz++)
				{
					BlockPos pos = p.offset(dx, dy, dz);
					var b = MC.level.getBlockState(pos).getBlock();
					if(b == Blocks.CHEST || b == Blocks.FURNACE
						|| b == Blocks.CRAFTING_TABLE
						|| b == Blocks.ENCHANTING_TABLE
						|| b == Blocks.ANVIL)
					{
						found++;
						last = pos;
					}
				}
		if(found > 0 && last != null)
			MC.player.displayClientMessage(
				net.minecraft.network.chat.Component
					.literal("§a[基地探测] 发现 " + found
						+ " 个建筑方块，最近在 " + last.getX()
						+ " " + last.getY() + " " + last.getZ()),
				false);
	}
}
