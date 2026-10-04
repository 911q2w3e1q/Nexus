package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;

public final class BowAimbotHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int counter;
	
	public BowAimbotHack()
	{
		super("BowAimbot", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		if(!MC.player.getMainHandItem().is(Items.BOW))
			return;
		
		counter++;
		if(counter < 20)
			return;
		counter = 0;
		
		MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
	}
}
