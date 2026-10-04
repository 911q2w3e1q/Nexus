package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AutoLeaveHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoLeaveHack()
	{
		super("AutoLeave", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(MC.player.getHealth() < 3)
			MC.player.connection.getConnection().disconnect(
				net.minecraft.network.chat.Component.literal("AutoLeave"));
	}
}
