package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AntiAFKHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int counter;
	
	public AntiAFKHack()
	{
		super("AntiAFK", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		counter++;
		if(counter < 200)
			return;
		counter = 0;
		
		MC.player.setYRot(MC.player.getYRot() + 90);
	}
}
