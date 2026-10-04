package net.nexus.mixin;

import net.nexus.hacks.ChatFilterHack;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundPlayerChatPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin
{
	@Inject(method = "handlePlayerChat", at = @At("HEAD"),
		cancellable = true)
	private void nexusFilterPlayerChat(ClientboundPlayerChatPacket packet,
		CallbackInfo ci)
	{
		String msg = packet.unsignedContent() != null
			? packet.unsignedContent().getString()
			: packet.body().content();
		if(msg != null && ChatFilterHack.shouldBlockGlobal(msg))
			ci.cancel();

	}
	
	@Inject(method = "handleSystemChat", at = @At("HEAD"),
		cancellable = true)
	private void nexusFilterSystemChat(ClientboundSystemChatPacket packet,
		CallbackInfo ci)
	{
		if(ChatFilterHack.shouldBlockGlobal(packet.content().getString()))
			ci.cancel();
	}
}
