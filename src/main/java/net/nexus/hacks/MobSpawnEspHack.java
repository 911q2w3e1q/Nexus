package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.render.EspRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec3;

public final class MobSpawnEspHack extends Hack
{
	private static boolean active;

	public MobSpawnEspHack()
	{
		super("MobSpawnEsp", "渲染");
	}

	/** 高亮刷怪笼（方块实体） */
	public static void render(GuiGraphics g, Vec3 cam, int fov)
	{
		if(!active)
			return;
		Minecraft MC = Minecraft.getInstance();
		if(MC.player == null || MC.level == null)
			return;
		for(net.minecraft.world.level.block.entity.BlockEntity be
			: MC.level.getGloballyRenderedBlockEntities())
		{
			if(be.getType() == net.minecraft.world.level.block.entity
				.BlockEntityType.MOB_SPAWNER)
			{
				var pos = be.getBlockPos();
				EspRenderer.drawBlock(g, pos.getX() + 0.5,
					pos.getY() + 0.5, pos.getZ() + 0.5,
					cam, fov, 0xFFFF3333, "刷怪笼");
			}
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
