package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public final class VeinMinerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting range = new Setting("范围", 3f, 1f, 6f, 1f);
	public final Setting maxBlocks = new Setting("最大方块数",
		8f, 2f, 32f, 1f);
	
	public VeinMinerHack()
	{
		super("VeinMiner", "世界");
		addSetting(range);
		addSetting(maxBlocks);
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
		
		// 挖准心指向的方块，并连锁挖同类型方块
		if(MC.hitResult instanceof net.minecraft.world.phys.BlockHitResult bhr)
		{
			BlockPos start = bhr.getBlockPos();
			BlockState target = MC.level.getBlockState(start);
			if(target.isAir())
				return;
			
			int mined = 0;
			BlockPos center = MC.player.blockPosition();
			int r = (int)range.value;
			for(int dx = -r; dx <= r && mined < maxBlocks.value; dx++)
				for(int dy = -r; dy <= r && mined < maxBlocks.value; dy++)
					for(int dz = -r; dz <= r && mined < maxBlocks.value; dz++)
					{
						BlockPos pos = center.offset(dx, dy, dz);
						if(MC.level.getBlockState(pos)
							.is(target.getBlock()))
						{
							MC.gameMode.destroyBlock(pos);
							mined++;
						}
					}
		}
	}
}
