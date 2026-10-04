package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class LsdHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public LsdHack()
	{
		super("Lsd", "渲染");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 迷幻：亮度持续抖动
		tick++;
		double g = 15 + Math.random() * 15;
		MC.options.gamma().set(g);
	}
	
	@Override
	protected void onDisable()
	{
		if(MC.player != null)
			MC.options.gamma().set(0.0);
	}
}
