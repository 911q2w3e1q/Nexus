package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AutoSprintHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoSprintHack()
	{
		super("AutoSprint", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player != null && MC.player.zza > 0)
			MC.player.setSprinting(true);
	}
}
