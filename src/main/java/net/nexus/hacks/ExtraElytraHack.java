package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class ExtraElytraHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("速度", 1.5f, 1f, 4f, 0.1f);
	
	public ExtraElytraHack()
	{
		super("ExtraElytra", "移动");
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 鞘翅滑翔加速
		if(MC.player.isFallFlying())
		{
			var v = MC.player.getDeltaMovement();
			MC.player.setDeltaMovement(
				v.x * speed.value,
				v.y,
				v.z * speed.value);
			// 按空格向上加速滑翔
			if(MC.options.keyJump.isDown())
				MC.player.setDeltaMovement(v.x, 0.2 * speed.value, v.z);
		}
	}
}
