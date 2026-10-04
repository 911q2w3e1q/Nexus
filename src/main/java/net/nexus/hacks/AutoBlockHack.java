package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;

public final class AutoBlockHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoBlockHack()
	{
		super("AutoBlock", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.options.keyAttack.isDown())
			return;
		
		MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
	}
}
