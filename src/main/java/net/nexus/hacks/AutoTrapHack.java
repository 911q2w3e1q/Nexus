package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class AutoTrapHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public final Setting range = new Setting("范围", 4f, 2f, 8f, 0.5f);
	
	public AutoTrapHack()
	{
		super("AutoTrap", "世界");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		if(cooldown > 0)
		{
			cooldown--;
			return;
		}
		
		// 选最近目标
		Entity target = null;
		double best = range.value;
		for(Entity e : EntityUtils.getAttackableEntities(true, true))
		{
			double d = EntityUtils.getDistanceTo(e);
			if(d < best)
			{
				best = d;
				target = e;
			}
		}
		if(target == null)
			return;
		
		// 找方块
		int slot = -1;
		for(int i = 0; i < 36; i++)
		{
			var s = MC.player.getInventory().getItem(i);
			if(s != null && !s.isEmpty()
				&& s.getItem() instanceof
				net.minecraft.world.item.BlockItem)
			{
				slot = i;
				break;
			}
		}
		if(slot < 0)
			return;
		
		// 目标周围放方块困住
		BlockPos tp = target.blockPosition();
		BlockPos[] positions = {
			tp.offset(1, 0, 0), tp.offset(-1, 0, 0),
			tp.offset(0, 0, 1), tp.offset(0, 0, -1),
			tp.offset(0, 1, 0)};
		
		for(BlockPos pos : positions)
		{
			if(MC.level.getBlockState(pos).isAir())
			{
				MC.player.getInventory().setSelectedSlot(
					slot < 9 ? slot : 0);
				MC.gameMode.useItemOn(MC.player,
					InteractionHand.MAIN_HAND,
					new BlockHitResult(Vec3.atCenterOf(pos),
						Direction.UP, pos, false));
				cooldown = 2;
				return;
			}
		}
	}
}
