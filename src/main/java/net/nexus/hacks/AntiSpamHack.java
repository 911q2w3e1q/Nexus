package net.nexus.hacks;

import net.nexus.hack.Hack;
import java.util.ArrayList;
import java.util.List;

public final class AntiSpamHack extends Hack
{
	private static boolean active;
	private static final List<String> recent = new ArrayList<>();
	private static long lastTs = -1;

	public AntiSpamHack()
	{
		super("AntiSpam", "聊天");
	}

	/** 防刷屏判定：开启后同一秒内重复消息拦截 */
	public static boolean shouldBlock(String msg)
	{
		if(!active)
			return false;
		long now = System.currentTimeMillis() / 1000;
		String key = msg.length() > 40 ? msg.substring(0, 40) : msg;
		if(now == lastTs && recent.contains(key))
			return true;
		recent.add(key);
		if(recent.size() > 20)
			recent.remove(0);
		lastTs = now;
		return false;
	}

	public static boolean isActive()
	{
		return active;
	}

	@Override
	protected void onEnable()
	{
		active = true;
		recent.clear();
		lastTs = -1;
	}

	@Override
	protected void onDisable()
	{
		active = false;
		recent.clear();
		lastTs = -1;
	}
}
