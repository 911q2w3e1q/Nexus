package net.nexus.render;

import net.nexus.NexusClient;
import net.nexus.hacks.PlayerEspHack;
import net.nexus.hacks.MobEspHack;
import net.nexus.hacks.ChestEspHack;
import net.nexus.hacks.ItemEspHack;
import net.nexus.util.Translate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

public final class EspRenderer
{
	private static final Minecraft MC = Minecraft.getInstance();
	
	private static final java.util.List<net.minecraft.core.BlockPos>
		chestCache = new java.util.ArrayList<>();
	private static int scanTick;
	private static float partialTick;
	
	public static void render(GuiGraphics g, float tickDelta)
	{
		if(MC.player == null || MC.level == null)
			return;
		
		partialTick = tickDelta;
		
		boolean player = PlayerEspHack.isActive();
		boolean mob = MobEspHack.isActive();
		boolean chest = ChestEspHack.isActive();
		boolean item = ItemEspHack.isActive();
		if(!player && !mob && !chest && !item)
			return;
		
		Vec3 cam = MC.player.getEyePosition(tickDelta);
		int fov = MC.options.fov().get();
		
		// 定时扫描箱子（用 BlockEntity 列表，不受 XRay 影响）
		scanTick++;
		if(chest && scanTick % 20 == 0)
		{
			chestCache.clear();
			for(net.minecraft.world.level.block.entity.BlockEntity be
				: MC.level.getGloballyRenderedBlockEntities())
			{
				var type = be.getType();
				if(type == net.minecraft.world.level.block.entity
					.BlockEntityType.CHEST
					|| type == net.minecraft.world.level.block.entity
						.BlockEntityType.TRAPPED_CHEST
					|| type == net.minecraft.world.level.block.entity
						.BlockEntityType.ENDER_CHEST)
				{
					chestCache.add(be.getBlockPos());
				}
			}
		}
		
		// 刷怪笼 ESP（渲染方块实体里的刷怪笼）
		if(net.nexus.hacks.MobSpawnEspHack.isActive())
		{
			for(net.minecraft.world.level.block.entity.BlockEntity be
				: MC.level.getGloballyRenderedBlockEntities())
			{
				if(be.getType() == net.minecraft.world.level.block.entity
					.BlockEntityType.MOB_SPAWNER)
				{
					var pos = be.getBlockPos();
					drawBlock(g, pos.getX() + 0.5, pos.getY() + 0.5,
						pos.getZ() + 0.5, cam, fov,
						0xFFFF3333, "刷怪笼");
				}
			}
		}
		
		// 开阔水域 ESP（周围 24 格扫描水面）
		if(net.nexus.hacks.OpenWaterEspHack.isActive())
		{
			var bp = MC.player.blockPosition();
			for(int dx = -24; dx <= 24; dx++)
				for(int dy = -12; dy <= 12; dy++)
					for(int dz = -24; dz <= 24; dz++)
					{
						var pos = bp.offset(dx, dy, dz);
						var bs = MC.level.getBlockState(pos);
						if(bs.is(net.minecraft.world.level.block
							.Blocks.WATER))
							drawBlock(g, pos.getX() + 0.5,
								pos.getY() + 0.5, pos.getZ() + 0.5,
								cam, fov, 0xFF44AAFF, "水");
					}
		}
		
		// 传送门 ESP（周围 24 格扫描）
		if(net.nexus.hacks.PortalEspHack.isActive())
		{
			var bp = MC.player.blockPosition();
			for(int dx = -24; dx <= 24; dx++)
				for(int dy = -12; dy <= 12; dy++)
					for(int dz = -24; dz <= 24; dz++)
					{
						var pos = bp.offset(dx, dy, dz);
						var bs = MC.level.getBlockState(pos);
						if(bs.is(net.minecraft.world.level.block
							.Blocks.NETHER_PORTAL)
							|| bs.is(net.minecraft.world.level.block
								.Blocks.END_PORTAL))
							drawBlock(g, pos.getX() + 0.5,
								pos.getY() + 0.5, pos.getZ() + 0.5,
								cam, fov, 0xFFAA44FF, "传送门");
					}
		}
		
		// 屏障方块 ESP + 搜索方块（周围 24 格扫描）
		boolean barrier = net.nexus.hacks.BarrierEspHack.isActive();
		boolean search = net.nexus.hacks.SearchHack.isActive();
		if(barrier || search)
		{
			var bp = MC.player.blockPosition();
			for(int dx = -24; dx <= 24; dx++)
				for(int dy = -12; dy <= 12; dy++)
					for(int dz = -24; dz <= 24; dz++)
					{
						var pos = bp.offset(dx, dy, dz);
						var bs = MC.level.getBlockState(pos);
						if(barrier && bs.is(
							net.minecraft.world.level.block.Blocks.BARRIER))
							drawBlock(g, pos.getX() + 0.5,
								pos.getY() + 0.5, pos.getZ() + 0.5,
								cam, fov, 0xFFFF4444, "屏障");
						if(search && bs.is(
							net.minecraft.world.level.block.Blocks.CHEST))
							drawBlock(g, pos.getX() + 0.5,
								pos.getY() + 0.5, pos.getZ() + 0.5,
								cam, fov, 0xFF44FF44, "箱子");
					}
		}
		
		// 实体
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(e == MC.player)
				continue;
			if(e instanceof Player p && player)
				drawEntity(g, p, cam, fov, PlayerEspHack.getColor(),
					p.getDisplayName().getString());
			else if(e instanceof Monster m && mob)
				drawEntity(g, m, cam, fov, MobEspHack.getColor(),
					m.getType().getDescription().getString());
			else if(e instanceof ItemEntity i && item)
				drawEntity(g, i, cam, fov, ItemEspHack.getColor(),
					i.getItem().getHoverName().getString());
		}
		
		// 真实视野：渲染隐形实体（框）
		if(net.nexus.hacks.TrueSightHack.isActive())
		{
			for(Entity e : MC.level.entitiesForRendering())
			{
				if(e == MC.player || !e.isAlive())
					continue;
				if(!(e instanceof net.minecraft.world.entity
					.LivingEntity))
					continue;
				var le = (net.minecraft.world.entity.LivingEntity)e;
				if(le.isInvisible())
					drawEntity(g, le, cam, fov, 0x66AAFFAA,
						"隐形 " + le.getType().getDescription()
							.getString());
			}
		}
		
		// Tracers 追踪线（玩家到所有实体）
		if(net.nexus.hacks.TracersHack.isActive())
		{
			Vec3 eye = MC.player.getEyePosition(tickDelta);
			for(Entity e : MC.level.entitiesForRendering())
			{
				if(e == MC.player || !e.isAlive())
					continue;
				if(!(e instanceof net.minecraft.world.entity
					.LivingEntity))
					continue;
				Vec3 c = e.getBoundingBox().getCenter();
				int color = e instanceof Player
					? 0xFF55FFFF : 0xFFFF5555;
				drawLine3D(g,
					new double[] {eye.x, eye.y, eye.z},
					new double[] {c.x, c.y, c.z}, cam, fov, color);
			}
		}
		
		// NameTags 名字（玩家头顶显示名字）
		if(net.nexus.hacks.NameTagsHack.isActive())
		{
			for(Entity e : MC.level.entitiesForRendering())
			{
				if(e == MC.player || !e.isAlive())
					continue;
				if(!(e instanceof Player))
					continue;
				net.minecraft.world.phys.AABB box =
					e.getBoundingBox();
				double[] sp = project(box.minX + (box.maxX
					- box.minX) / 2,
					box.maxY + 0.5,
					box.minZ + (box.maxZ - box.minZ) / 2,
					cam, fov);
				if(sp == null)
					continue;
				String name = e.getDisplayName().getString();
				g.drawString(MC.font, name,
					(int)sp[0] - MC.font.width(name) / 2,
					(int)sp[1] - 10, 0xFFFFFFFF, true);
			}
		}
		
		// 箱子
		if(chest)
			for(net.minecraft.core.BlockPos pos : chestCache)
				drawBlock(g, pos.getX() + 0.5, pos.getY() + 0.5,
					pos.getZ() + 0.5, cam, fov,
					ChestEspHack.getColor(), "箱子");
		
		// 血条标签
		if(net.nexus.hacks.HealthTagsHack.isActive())
			for(net.nexus.hacks.HealthTagsHack.HealthTag tag
				: net.nexus.hacks.HealthTagsHack.getTags())
			{
				double[] sp = project(tag.x, tag.y, tag.z, cam, fov);
				if(sp == null)
					continue;
				g.drawString(MC.font, tag.text,
					(int)sp[0] - 12, (int)sp[1] - 4, tag.color, true);
			}
		
		// 投掷物弹道预测线
		if(net.nexus.hacks.TrajectoriesHack.isActive())
		{
			net.minecraft.world.item.ItemStack held =
				MC.player.getMainHandItem();
			var heldItem = held.getItem();
			if(heldItem == net.minecraft.world.item.Items.SNOWBALL
				|| heldItem == net.minecraft.world.item.Items.ENDER_PEARL
				|| heldItem instanceof net.minecraft.world.item.ThrowablePotionItem
				|| heldItem == net.minecraft.world.item.Items.EGG
				|| heldItem == net.minecraft.world.item.Items.FISHING_ROD)
			{
				Vec3 pos = MC.player.getEyePosition(1.0f);
				Vec3 dir = MC.player.getLookAngle();
				double vx = dir.x * 1.5;
				double vy = dir.y * 1.5;
				double vz = dir.z * 1.5;
				Vec3 prev = pos;
				for(int i = 0; i < 30; i++)
				{
					vy -= 0.05;
					pos = pos.add(vx, vy, vz);
					vx *= 0.99;
					vz *= 0.99;
					drawLine3D(g,
						new double[] {prev.x, prev.y, prev.z},
						new double[] {pos.x, pos.y, pos.z},
						cam, fov, 0xFF55FF55);
					prev = pos;
				}
			}
		}
		
		// AirPlace 引导线
		if(net.nexus.hacks.AirPlaceHack.isActive()
			&& net.nexus.hacks.AirPlaceHack.showGuide())
		{
			Vec3 eye = MC.player.getEyePosition(1.0f);
			Vec3 dir = MC.player.getLookAngle();
			float r = net.nexus.hacks.AirPlaceHack.getRange();
			drawLine3D(g, new double[] {eye.x, eye.y, eye.z},
				new double[] {eye.x + dir.x * r, eye.y + dir.y * r,
					eye.z + dir.z * r},
				cam, fov, 0xFFFF00FF);
			// 末端小十字
			double[] ep = project(eye.x + dir.x * r,
				eye.y + dir.y * r, eye.z + dir.z * r, cam, fov);
			if(ep != null)
			{
				int sx = (int)ep[0], sy = (int)ep[1];
				g.fill(sx - 3, sy, sx + 4, sy + 1, 0xFFFF00FF);
				g.fill(sx, sy - 3, sx + 1, sy + 4, 0xFFFF00FF);
			}
		}
		
		// 足迹线
		if(net.nexus.hacks.BreadcrumbsHack.isActive())
		{
			net.minecraft.world.phys.Vec3 prev = null;
			for(net.minecraft.world.phys.Vec3 pt
				: net.nexus.hacks.BreadcrumbsHack.getTrail())
			{
				if(prev != null)
					drawLine3D(g, new double[] {prev.x, prev.y, prev.z},
						new double[] {pt.x, pt.y, pt.z}, cam, fov,
						0xFF00FFFF);
				prev = pt;
			}
		}
	}
	
	private static void drawEntity(GuiGraphics g, Entity e, Vec3 cam,
		int fov, int color, String name)
	{
		net.minecraft.world.phys.AABB box = e.getBoundingBox();
		double[] c = project(box.getCenter().x, box.getCenter().y,
			box.getCenter().z, cam, fov);
		if(c == null)
			return;
		
		int sx = (int)c[0], sy = (int)c[1];
		double dist = Math.round(e.distanceTo(MC.player) * 10) / 10.0;
		if(dist > net.nexus.hacks.PlayerEspHack.getMaxDist())
			return;
		
		// 名字 + 距离
		g.drawString(MC.font, name + " " + dist + "m",
			sx - 20, sy - 40, color, true);
		
		// 3D 线框（AABB 12 条边）
		double[][] corners = {
			{box.minX, box.minY, box.minZ},
			{box.maxX, box.minY, box.minZ},
			{box.maxX, box.minY, box.maxZ},
			{box.minX, box.minY, box.maxZ},
			{box.minX, box.maxY, box.minZ},
			{box.maxX, box.maxY, box.minZ},
			{box.maxX, box.maxY, box.maxZ},
			{box.minX, box.maxY, box.maxZ}
		};
		int[][] edges = {{0,1},{1,2},{2,3},{3,0},
			{4,5},{5,6},{6,7},{7,4},{0,4},{1,5},{2,6},{3,7}};
		for(int[] ed : edges)
			drawLine3D(g, corners[ed[0]], corners[ed[1]],
				cam, fov, color);
	}
	
	private static void drawBlock(GuiGraphics g, double x, double y,
		double z, Vec3 cam, int fov, int color, String name)
	{
		double[] c = project(x, y, z, cam, fov);
		if(c == null)
			return;
		
		int sx = (int)c[0], sy = (int)c[1];
		double dist = Math.round(
			Math.sqrt((x - cam.x) * (x - cam.x)
				+ (y - cam.y) * (y - cam.y)
				+ (z - cam.z) * (z - cam.z)) * 10) / 10.0;
		if(dist > net.nexus.hacks.PlayerEspHack.getMaxDist())
			return;
		
		g.drawString(MC.font, name + " " + dist + "m",
			sx - 20, sy - 40, color, true);
		
		// 方块 3D 线框（半格扩大显示）
		double h = 0.5;
		double[][] corners = {
			{x-h, y-h, z-h}, {x+h, y-h, z-h},
			{x+h, y-h, z+h}, {x-h, y-h, z+h},
			{x-h, y+h, z-h}, {x+h, y+h, z-h},
			{x+h, y+h, z+h}, {x-h, y+h, z+h}
		};
		int[][] edges = {{0,1},{1,2},{2,3},{3,0},
			{4,5},{5,6},{6,7},{7,4},{0,4},{1,5},{2,6},{3,7}};
		for(int[] ed : edges)
			drawLine3D(g, corners[ed[0]], corners[ed[1]],
				cam, fov, color);
	}
	
	/** 3D 线段投影到屏幕并画线 */
	private static void drawLine3D(GuiGraphics g, double[] a,
		double[] b, Vec3 cam, int fov, int color)
	{
		double[] p1 = project(a[0], a[1], a[2], cam, fov);
		double[] p2 = project(b[0], b[1], b[2], cam, fov);
		if(p1 == null || p2 == null)
			return;
		
		int x1 = (int)p1[0], y1 = (int)p1[1];
		int x2 = (int)p2[0], y2 = (int)p2[1];
		
		int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
		int sx = x1 < x2 ? 1 : -1, sy = y1 < y2 ? 1 : -1;
		int err = dx - dy;
		int x = x1, y = y1;
		while(true)
		{
			g.fill(x, y, x + 1, y + 1, color);
			if(x == x2 && y == y2)
				break;
			int e2 = 2 * err;
			if(e2 > -dy)
			{
				err -= dy;
				x += sx;
			}
			if(e2 < dx)
			{
				err += dx;
				y += sy;
			}
		}
	}
	
	/** 世界坐标 -> 屏幕坐标 */
	public static double[] project(double x, double y, double z,
		Vec3 cam, int fov)
	{
		double dx = x - cam.x, dy = y - cam.y, dz = z - cam.z;
		
		float yaw = (float)Math.toRadians(
			MC.player.getViewYRot(partialTick));
		float pitch = (float)Math.toRadians(
			MC.player.getViewXRot(partialTick));
		
		// 绕 Y 轴（yaw）
		double x1 = dx * Math.cos(-yaw) - dz * Math.sin(-yaw);
		double z1 = dx * Math.sin(-yaw) + dz * Math.cos(-yaw);
		// 绕 X 轴（pitch）
		double y2 = dy * Math.cos(pitch) - z1 * Math.sin(pitch);
		double z2 = dy * Math.sin(pitch) + z1 * Math.cos(pitch);
		
		if(z2 <= 0.05)
			return null;
		
		int width = MC.getWindow().getGuiScaledWidth();
		int height = MC.getWindow().getGuiScaledHeight();
		double focal = (height / 2.0)
			/ Math.tan(Math.toRadians(fov) / 2.0);
		
		double sx = width / 2.0 + x1 / z2 * focal;
		double sy = height / 2.0 - y2 / z2 * focal;
		
		if(sx < -200 || sx > width + 200
			|| sy < -200 || sy > height + 200)
			return null;
		
		return new double[] {sx, sy};
	}
}
