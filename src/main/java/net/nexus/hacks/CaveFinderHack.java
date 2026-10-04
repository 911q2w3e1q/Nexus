package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

public final class CaveFinderHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int scanTick;
	
	public CaveFinderHack()
	{
		super("CaveFinder", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 每 5 秒扫描周围洞穴（y<64 空气空洞），提示最近洞穴方向
		scanTick++;
		if(scanTick < 100)
			return;
		scanTick = 0;
		
		BlockPos p = MC.player.blockPosition();
		for(int r = 1; r <= 32; r++)
			for(int dx = -r; dx <= r; dx++)
				for(int dy = -r; dy <= r; dy++)
					for(int dz = -r; dz <= r; dz++)
					{
						BlockPos pos = p.offset(dx, dy, dz);
						if(pos.getY() < 64)
						{
							var b = MC.level.getBlockState(pos)
								.getBlock();
							// 空气且头顶有石头 → 洞穴
							if(b == Blocks.CAVE_AIR
								|| b == Blocks.AIR
									&& MC.level.getBlockState(
										pos.above()).is(Blocks.STONE))
							{
								MC.player.displayClientMessage(
									net.minecraft.network.chat
										.Component.literal(
											"§b[洞穴探测] 发现洞穴："
											+ pos.getX() + " "
											+ pos.getY() + " "
											+ pos.getZ()),
									false);
								return;
							}
						}
					}
	}
}
