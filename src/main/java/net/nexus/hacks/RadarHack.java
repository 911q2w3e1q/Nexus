package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.hack.Hack.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public final class RadarHack extends Hack
{
	private static boolean active;
	private static int range = 32;

	public RadarHack()
	{
		super("Radar", "渲染");
		Setting s = new Setting("范围", 32.0f, 8.0f, 96.0f, 8.0f);
		addSetting(s);
	}

	/** 右上角小地图：绘制周围实体红点 */
	public static void render(GuiGraphics g, Minecraft MC)
	{
		if(!active || MC.player == null || MC.level == null)
			return;
		int gw = MC.getWindow().getGuiScaledWidth();
		int rx = gw - 68, ry = 10;
		g.fill(rx, ry, rx + 64, ry + 64, 0x88000000);
		g.fill(rx + 31, ry + 31, rx + 33, ry + 33, 0xFFFFFFFF);
		double scale = 64.0 / (double)range;
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(e == MC.player || !e.isAlive())
				continue;
			if(!(e instanceof LivingEntity))
				continue;
			double dx = e.getX() - MC.player.getX();
			double dz = e.getZ() - MC.player.getZ();
			double dist = Math.sqrt(dx * dx + dz * dz);
			if(dist > range)
				continue;
			int sx = rx + 32 + (int)(dx * scale);
			int sy = ry + 32 + (int)(dz * scale);
			g.fill(sx - 1, sy - 1, sx + 2, sy + 2, 0xFFFF4444);
		}
	}

	public static boolean isActive()
	{
		return active;
	}

	@Override
	public void onTick()
	{
		for(Setting s : getSettings())
		{
			if(s.isSlider)
				range = (int)s.value;
		}
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
