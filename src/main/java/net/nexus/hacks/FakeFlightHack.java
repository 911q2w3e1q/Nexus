package net.nexus.hacks;

import net.nexus.hack.Hack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public final class FakeFlightHack extends Hack
{
	private final Minecraft MC = Minecraft.getInstance();
	
	public FakeFlightHack()
	{
		super("FakeFlight", "移动");
	}
	
	@Override
	public void onTick()
	{
		if(MC.player == null)
			return;
		
		// slow fall / hover
		Vec3 v = MC.player.getDeltaMovement();
		if(!MC.player.onGround())
		{
			if(v.y < -0.05)
				v = new Vec3(v.x, -0.05, v.z);
			
			if(MC.options.keyJump.isDown())
				v = new Vec3(v.x, 0.2, v.z);
			else if(MC.options.keyShift.isDown())
				v = new Vec3(v.x, -0.2, v.z);
		}
		MC.player.setDeltaMovement(v);
	}
}
