package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public final class RestockHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public RestockHack()
	{
		super("Restock", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 从周围箱子补箭（默认补箭，实用）
		if(MC.player.getInventory().countItem(Items.ARROW) >= 16)
			return;
		
		BlockPos p = MC.player.blockPosition();
		for(int dx = -3; dx <= 3; dx++)
			for(int dy = -2; dy <= 2; dy++)
				for(int dz = -3; dz <= 3; dz++)
				{
					var be = MC.level.getBlockEntity(
						p.offset(dx, dy, dz));
					if(be instanceof ChestBlockEntity chest)
					{
						for(int i = 0; i < chest.getContainerSize(); i++)
						{
							var s = chest.getItem(i);
							if(s != null && s.is(Items.ARROW))
							{
								MC.player.getInventory().add(s.copy());
								chest.setItem(i,
									net.minecraft.world.item.ItemStack.EMPTY);
								return;
							}
						}
					}
				}
	}
}
