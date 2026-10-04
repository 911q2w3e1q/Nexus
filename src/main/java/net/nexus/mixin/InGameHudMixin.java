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
		graphics.drawString(font, "Nexus Client a0.6 1.21.11",
			4, 3, 0xFFFFFFFF, false);
		
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
}
