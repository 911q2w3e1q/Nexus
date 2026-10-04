package net.nexus.hacks;

import net.nexus.hack.Hack;

public final class RainbowUiHack extends Hack
{
	private static boolean active;
	private static float hue;
	
	public RainbowUiHack()
	{
		super("RainbowUi", "渲染");
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	/** 返回当前彩虹色（ARGB，由 GUI 调用） */
	public static int getColor()
	{
		return 0xFF000000
			| java.awt.Color.HSBtoRGB(hue, 1f, 1f) & 0xFFFFFF;
	}
	
	public static void tick()
	{
		hue += 0.005f;
		if(hue > 1)
			hue -= 1;
	}
	
	@Override
	protected void onEnable()
	{
		active = true;
	}
	
	@Override
	protected void onDisable()
	{
		active = false;
	}
}
