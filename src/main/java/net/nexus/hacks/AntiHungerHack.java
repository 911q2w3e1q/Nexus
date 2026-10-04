package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AntiHungerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AntiHungerHack()
	{
		super("AntiHunger", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 饥饿值保持满
		var fd = MC.player.getFoodData();
		if(fd.getFoodLevel() < 20)
			fd.setFoodLevel(20);
		if(fd.getSaturationLevel() < 5)
			fd.setSaturation(5f);
	}
}
