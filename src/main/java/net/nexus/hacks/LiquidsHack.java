package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class LiquidsHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public LiquidsHack()
	{
		super("Liquids", "世界");
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
		
		// 手持水桶/岩浆桶时快速在面前放置液体
		var held = MC.player.getMainHandItem();
		if(held.is(Items.WATER_BUCKET)
			|| held.is(Items.LAVA_BUCKET))
		{
			if(MC.hitResult instanceof BlockHitResult bhr)
			{
				MC.gameMode.useItemOn(MC.player,
					InteractionHand.MAIN_HAND, bhr);
				cooldown = 1;
			}
		}
	}
}
