package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class DerpHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public DerpHack()
	{
		super("Derp", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 头部随机乱转（搞笑效果）
		MC.player.setYRot(
			MC.player.getYRot() + (float)(Math.random() * 20 - 10));
		MC.player.setXRot(
			(float)(Math.random() * 60 - 30));
	}
}
