package net.nexus.hack;

import java.util.ArrayList;
import java.util.List;

public abstract class Hack
{
	private final String name;
	private final String category;
	private boolean enabled;
	private final List<Setting> settings = new ArrayList<>();
	
	public final Setting sneakTrigger = new Setting("潜行触发", false);
	public final Setting sprintTrigger = new Setting("疾跑触发", false);
	
	public Hack(String name, String category)
	{
		this.name = name;
		this.category = category;
		addSetting(sneakTrigger);
		addSetting(sprintTrigger);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void toggle()
	{
		enabled = !enabled;
		if(enabled)
			onEnable();
		else
			onDisable();
	}
	
	public void setEnabled(boolean enabled)
	{
		if(this.enabled == enabled)
			return;
		this.enabled = enabled;
		if(enabled)
			onEnable();
		else
			onDisable();
	}
	
	protected void onEnable()
	{
	}
	
	protected void onDisable()
	{
	}
	
	public void onTick()
	{
	}
	
	/** 检查触发条件（潜行/疾跑触发设置） */
	public boolean checkTrigger()
	{
		if(sneakTrigger.boolValue)
		{
			var player = net.minecraft.client.Minecraft.getInstance().player;
			if(player == null || !player.isShiftKeyDown())
				return false;
		}
		if(sprintTrigger.boolValue)
		{
			var player = net.minecraft.client.Minecraft.getInstance().player;
			if(player == null || !player.isSprinting())
				return false;
		}
		return true;
	}
	
	// ===== 设置系统 =====
	public static final class Setting
	{
		public final String name;
		public final boolean isSlider;
		// 滑动条
		public float value;
		public final float min;
		public final float max;
		public final float step;
		// 开关
		public boolean boolValue;
		
		public Setting(String name, float value, float min,
			float max, float step)
		{
			this.name = name;
			this.isSlider = true;
			this.value = value;
			this.min = min;
			this.max = max;
			this.step = step;
		}
		
		public Setting(String name, boolean value)
		{
			this.name = name;
			this.isSlider = false;
			this.boolValue = value;
			this.value = 0;
			this.min = 0;
			this.max = 1;
			this.step = 1;
		}
		
		/** 按位置设置滑动值（0~1 比例） */
		public void setByRatio(float ratio)
		{
			if(ratio < 0)
				ratio = 0;
			if(ratio > 1)
				ratio = 1;
			float v = min + ratio * (max - min);
			v = Math.round(v / step) * step;
			if(v < min)
				v = min;
			if(v > max)
				v = max;
			value = v;
		}
		
		public float getRatio()
		{
			return (value - min) / (max - min);
		}
	}
	
	public void addSetting(Setting s)
	{
		settings.add(s);
	}
	
	public List<Setting> getSettings()
	{
		return settings;
	}
	
	public boolean hasSettings()
	{
		return !settings.isEmpty();
	}
}
