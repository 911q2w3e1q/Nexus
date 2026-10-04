package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public final class AutoStealHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoStealHack()
	{
		super("AutoSteal", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 周围 3 格内的箱子：拿走所有物品
		BlockPos p = MC.player.blockPosition();
		for(int dx = -3; dx <= 3; dx++)
			for(int dy = -2; dy <= 2; dy++)
				for(int dz = -3; dz <= 3; dz++)
				{
					BlockPos pos = p.offset(dx, dy, dz);
					var be = MC.level.getBlockEntity(pos);
					if(be instanceof ChestBlockEntity chest)
					{
						for(int i = 0; i < chest.getContainerSize(); i++)
						{
							var s = chest.getItem(i);
							if(s != null && !s.isEmpty())
							{
								// 偷到玩家背包
								MC.player.getInventory()
									.add(s.copy());
								chest.setItem(i,
									net.minecraft.world.item.ItemStack.EMPTY);
								return;
							}
						}
					}
				}
	}
}
