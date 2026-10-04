package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class SneakHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public SneakHack()
	{
		super("Sneak", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		MC.player.setShiftKeyDown(true);
	}
}
