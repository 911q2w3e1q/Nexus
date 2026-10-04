package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.hack.Hack.Setting;

public final class NoBackgroundHack extends Hack
{
	private static boolean active;
	private static int alpha;

	public NoBackgroundHack()
	{
		super("NoBackground", "渲染");
		Setting s = new Setting("不透明度", 0.0f, 0.0f, 255.0f, 5.0f);
		addSetting(s);
	}

	/** 背景颜色：开启时按设定透明度（0=全透明） */
	public static int bgColor()
	{
		if(!active)
			return 0xAA000000;
		return (alpha << 24);
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
			if(s.isSlider)
				alpha = (int)s.value;
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
