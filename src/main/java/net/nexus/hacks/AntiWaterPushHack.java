package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.hack.Hack.Setting;

public final class AntiWaterPushHack extends Hack
{
	private static boolean active;
	private static boolean blockLava = true;

	public AntiWaterPushHack()
	{
		super("AntiWaterPush", "移动");
		addSetting(new Setting("拦岩浆推挤", true));
	}

	/** 是否拦截水流/岩浆推挤 */
	public static boolean shouldBlockWater()
	{
		return active;
	}

	/** 是否拦截岩浆推挤（目前水流推挤统一拦截） */
	public static boolean shouldBlockLava()
	{
		return active && blockLava;
	}

	public static boolean isActive()
	{
		return active;
	}

	@Override
	public void onTick()
	{
		for(Setting s : getSettings())
		{
			if(s.name.equals("拦岩浆推挤"))
				blockLava = s.boolValue;
		}
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
