package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class NoWeatherHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public NoWeatherHack()
	{
		super("NoWeather", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.level == null)
			return;
		
		// 天气清零（无雨无雷）
		MC.level.setRainLevel(0);
		MC.level.setThunderLevel(0);
	}
}
