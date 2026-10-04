package net.nexus.hacks;

import net.nexus.hack.Hack;

public final class AntiKnockbackHack extends Hack
{
	private static boolean active;
	
	public AntiKnockbackHack()
	{
		super("AntiKnockback", "战斗");
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
