package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;

public final class BowAimbotHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private boolean shot;
	
	public final Setting range = new Setting("范围", 30f, 5f, 64f, 1f);
	
	public BowAimbotHack()
	{
		super("BowAimbot", "战斗");
		addSetting(range);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		if(!MC.player.getMainHandItem().is(Items.BOW))
			return;
		
		// 选最近目标并瞄准
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
			return;
		
		EntityUtils.lookAt(target);
		
		// 拉弓
		if(!MC.player.isUsingItem())
		{
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
			shot = true;
		}
		
		// 蓄力满 20 tick 后放箭
		if(shot && MC.player.getTicksUsingItem() >= 20)
		{
			MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
			shot = false;
		}
	}
}
