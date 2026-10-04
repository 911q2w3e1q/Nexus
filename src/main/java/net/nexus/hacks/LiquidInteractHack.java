package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class LiquidInteractHack extends Hack
{
	private static boolean active;
	private final Minecraft MC = Minecraft.getInstance();
	
	public LiquidInteractHack()
	{
		super("LiquidInteract", "世界");
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
