package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.hack.Hack.Setting;

public final class LiquidInteractHack extends Hack
{
	private static boolean active;
	private static boolean allowWater = true;
	private static boolean allowLava = true;

	public LiquidInteractHack()
	{
		super("LiquidInteract", "世界");
		addSetting(new Setting("允许对水放置", true));
		addSetting(new Setting("允许对岩浆放置", true));
	}

	/** 是否允许对液体放置（空中放置配合） */
	public static boolean shouldAllowFluid()
	{
		return active;
	}

	/** 是否允许对水放置 */
	public static boolean allowWater()
	{
		return active && allowWater;
	}

	/** 是否允许对岩浆放置 */
	public static boolean allowLava()
	{
		return active && allowLava;
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
			if(s.name.equals("允许对水放置"))
				allowWater = s.boolValue;
			else if(s.name.equals("允许对岩浆放置"))
				allowLava = s.boolValue;
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
