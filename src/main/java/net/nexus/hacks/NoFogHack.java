package net.nexus.hacks;

import net.nexus.hack.Hack;
import org.joml.Vector4f;

public final class NoFogHack extends Hack
{
	private static boolean active;
	private static float far;

	public NoFogHack()
	{
		super("NoFog", "渲染");
	}

	/** 清除雾：把雾距拉到极远 */
	public static void clearFog(Vector4f v)
	{
		if(!active || v == null)
			return;
		v.x = far;
		v.y = far;
	}

	public static boolean isActive()
	{
		return active;
	}

	@Override
	protected void onEnable()
	{
		active = true;
		far = 1000000.0f;
	}

	@Override
	protected void onDisable()
	{
		active = false;
	}
}
