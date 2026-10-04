package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class ZoomHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int previousFov = -1;
	
	public final Setting fov = new Setting("视野", 30f, 15f, 50f, 5f);
	
	public ZoomHack()
	{
		super("Zoom", "渲染");
		addSetting(fov);
	}
	
	@Override
	protected void onEnable()
	{
		if(MC.options != null)
		{
			previousFov = MC.options.fov().get();
			MC.options.fov().set((int)fov.value);
		}
	}
	
	@Override
	protected void onDisable()
	{
		if(previousFov >= 0 && MC.options != null)
		{
			MC.options.fov().set(previousFov);
			previousFov = -1;
		}
	}
}
