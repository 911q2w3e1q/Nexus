package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.hack.Hack.Setting;

public final class NoOverlayHack extends Hack
{
	private static boolean active;
	private static boolean firstPersonOnly;

	public NoOverlayHack()
	{
		super("NoOverlay", "渲染");
		addSetting(new Setting("仅第一人称", false));
	}

	/** 是否取消屏幕效果（火焰/水/岩浆遮罩） */
	public static boolean shouldCancel(boolean firstPerson)
	{
		if(!active)
			return false;
		return !firstPersonOnly || firstPerson;
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
			if(s.name.equals("仅第一人称"))
				firstPersonOnly = s.boolValue;
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
