package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public final class HealthTagsHack extends Hack
{
	private static boolean active;
	private static final List<HealthTag> TAGS = new ArrayList<>();
	private final Minecraft MC = Minecraft.getInstance();
	
	public HealthTagsHack()
	{
		super("HealthTags", "渲染");
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	public static List<HealthTag> getTags()
	{
		return TAGS;
	}
	
	public static class HealthTag
	{
		public double x, y, z;
		public String text;
		public int color;
	}
	
	@Override
	public void onTick()
	{
		TAGS.clear();
		if(!active || MC.level == null)
			return;
		
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(e == MC.player || !(e instanceof LivingEntity l)
				|| !l.isAlive())
				continue;
			if(MC.player.distanceTo(e) > 64)
				continue;
			
			HealthTag tag = new HealthTag();
			tag.x = e.getX();
			tag.y = e.getY() + e.getBbHeight() + 0.4;
			tag.z = e.getZ();
			int hp = (int)Math.ceil(l.getHealth());
			int max = (int)l.getMaxHealth();
			tag.text = hp + "/" + max;
			float ratio = l.getHealth() / l.getMaxHealth();
			tag.color = ratio > 0.6f ? 0xFF00FF00
				: ratio > 0.3f ? 0xFFFFFF00 : 0xFFFF0000;
			TAGS.add(tag);
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
		TAGS.clear();
	}
}
