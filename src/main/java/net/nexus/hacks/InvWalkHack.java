package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;

public final class InvWalkHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public InvWalkHack()
	{
		super("InvWalk", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// 背包/任何界面打开时也能移动、跳跃
		if(MC.screen != null)
		{
			MC.player.zza = MC.options.keyUp.isDown() ? 1f : 0f;
			MC.player.zza -= MC.options.keyDown.isDown() ? 1f : 0f;
			MC.player.xxa = MC.options.keyLeft.isDown() ? 1f : 0f;
			MC.player.xxa -= MC.options.keyRight.isDown() ? 1f : 0f;
			if(MC.options.keyJump.isDown()
				&& MC.player.onGround())
				MC.player.jumpFromGround();
		}
	}
}
