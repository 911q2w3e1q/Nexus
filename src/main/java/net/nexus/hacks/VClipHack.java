package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public final class VClipHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private int cooldown;
	
	public final Setting distance = new Setting("距离", 5f, 1f, 15f, 1f);
	
	public VClipHack()
	{
		super("VClip", "移动");
		addSetting(distance);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		if(cooldown > 0)
		{
			cooldown--;
			return;
		}
		
		boolean up = MC.options.keyJump.isDown()
			&& MC.options.keyShift.isDown();
		boolean down = MC.options.keyShift.isDown()
			&& MC.options.keyAttack.isDown();
		
		if(up)
		{
			BlockPos p = MC.player.blockPosition();
			MC.player.setPos(p.getX() + 0.5, p.getY() + distance.value, p.getZ() + 0.5);
			cooldown = 10;
		}else if(down)
		{
			BlockPos p = MC.player.blockPosition();
			MC.player.setPos(p.getX() + 0.5, p.getY() - distance.value, p.getZ() + 0.5);
			cooldown = 10;
		}
	}
}
