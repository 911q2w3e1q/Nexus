package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class HeadRollHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public HeadRollHack()
	{
		super("HeadRoll", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 头部持续旋转（搞笑）
		MC.player.setYRot(MC.player.getYRot() + 10);
	}
}
