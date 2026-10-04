package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class InstaBuildHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public InstaBuildHack()
	{
		super("InstaBuild", "世界");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 瞬间建造：连续快速放置（清冷却）
		var held = MC.player.getMainHandItem();
		if(MC.player.getCooldowns().isOnCooldown(held))
			MC.player.getCooldowns().addCooldown(held, 0);
	}
}
