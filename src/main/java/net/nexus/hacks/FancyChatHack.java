package net.nexus.hacks;

import net.nexus.hack.Hack;

public final class FancyChatHack extends Hack
{
	private static boolean active;
	private static final String[] COLORS = {
		"§c", "§6", "§e", "§a", "§b", "§d", "§f"
	};
	
	public FancyChatHack()
	{
		super("FancyChat", "聊天");
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	/** 给消息加彩虹色（由聊天 mixin 调用） */
	public static String rainbow(String msg)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < msg.length(); i++)
			sb.append(COLORS[i % COLORS.length])
				.append(msg.charAt(i));
		return sb.toString();
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
