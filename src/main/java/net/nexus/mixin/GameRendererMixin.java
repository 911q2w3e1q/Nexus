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
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import org.joml.Matrix4f;

@Mixin(GameRenderer.class)
public final class GameRendererMixin
{
	@Inject(method = "render(Lnet/minecraft/client/DeltaTracker;Z)V",
		at = @At("TAIL"))
	private void nexusRender(DeltaTracker tickDelta, boolean bl,
		CallbackInfo ci)
	{
		Minecraft MC = Minecraft.getInstance();
		if(MC.level == null || MC.player == null)
			return;
		if(MC.options.hideGui)
			return;
		
		MultiBufferSource.BufferSource buffer =
			MC.renderBuffers().bufferSource();
		Matrix4f mat = new Matrix4f().setOrtho(0,
			MC.getWindow().getGuiScaledWidth(),
			MC.getWindow().getGuiScaledHeight(), 0, 1000, 3000);
		Font font = MC.font;
		
		// 左上角标题
		font.drawInBatch("Nexus Client a0.7 1.21.11",
			4, 3, 0xFFFFFF, false, mat, buffer,
			Font.DisplayMode.NORMAL, 0, 15728880);
		
		// 已启用功能列表
		int y = 14;
		for(Hack hack : NexusClient.getInstance().hackList.getAll())
		{
			if(!hack.isEnabled())
				continue;
			font.drawInBatch(Translate.name(hack.getName()),
				4, y, 0x00FF00, false, mat, buffer,
				Font.DisplayMode.NORMAL, 0, 15728880);
			y += 10;
			if(y > 120)
				break;
		}
		
		buffer.endBatch();
	}
}
