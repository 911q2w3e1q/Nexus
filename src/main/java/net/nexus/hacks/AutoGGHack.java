package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AutoGGHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public AutoGGHack()
	{
		super("AutoGG", "聊天");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(cooldown > 0)
			cooldown--;
		
		if(MC.player.getHealth() <= 0 && cooldown == 0)
		{
			cooldown = 40;
			if(MC.getConnection() != null)
				MC.getConnection().sendChat("GG!");
		}
	}
}
