package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.render.EspRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec3;

public final class BarrierEspHack extends Hack
{
	private static boolean active;

	public BarrierEspHack()
	{
		super("BarrierEsp", "渲染");
	}

	/** 高亮周围 24 格屏障方块 */
	public static void render(GuiGraphics g, Vec3 cam, int fov)
	{
		if(!active)
			return;
		Minecraft MC = Minecraft.getInstance();
		if(MC.player == null || MC.level == null)
			return;
		var bp = MC.player.blockPosition();
		for(int dx = -24; dx <= 24; dx++)
			for(int dy = -12; dy <= 12; dy++)
				for(int dz = -24; dz <= 24; dz++)
				{
					var pos = bp.offset(dx, dy, dz);
					var bs = MC.level.getBlockState(pos);
					if(bs.is(net.minecraft.world.level.block.Blocks.BARRIER))
						EspRenderer.drawBlock(g, pos.getX() + 0.5,
							pos.getY() + 0.5, pos.getZ() + 0.5,
							cam, fov, 0xFFFF4444, "屏障");
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
