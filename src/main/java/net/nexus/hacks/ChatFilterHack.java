package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;

public final class ChatFilterHack extends Hack
{
	private static final List<String> BLOCKED = Arrays.asList(
		"广告", "q群", "QQ群", "加群", "私服", "discord.gg",
		"买挂", "出挂");
	private final Minecraft MC = Minecraft.getInstance();
	
	public ChatFilterHack()
	{
		super("ChatFilter", "其他");
	}
	
	public static boolean shouldBlock(String message)
	{
		String lower = message.toLowerCase();
		for(String word : BLOCKED)
			if(lower.contains(word.toLowerCase()))
				return true;
		return false;
	}
	
	// ---- 防刷屏（AntiSpam 共用）----
	private static final java.util.ArrayList<String> recent =
		new java.util.ArrayList<>();
	private static long lastTs = -1;
	
	/** 拦截判定：过滤词命中 或 防刷屏开启时短时间重复消息 */
	public static boolean shouldBlockGlobal(String msg)
	{
		if(shouldBlock(msg))
			return true;
		if(!net.nexus.hacks.AntiSpamHack.isActive())
			return false;
		
		long now = System.currentTimeMillis() / 1000;
		String key = msg.length() > 40 ? msg.substring(0, 40) : msg;
		// 时间窗：同一秒内重复消息直接拦
		if(now == lastTs && recent.contains(key))
			return true;
		recent.add(key);
		if(recent.size() > 20)
			recent.remove(0);
		lastTs = now;
		return false;
	}
}
