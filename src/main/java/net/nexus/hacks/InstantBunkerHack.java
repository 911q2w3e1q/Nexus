package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class InstantBunkerHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public InstantBunkerHack()
	{
		super("InstantBunker", "世界");
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
		
		// 全包 5 面 + 脚下
		BlockPos feet = MC.player.blockPosition();
		BlockPos[] positions = {
			feet.offset(1, 0, 0), feet.offset(-1, 0, 0),
			feet.offset(0, 0, 1), feet.offset(0, 0, -1),
			feet.offset(0, 1, 0), feet.offset(0, -1, 0)};
		
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
