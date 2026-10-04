package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class TillauraHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public final Setting range = new Setting("范围", 3f, 1f, 6f, 1f);
	
	public TillauraHack()
	{
		super("Tillaura", "世界");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		if(cooldown > 0)
		{
			cooldown--;
			return;
		}
		
		// 找锄头
		int slot = -1;
		for(int i = 0; i < 36; i++)
		{
			var s = MC.player.getInventory().getItem(i);
			if(s != null && s.getItem()
				instanceof net.minecraft.world.item.HoeItem)
			{
				slot = i;
				break;
			}
		}
		if(slot < 0)
			return;
		
		// 周围草方块/泥土锄成耕地
		BlockPos p = MC.player.blockPosition();
		int r = (int)range.value;
		for(int dx = -r; dx <= r; dx++)
			for(int dy = -1; dy <= 1; dy++)
				for(int dz = -r; dz <= r; dz++)
				{
					BlockPos pos = p.offset(dx, dy, dz);
					var b = MC.level.getBlockState(pos).getBlock();
					if(b == Blocks.GRASS_BLOCK
						|| b == Blocks.DIRT
						|| b == Blocks.COARSE_DIRT)
					{
						MC.player.getInventory().setSelectedSlot(
							slot < 9 ? slot : 0);
						MC.gameMode.useItemOn(MC.player,
							InteractionHand.MAIN_HAND,
							new BlockHitResult(Vec3.atCenterOf(pos),
								Direction.UP, pos, false));
						cooldown = 2;
						return;
					}
				}
	}
}
