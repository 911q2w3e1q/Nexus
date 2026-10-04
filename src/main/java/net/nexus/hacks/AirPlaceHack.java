package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class AirPlaceHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int tick;
	
	public final Setting range = new Setting("距离", 5f, 2f, 8f, 0.5f);
	public final Setting guide = new Setting("显示引导线", true);
	
	public AirPlaceHack()
	{
		super("AirPlace", "世界");
		addSetting(range);
		addSetting(guide);
	}
	
	public static boolean isActive()
	{
		return net.nexus.NexusClient.getInstance().hackList.airPlace
			.isEnabled();
	}
	
	public static float getRange()
	{
		return net.nexus.NexusClient.getInstance().hackList.airPlace
			.range.value;
	}
	
	public static boolean showGuide()
	{
		return net.nexus.NexusClient.getInstance().hackList.airPlace
			.guide.boolValue;
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 沿视线射程末端放置（对齐主流客户端行为）
		Vec3 eye = MC.player.getEyePosition(1.0f);
		Vec3 dir = MC.player.getLookAngle();
		BlockPos pos = BlockPos.containing(
			eye.x + dir.x * range.value,
			eye.y + dir.y * range.value,
			eye.z + dir.z * range.value);
		
		var bs = MC.level.getBlockState(pos);
		boolean liquidOk = net.nexus.hacks.LiquidInteractHack
			.isActive()
			&& !bs.getFluidState().isEmpty();
		if(!bs.isAir() && !liquidOk)
			return;
		
		// 找方块
		int slot = -1;
		for(int i = 0; i < 9; i++)
		{
			var stack = MC.player.getInventory().getItem(i);
			if(stack != null && !stack.isEmpty()
				&& stack.getItem() instanceof
				net.minecraft.world.item.BlockItem)
			{
				slot = i;
				break;
			}
		}
		if(slot < 0)
			return;
		
		MC.player.getInventory().setSelectedSlot(slot);
		BlockHitResult hit = new BlockHitResult(
			Vec3.atCenterOf(pos),
			Direction.UP, pos, false);
		MC.gameMode.useItemOn(MC.player, InteractionHand.MAIN_HAND, hit);
	}
}
