package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.nexus.util.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;

public final class KillauraHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	private float attackTimer;
	
	public final Setting range = new Setting("范围", 4.25f, 2f, 8f, 0.25f);
	public final Setting speed = new Setting("攻击间隔", 1f, 1f, 10f, 1f);
	public final Setting autoRotate = new Setting("自动旋转", true);
	public final Setting targetPlayers = new Setting("攻击玩家", true);
	public final Setting targetMobs = new Setting("攻击怪物", true);
	
	public KillauraHack()
	{
		super("Killaura", "战斗");
		addSetting(range);
		addSetting(speed);
		addSetting(autoRotate);
		addSetting(targetPlayers);
		addSetting(targetMobs);
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null || MC.level == null)
			return;
		
		// 攻击间隔累计（float 精度，支持 1.5 tick 间隔）
		attackTimer += speed.value;
		if(attackTimer < 20f)
			return;
		attackTimer -= 20f;
		
		Entity target = null;
		double best = range.value;
		for(Entity e : EntityUtils.getAttackableEntities(
			targetPlayers.boolValue, targetMobs.boolValue))
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
		
		// 自动旋转视角对准（服务器攻击判定需要视线内）
		if(autoRotate.boolValue)
			EntityUtils.lookAt(target);
		
		MC.gameMode.attack(MC.player, target);
		MC.player.swing(InteractionHand.MAIN_HAND);
	}
}
