package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AutoRespawnHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoRespawnHack()
	{
		super("AutoRespawn", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.player.getHealth() <= 0)
			MC.player.respawn();
	}
}
