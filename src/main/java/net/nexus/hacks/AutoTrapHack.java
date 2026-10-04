package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class AutoTrapHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public AutoTrapHack()
	{
		super("AutoTrap", "世界");
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
		
		Entity target = null;
		double bestDist = 5;
		for(Entity e : MC.level.entitiesForRendering())
		{
			if(e == MC.player || !e.isAlive())
				continue;
			double d = MC.player.distanceTo(e);
			if(d < bestDist)
			{
				bestDist = d;
				target = e;
			}
		}
		if(target == null)
			return;
		
		int slot = -1;
		for(int i = 0; i < 36; i++)
		{
			ItemStack stack = MC.player.getInventory().getItem(i);
			if(stack != null && stack.is(Items.OBSIDIAN))
			{
				slot = i;
				break;
			}
		}
		if(slot < 0)
			return;
		
		// place blocks above target
		BlockPos base = target.blockPosition();
		BlockPos[] positions = {
			base.above(), base.above().above(),
			base.offset(1, 0, 0), base.offset(-1, 0, 0),
			base.offset(0, 0, 1), base.offset(0, 0, -1)};
		
		for(BlockPos pos : positions)
		{
			if(MC.level.getBlockState(pos).isAir())
			{
				MC.player.getInventory().setSelectedSlot(slot < 9 ? slot : 0);
				BlockHitResult hit = new BlockHitResult(
					Vec3.atCenterOf(pos), Direction.UP, pos, false);
				MC.gameMode.useItemOn(MC.player,
					InteractionHand.MAIN_HAND, hit);
				cooldown = 5;
				return;
			}
		}
	}
}
