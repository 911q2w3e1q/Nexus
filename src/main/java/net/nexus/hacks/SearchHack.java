package net.nexus.hacks;

import net.nexus.hack.Hack;

public final class SearchHack extends Hack
{
	private static boolean active;
	
	public SearchHack()
	{
		super("Search", "渲染");
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
