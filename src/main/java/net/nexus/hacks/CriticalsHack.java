package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;

public final class CriticalsHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private boolean jumped;
	
	public final Setting range = new Setting("范围", 4f, 2f, 8f, 0.25f);
	
	public CriticalsHack()
	{
		super("Criticals", "战斗");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 有目标且在地面时先跳，落下的瞬间攻击（暴击）
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
		{
			jumped = false;
			return;
		}
		
		if(MC.player.onGround() && !jumped)
		{
			MC.player.jumpFromGround();
			jumped = true;
			return;
		}
		
		if(jumped && !MC.player.onGround())
		{
			EntityUtils.lookAt(target);
			MC.gameMode.attack(MC.player, target);
			MC.player.swing(InteractionHand.MAIN_HAND);
			jumped = false;
		}
	}
}
