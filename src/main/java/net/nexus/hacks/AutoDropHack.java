package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AutoDropHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoDropHack()
	{
		super("AutoDrop", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		for(int i = 0; i < 36; i++)
		{
			var stack = MC.player.getInventory().getItem(i);
			if(stack == null || stack.isEmpty())
				continue;
			MC.player.drop(stack, true, false);
		}
	}
}
