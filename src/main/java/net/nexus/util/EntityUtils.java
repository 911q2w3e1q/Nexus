package net.nexus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

/** 实体工具（对齐主流客户端行为） */
public final class EntityUtils
{
	private static final Minecraft MC = Minecraft.getInstance();
	
	/** 获取可攻击实体列表（玩家/怪物，按需过滤） */
	public static List<Entity> getAttackableEntities(
		boolean includePlayers, boolean includeMobs)
	{
		List<Entity> list = new ArrayList<>();
		if(MC.level == null)
			return list;
		
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(e == MC.player || !e.isAlive() || e.isRemoved())
				continue;
			if(!(e instanceof LivingEntity))
				continue;
			if(e instanceof Player)
			{
				if(!includePlayers)
					continue;
				// 跳过好友
				if(net.nexus.hacks.FriendsHack.isFriend(
					e.getName().getString()))
					continue;
			}
			else if(e instanceof net.minecraft.world.entity.monster.Monster
				|| e instanceof net.minecraft.world.entity.animal.Animal)
			{
				if(!includeMobs)
					continue;
			}
			else
				continue;
			list.add(e);
		}
		return list;
	}
	
	/** 实体中心 */
	public static Vec3 getCenter(Entity e)
	{
		AABB box = e.getBoundingBox();
		return new Vec3((box.minX + box.maxX) / 2,
			(box.minY + box.maxY) / 2,
			(box.minZ + box.maxZ) / 2);
	}
	
	/** 玩家眼睛到实体 box 的最短距离 */
	public static double getDistanceTo(Entity e)
	{
		if(MC.player == null)
			return Double.MAX_VALUE;
		Vec3 eye = MC.player.getEyePosition(1.0f);
		AABB box = e.getBoundingBox().inflate(0.2);
		double x = clamp(eye.x, box.minX, box.maxX);
		double y = clamp(eye.y, box.minY, box.maxY);
		double z = clamp(eye.z, box.minZ, box.maxZ);
		return eye.distanceTo(new Vec3(x, y, z));
	}
	
	private static double clamp(double v, double min, double max)
	{
		return v < min ? min : (v > max ? max : v);
	}
	
	/** 旋转玩家视角对准目标中心 */
	public static void lookAt(Entity e)
	{
		if(MC.player == null)
			return;
		Vec3 target = getCenter(e);
		Vec3 eye = MC.player.getEyePosition(1.0f);
		double dx = target.x - eye.x;
		double dy = target.y - eye.y;
		double dz = target.z - eye.z;
		float yaw = (float)(Math.toDegrees(Math.atan2(-dx, dz)));
		float pitch = (float)(-Math.toDegrees(
			Math.atan2(dy, Math.sqrt(dx * dx + dz * dz))));
		MC.player.setYRot(yaw);
		MC.player.setXRot(pitch);
	}
}
