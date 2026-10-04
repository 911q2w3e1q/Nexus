package net.nexus.hacks;

import net.nexus.hack.Hack;

public final class PortalEspHack extends Hack
{
	private static boolean active;
	
	public PortalEspHack()
	{
		super("PortalEsp", "渲染");
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
