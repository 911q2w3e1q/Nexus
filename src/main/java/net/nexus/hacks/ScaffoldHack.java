package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class ScaffoldHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public final Setting speed = new Setting("搭路速度", 1f, 1f, 5f, 1f);
	
	public ScaffoldHack()
	{
		super("Scaffold", "世界");
		addSetting(speed);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		BlockPos feet = MC.player.blockPosition().below();
		if(!MC.level.getBlockState(feet).isAir())
			return;
		
		// find any block
		int slot = -1;
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = MC.player.getInventory().getItem(i);
			if(stack != null && !stack.isEmpty())
			{
				slot = i;
				break;
			}
		}
		if(slot < 0)
			return;
		
		MC.player.getInventory().setSelectedSlot(slot);
		BlockHitResult hit = new BlockHitResult(Vec3.atCenterOf(feet),
			Direction.UP, feet, false);
		MC.gameMode.useItemOn(MC.player, InteractionHand.MAIN_HAND, hit);
	}
}
