package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayDeque;
import java.util.Deque;

public final class BreadcrumbsHack extends Hack
{
	private static boolean active;
	private static final Deque<Vec3> TRAIL = new ArrayDeque<>();
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting length = new Setting("足迹长度", 500f, 50f, 2000f, 50f);
	
	public BreadcrumbsHack()
	{
		super("Breadcrumbs", "渲染");
		addSetting(length);
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	public static Deque<Vec3> getTrail()
	{
		return TRAIL;
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		tick++;
		if(tick < 5)
			return;
		tick = 0;
		
		Vec3 pos = MC.player.position();
		Vec3 last = TRAIL.peekLast();
		if(last == null || last.distanceTo(pos) > 0.5)
			TRAIL.addLast(pos);
		
		while(TRAIL.size() > (int)length.value)
			TRAIL.removeFirst();
	}
	
	@Override
	protected void onEnable()
	{
		active = true;
		TRAIL.clear();
	}
	
	@Override
	protected void onDisable()
	{
		active = false;
		TRAIL.clear();
	}
}
