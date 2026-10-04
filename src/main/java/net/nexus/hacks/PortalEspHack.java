package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.render.EspRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec3;

public final class PortalEspHack extends Hack
{
	private static boolean active;

	public PortalEspHack()
	{
		super("PortalEsp", "渲染");
	}

	/** 高亮周围 24 格传送门 */
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
					if(bs.is(net.minecraft.world.level.block.Blocks.NETHER_PORTAL)
						|| bs.is(net.minecraft.world.level.block.Blocks.END_PORTAL))
						EspRenderer.drawBlock(g, pos.getX() + 0.5,
							pos.getY() + 0.5, pos.getZ() + 0.5,
							cam, fov, 0xFFAA44FF, "传送门");
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
