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

public final class SurroundHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public final Setting includeHead = new Setting("包含头顶", true);
	public final Setting includeFeet = new Setting("包含脚下", false);
	
	public SurroundHack()
	{
		super("Surround", "世界");
		addSetting(includeHead);
		addSetting(includeFeet);
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
		
		// 找黑曜石
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
		
		BlockPos feet = MC.player.blockPosition();
		java.util.List<BlockPos> positions =
			new java.util.ArrayList<>();
		positions.add(feet.offset(1, 0, 0));
		positions.add(feet.offset(-1, 0, 0));
		positions.add(feet.offset(0, 0, 1));
		positions.add(feet.offset(0, 0, -1));
		if(includeHead.boolValue)
			positions.add(feet.offset(0, 1, 0));
		if(includeFeet.boolValue)
			positions.add(feet.offset(0, -1, 0));
		
		for(BlockPos pos : positions)
		{
			var bs = MC.level.getBlockState(pos);
			if(bs.isAir()
				|| !bs.getFluidState().isEmpty())
			{
				MC.player.getInventory().setSelectedSlot(
					slot < 9 ? slot : 0);
				BlockHitResult hit = new BlockHitResult(
					Vec3.atCenterOf(pos), Direction.UP, pos, false);
				MC.gameMode.useItemOn(MC.player,
					InteractionHand.MAIN_HAND, hit);
				cooldown = 2;
				return;
			}
		}
	}
}
