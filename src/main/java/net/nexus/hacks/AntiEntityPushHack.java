package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.hack.Hack.Setting;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;

public final class AntiEntityPushHack extends Hack
{
	private static boolean active;
	private static boolean blockPlayers = true;
	private static boolean blockMobs = true;

	public AntiEntityPushHack()
	{
		super("AntiEntityPush", "移动");
		addSetting(new Setting("拦玩家", true));
		addSetting(new Setting("拦怪物", true));
	}

	/** 是否拦截该实体的推挤（实体碰撞） */
	public static boolean shouldBlockEntity(Entity e)
	{
		if(!active)
			return false;
		if(e instanceof Player)
			return blockPlayers;
		if(e instanceof Monster)
			return blockMobs;
		return true;
	}

	public static boolean isActive()
	{
		return active;
	}

	@Override
	public void onTick()
	{
		for(Setting s : getSettings())
		{
			if(s.name.equals("拦玩家"))
				blockPlayers = s.boolValue;
			else if(s.name.equals("拦怪物"))
				blockMobs = s.boolValue;
		}
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
