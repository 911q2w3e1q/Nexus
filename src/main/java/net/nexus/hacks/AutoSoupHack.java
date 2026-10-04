package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;

public final class AutoSoupHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoSoupHack()
	{
		super("AutoSoup", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.player.getHealth() > 12)
			return;
		
		for(int i = 0; i < 9; i++)
		{
			var stack = MC.player.getInventory().getItem(i);
			if(stack != null && (stack.is(Items.MUSHROOM_STEW)
				|| stack.is(Items.BEETROOT_SOUP)
				|| stack.is(Items.RABBIT_STEW)))
			{
				MC.player.getInventory().setSelectedSlot(i);
				MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
				return;
			}
		}
	}
}
