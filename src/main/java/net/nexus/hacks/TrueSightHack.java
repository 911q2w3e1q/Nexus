package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.render.EspRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public final class TrueSightHack extends Hack
{
	private static boolean active;

	public TrueSightHack()
	{
		super("TrueSight", "渲染");
	}

	/** 真实视野：渲染隐形实体线框 */
	public static void render(GuiGraphics g, Vec3 cam, int fov)
	{
		if(!active)
			return;
		Minecraft MC = Minecraft.getInstance();
		if(MC.player == null || MC.level == null)
			return;
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(e == MC.player || !e.isAlive())
				continue;
			if(!(e instanceof LivingEntity))
				continue;
			var le = (LivingEntity)e;
			if(le.isInvisible())
				EspRenderer.drawEntity(g, le, cam, fov, 0x66AAFFAA,
					"隐形 " + le.getType().getDescription().getString());
		}
	}

	public static boolean isActive()
	{
		return active;
	}

	@Override
	protected void onEnable()
	{
		active = true;
	}

	@Override
	protected void onDisable()
	{
		active = false;
	}
}
