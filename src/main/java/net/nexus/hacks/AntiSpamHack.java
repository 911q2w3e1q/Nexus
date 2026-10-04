package net.nexus.hacks;

import net.nexus.hack.Hack;
import java.util.ArrayList;
import java.util.List;

public final class AntiSpamHack extends Hack
{
	private static boolean active;
	
	public AntiSpamHack()
	{
		super("AntiSpam", "聊天");
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
