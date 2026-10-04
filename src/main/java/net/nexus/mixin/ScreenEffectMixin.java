package net.nexus.mixin;

import net.nexus.hacks.NoOverlayHack;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public abstract class ScreenEffectMixin
{
	// 无屏幕效果：去掉火焰/水/岩浆遮罩
	@Inject(method = "renderScreenEffect", at = @At("HEAD"),
		cancellable = true)
	private void nexusNoOverlay(boolean firstPerson,
		float partialTicks,
		net.minecraft.client.renderer.SubmitNodeCollector collector,
		CallbackInfo ci)
	{
		if(NoOverlayHack.shouldCancel(firstPerson))
			ci.cancel();
	}
}
