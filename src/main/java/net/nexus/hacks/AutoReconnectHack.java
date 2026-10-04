package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;

public final class AutoReconnectHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int ticks;
	
	public final Setting delay = new Setting("重连延迟", 5f, 1f, 30f, 1f);
	
	public AutoReconnectHack()
	{
		super("AutoReconnect", "其他");
		addSetting(delay);
	}
	
	private net.minecraft.client.multiplayer.ServerData lastServer;
	
	@Override
	public void onTick()
	{
		// 记录当前服务器
		if(MC.getCurrentServer() != null)
			lastServer = MC.getCurrentServer();
		
		if(MC.screen instanceof DisconnectedScreen)
		{
			ticks++;
			if(ticks > (int)(delay.value * 20) && lastServer != null)
			{
				ticks = 0;
				var address = net.minecraft.client.multiplayer.resolver
					.ServerAddress.parseString(lastServer.ip);
				net.minecraft.client.gui.screens.ConnectScreen
					.startConnecting(
						new JoinMultiplayerScreen(
							new net.minecraft.client.gui.screens
								.TitleScreen()),
						MC, address, lastServer, false,
						new net.minecraft.client.multiplayer
							.TransferState(
								java.util.Map.of(), java.util.Map.of(),
								false));
			}
		}
		else
		{
			ticks = 0;
		}
	}
}
