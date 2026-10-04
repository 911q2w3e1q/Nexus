package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.render.EspRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ThrowablePotionItem;
import net.minecraft.world.phys.Vec3;

public final class TrajectoriesHack extends Hack
{
	private static boolean active;

	public TrajectoriesHack()
	{
		super("Trajectories", "渲染");
	}

	/** 弹道预测：投掷物飞行轨迹线 */
	public static void render(GuiGraphics g, Vec3 cam, int fov)
	{
		if(!active)
			return;
		Minecraft MC = Minecraft.getInstance();
		if(MC.player == null || MC.level == null)
			return;
		ItemStack held = MC.player.getMainHandItem();
		var heldItem = held.getItem();
		if(heldItem != Items.SNOWBALL && heldItem != Items.ENDER_PEARL
			&& !(heldItem instanceof ThrowablePotionItem)
			&& heldItem != Items.EGG && heldItem != Items.FISHING_ROD)
			return;
		Vec3 pos = MC.player.getEyePosition(1.0f);
		Vec3 dir = MC.player.getLookAngle();
		double vx = dir.x * 1.5, vy = dir.y * 1.5, vz = dir.z * 1.5;
		Vec3 prev = pos;
		for(int i = 0; i < 30; i++)
		{
			vy -= 0.05;
			pos = pos.add(vx, vy, vz);
			vx *= 0.99;
			vz *= 0.99;
			EspRenderer.drawLine3D(g,
				new double[] {prev.x, prev.y, prev.z},
				new double[] {pos.x, pos.y, pos.z}, cam, fov,
				0xFF55FF55);
			prev = pos;
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
