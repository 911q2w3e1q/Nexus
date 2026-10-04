package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;

public final class ThrowHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public ThrowHack()
	{
		super("Throw", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 自动投掷手持物品
		tick++;
		if(tick < 10)
			return;
		tick = 0;
		MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
	}
}
