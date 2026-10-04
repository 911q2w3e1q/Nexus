package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class NameTagsHack extends Hack
{
	private static boolean active;
	private final Minecraft MC = Minecraft.getInstance();
	
	public NameTagsHack()
	{
		super("NameTags", "渲染");
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
