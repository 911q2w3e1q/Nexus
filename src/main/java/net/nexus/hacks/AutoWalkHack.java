package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AutoWalkHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoWalkHack()
	{
		super("AutoWalk", "其他");
	}
	
	@Override
	protected void onEnable()
	{
		MC.options.keyUp.setDown(true);
	}
	
	@Override
	protected void onDisable()
	{
		MC.options.keyUp.setDown(false);
	}
}
