package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class NoClipHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting fly = new Setting("飞行", true);
	public final Setting speed = new Setting("速度", 1f, 0.5f, 3f, 0.1f);
	
	public NoClipHack()
	{
		super("NoClip", "移动");
		addSetting(fly);
		addSetting(speed);
	}
	
	@Override
	protected void onEnable()
	{
		if(MC.player != null)
			MC.player.noPhysics = true;
	}
	
	@Override
	protected void onDisable()
	{
		if(MC.player != null)
			MC.player.noPhysics = false;
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		MC.player.noPhysics = true;
		
		if(fly.boolValue)
		{
			MC.player.getAbilities().flying = true;
			MC.player.getAbilities().setFlyingSpeed(speed.value / 3);
		}
	}
}
