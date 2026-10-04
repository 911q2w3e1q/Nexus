package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class NoFallHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public NoFallHack()
	{
		super("NoFall", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player != null)
			MC.player.fallDistance = 0;
	}
}
