package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class MileyCyrusHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private float angle;
	
	public MileyCyrusHack()
	{
		super("MileyCyrus", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 头部绕圈摆动
		angle += 6;
		MC.player.setYRot((float)Math.sin(Math.toRadians(angle)) * 90);
		MC.player.setXRot((float)Math.cos(Math.toRadians(angle)) * 45);
	}
}
