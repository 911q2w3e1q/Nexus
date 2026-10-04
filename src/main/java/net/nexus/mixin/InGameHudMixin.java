package net.nexus.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.nexus.NexusClient;
import net.nexus.hack.Hack;
import net.nexus.util.Translate;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import org.joml.Matrix4f;

@Mixin(Gui.class)
public final class InGameHudMixin
{
	@Inject(method = "renderTabList(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V",
		at = @At("HEAD"))
	private void nexusRender(GuiGraphics graphics, DeltaTracker tickDelta,
		CallbackInfo ci)
	{
		Minecraft MC = Minecraft.getInstance();
		if(MC.level == null || MC.player == null)
			return;
		
		Font font = MC.font;
		
		// 左上角标题
		graphics.drawString(font, "Nexus Client a0.8 1.21.11",
			4, 3, 0xFFFFFFFF, false);
		
		// 雷达（右上角小地图）
		if(net.nexus.hacks.RadarHack.isActive())
		{
			int gw = MC.getWindow().getGuiScaledWidth();
			int rx = gw - 68, ry = 10;
			graphics.fill(rx, ry, rx + 64, ry + 64, 0x88000000);
			graphics.fill(rx + 31, ry + 31, rx + 33, ry + 33,
				0xFFFFFFFF); // 自己中心白点
			for(net.minecraft.world.entity.Entity e
				: MC.level.entitiesForRendering())
			{
				if(e == MC.player || !e.isAlive())
					continue;
				if(!(e instanceof net.minecraft.world.entity
					.LivingEntity))
					continue;
				double dx = e.getX() - MC.player.getX();
				double dz = e.getZ() - MC.player.getZ();
				double dist = Math.sqrt(dx * dx + dz * dz);
				if(dist > 32)
					continue;
				int sx = rx + 32 + (int)(dx * 2);
				int sy = ry + 32 + (int)(dz * 2);
				graphics.fill(sx - 1, sy - 1, sx + 2, sy + 2,
					e instanceof net.minecraft.world.entity.player.Player
						? 0xFF55FFFF : 0xFFFF5555);
			}
		}
		
		// 已启用功能列表
		int y = 14;
		for(Hack hack : NexusClient.getInstance().hackList.getAll())
		{
			if(!hack.isEnabled())
				continue;
			graphics.drawString(font, Translate.name(hack.getName()),
				4, y, 0xFF00FF00, false);
			y += 10;
			if(y > 120)
				break;
		}
	}

	// 无暗角
	@Inject(method = "renderVignette",
		at = @At("HEAD"), cancellable = true)
	private void nexusNoVignette(GuiGraphics graphics,
		net.minecraft.world.entity.Entity entity, CallbackInfo ci)
	{
		if(net.nexus.hacks.NoVignetteHack.isActive())
			ci.cancel();
	}

}
