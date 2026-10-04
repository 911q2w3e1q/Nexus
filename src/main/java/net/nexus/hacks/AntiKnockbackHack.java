package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.hack.Hack.Setting;

public final class AntiKnockbackHack extends Hack
{
	private static boolean active;
	private static boolean blockAttack;

	public AntiKnockbackHack()
	{
		super("AntiKnockback", "战斗");
		addSetting(new Setting("拦攻击击退", true));
	}

	/** 是否拦截击退 */
	public static boolean shouldBlock()
	{
		return active;
	}

	/** 是否拦截攻击造成的击退 */
	public static boolean shouldBlockAttack()
	{
		return active && blockAttack;
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
			if(s.name.equals("拦攻击击退"))
				blockAttack = s.boolValue;
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
