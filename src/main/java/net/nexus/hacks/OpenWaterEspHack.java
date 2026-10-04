package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.render.EspRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec3;

public final class OpenWaterEspHack extends Hack
{
	private static boolean active;

	public OpenWaterEspHack()
	{
		super("OpenWaterEsp", "渲染");
	}

	/** 高亮周围 24 格水面 */
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
					if(bs.is(net.minecraft.world.level.block.Blocks.WATER))
						EspRenderer.drawBlock(g, pos.getX() + 0.5,
							pos.getY() + 0.5, pos.getZ() + 0.5,
							cam, fov, 0xFF44AAFF, "水");
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
