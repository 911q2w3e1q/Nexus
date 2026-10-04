package net.nexus.hacks;

import net.nexus.hack.Hack;

public final class ReachHack extends Hack
{
	private static boolean active;
	private static double reach = 6.0;
	
	public final Setting distance = new Setting("距离", 6f, 4.25f, 8f, 0.25f);
	
	public ReachHack()
	{
		super("Reach", "战斗");
		addSetting(distance);
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	public static double getReach()
	{
		return active ? reach : 4.25;
	}
	
	@Override
	public void onTick()
	{
		reach = distance.value;
	}
	
	@Override
	protected void onEnable()
	{
		active = true;
		reach = distance.value;
	}
	
	public static void updateReach(double v)
	{
		reach = v;
	}
	
	@Override
	protected void onDisable()
	{
		active = false;
	}
}
