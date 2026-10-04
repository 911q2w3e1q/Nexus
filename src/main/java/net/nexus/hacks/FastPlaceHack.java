package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class FastPlaceHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public FastPlaceHack()
	{
		super("FastPlace", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 放置无延迟：清空当前手持物品的使用冷却
		var held = MC.player.getMainHandItem();
		if(MC.player.getCooldowns().isOnCooldown(held))
			MC.player.getCooldowns().addCooldown(held, 0);
	}
}
