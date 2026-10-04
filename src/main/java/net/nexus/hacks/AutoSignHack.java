package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class AutoSignHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public AutoSignHack()
	{
		super("AutoSign", "世界");
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
		
		// 手持告示牌时在面前放置
		var held = MC.player.getMainHandItem();
		if(!(held.getItem() instanceof net.minecraft.world.item.SignItem))
			return;
		
		if(MC.hitResult instanceof BlockHitResult bhr)
		{
			var pos = bhr.getBlockPos().relative(
				bhr.getDirection());
			if(MC.level.getBlockState(pos).isAir())
			{
				MC.gameMode.useItemOn(MC.player,
					InteractionHand.MAIN_HAND,
					new BlockHitResult(Vec3.atCenterOf(pos),
						Direction.UP, pos, false));
				cooldown = 3;
			}
		}
	}
}
