package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.render.EspRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public final class NameTagsHack extends Hack
{
	private static boolean active;

	public NameTagsHack()
	{
		super("NameTags", "渲染");
	}

	/** 名字标签：玩家头顶显示名字 */
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
			if(!(e instanceof Player))
				continue;
			net.minecraft.world.phys.AABB box = e.getBoundingBox();
			double[] sp = EspRenderer.project(
				box.minX + (box.maxX - box.minX) / 2,
				box.maxY + 0.5,
				box.minZ + (box.maxZ - box.minZ) / 2, cam, fov);
			if(sp == null)
				continue;
			String name = e.getDisplayName().getString();
			g.drawString(MC.font, name,
				(int)sp[0] - MC.font.width(name) / 2,
				(int)sp[1] - 10, 0xFFFFFFFF, true);
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
