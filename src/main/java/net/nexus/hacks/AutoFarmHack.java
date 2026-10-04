package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class AutoFarmHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public AutoFarmHack()
	{
		super("AutoFarm", "世界");
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
		
		// 手持种子时对面前耕地播种
		var held = MC.player.getMainHandItem();
		boolean seed = held.is(Items.WHEAT_SEEDS)
			|| held.is(Items.BEETROOT_SEEDS)
			|| held.is(Items.CARROT)
			|| held.is(Items.POTATO);
		if(!seed)
			return;
		
		if(MC.hitResult instanceof BlockHitResult bhr)
		{
			var pos = bhr.getBlockPos();
			if(MC.level.getBlockState(pos).is(Blocks.FARMLAND))
			{
				MC.gameMode.useItemOn(MC.player,
					InteractionHand.MAIN_HAND,
					new BlockHitResult(Vec3.atCenterOf(pos),
						Direction.UP, pos, false));
				cooldown = 2;
			}
		}
	}
}
