package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;

public final class NoLevitationHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public NoLevitationHack()
	{
		super("NoLevitation", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 清除飘浮效果
		if(MC.player.hasEffect(MobEffects.LEVITATION))
			MC.player.removeEffect(MobEffects.LEVITATION);
	}
}
