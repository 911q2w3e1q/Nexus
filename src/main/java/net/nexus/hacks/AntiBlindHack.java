package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;

public final class AntiBlindHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public AntiBlindHack()
	{
		super("AntiBlind", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 清除失明/黑暗效果
		if(MC.player.hasEffect(MobEffects.BLINDNESS))
			MC.player.removeEffect(MobEffects.BLINDNESS);
		if(MC.player.hasEffect(MobEffects.DARKNESS))
			MC.player.removeEffect(MobEffects.DARKNESS);
	}
}
