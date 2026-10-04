package net.nexus.hacks;

import net.nexus.hack.Hack;

public final class AntiEntityPushHack extends Hack
{
	private static boolean active;
	
	public AntiEntityPushHack()
	{
		super("AntiEntityPush", "移动");
	}
	
	public static boolean isActive()
	{
		return active;
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
