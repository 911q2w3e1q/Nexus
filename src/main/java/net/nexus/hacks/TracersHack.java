package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.render.EspRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public final class TracersHack extends Hack
{
	private static boolean active;

	public TracersHack()
	{
		super("Tracers", "渲染");
	}

	/** 追踪线：玩家到所有实体连线 */
	public static void render(GuiGraphics g, Vec3 cam, int fov)
	{
		if(!active)
			return;
		Minecraft MC = Minecraft.getInstance();
		if(MC.player == null || MC.level == null)
			return;
		Vec3 eye = MC.player.getEyePosition(1.0f);
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(e == MC.player || !e.isAlive())
				continue;
			if(!(e instanceof LivingEntity))
				continue;
			Vec3 c = e.getBoundingBox().getCenter();
			int color = e instanceof Player ? 0xFF55FFFF : 0xFFFF5555;
			EspRenderer.drawLine3D(g,
				new double[] {eye.x, eye.y, eye.z},
				new double[] {c.x, c.y, c.z}, cam, fov, color);
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
