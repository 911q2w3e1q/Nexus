package net.nexus.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.render.state.GuiRenderState;

@Mixin(GuiGraphics.class)
public interface GuiGraphicsAccessor
{
	@Accessor("guiRenderState")
	GuiRenderState nexus_getGuiRenderState();
}
