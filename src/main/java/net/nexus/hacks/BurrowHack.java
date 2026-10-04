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

public final class BurrowHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public BurrowHack()
	{
		super("Burrow", "战斗");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 脚下埋方块（把自己包进方块防打）
		BlockPos feet = MC.player.blockPosition();
		if(!MC.level.getBlockState(feet).isAir())
			return;
		
		ItemStack obsidian = findBlock();
		if(obsidian == null)
			return;
		
		MC.gameMode.useItemOn(MC.player, InteractionHand.MAIN_HAND,
			new BlockHitResult(Vec3.atCenterOf(feet),
				Direction.UP, feet, false));
	}
	
	private ItemStack findBlock()
	{
		for(int i = 0; i < 9; i++)
		{
			var s = MC.player.getInventory().getItem(i);
			if(s != null && s.is(Items.OBSIDIAN))
			{
				MC.player.getInventory().setSelectedSlot(i);
				return s;
			}
		}
		return null;
	}
}
