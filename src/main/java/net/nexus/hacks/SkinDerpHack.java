package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class SkinDerpHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public SkinDerpHack()
	{
		super("SkinDerp", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 头部随机乱甩（每次不同角度）
		tick++;
		if(tick % 3 == 0)
		{
			MC.player.setYRot(
				MC.player.getYRot() + (float)(Math.random() * 360));
			MC.player.setXRot(
				(float)(Math.random() * 180 - 90));
		}
	}
}
