package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AutoReplyHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public AutoReplyHack()
	{
		super("AutoReply", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.getConnection() == null)
			return;
		
		if(cooldown > 0)
		{
			cooldown--;
			return;
		}
		
		MC.getConnection().sendChat("Nexus Client online!");
		cooldown = 400;
	}
}
