package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class AutoSwitchHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AutoSwitchHack()
	{
		super("AutoSwitch", "其他");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.hitResult == null)
			return;
		
		// 简化：攻击时自动换到第一格武器
		if(MC.options.keyAttack.isDown()
			&& MC.player.getInventory().getSelectedSlot() >= 9)
			MC.player.getInventory().setSelectedSlot(0);
	}
}
