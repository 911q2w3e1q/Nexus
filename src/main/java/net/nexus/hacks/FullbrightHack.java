package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class FullbrightHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private double previousGamma = -1;
	
	public FullbrightHack()
	{
		super("Fullbright", "渲染");
	}
	
	@Override
	protected void onEnable()
	{
		if(MC.options != null)
		{
			previousGamma = MC.options.gamma().get();
			MC.options.gamma().set(1.0);
		}
	}
	
	@Override
	protected void onDisable()
	{
		if(previousGamma >= 0 && MC.options != null)
		{
			MC.options.gamma().set(previousGamma);
			previousGamma = -1;
		}
	}
}
