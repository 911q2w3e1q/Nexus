package net.nexus.gui;

import net.nexus.NexusClient;
import net.nexus.hack.Hack;
import net.nexus.hack.Hack.Setting;
import net.nexus.util.Translate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class SettingsScreen extends Screen
{
	private final Minecraft MC = Minecraft.getInstance();
	private final Hack hack;
	private int scrollY;
	
	public SettingsScreen(Hack hack)
	{
		super(Component.literal("Nexus Settings"));
		this.hack = hack;
	}
	
	@Override
	public void render(GuiGraphics g, int mouseX, int mouseY,
		float partialTick)
	{
		// 背景
		g.fill(0, 0, width, height, 0xCC000000);
		
		// 标题
		g.drawString(MC.font,
			Translate.name(hack.getName()) + " - 设置",
			8, 8, 0xFFFFFFFF);
		g.drawString(MC.font, "Esc 返回", width - 70, 8,
			0xFFAAAAAA);
		
		// 设置列表
		int y = 30 - scrollY;
		for(Setting s : hack.getSettings())
		{
			if(y < 20 || y > height - 10)
			{
				y += 24;
				continue;
			}
			
			boolean hovered = mouseY >= y && mouseY <= y + 20;
			g.fill(4, y, width - 4, y + 20,
				hovered ? 0x663366FF : 0x33000000);
			
			if(s.isEnum)
			{
				g.drawString(MC.font, s.name, 10, y + 5,
					0xFFFFFFFF);
				g.drawString(MC.font,
					s.getEnumValue(),
					10 + MC.font.width(s.name) + 6, y + 5,
					0xFF00AAFF);
				g.drawString(MC.font, "点击切换",
					width - 100, y + 5, 0xFF888888);
			}
			else if(s.isSlider)
			{
				// 名称 + 值（值跟在名称后，防窄窗口溢出）
				String val = String.format("%.1f", s.value);
				g.drawString(MC.font, s.name, 10, y + 5,
					0xFFFFFFFF);
				g.drawString(MC.font, val,
					10 + MC.font.width(s.name) + 6, y + 5,
					0xFF00AAFF);
				
				// 进度条（加宽加高，易点击）
				int bx = width - 170;
				int bw = 150;
				g.fill(bx, y + 6, bx + bw, y + 15, 0xFF444444);
				g.fill(bx + 1, y + 7,
					bx + (int)(bw * s.getRatio()), y + 14,
					0xFF00AAFF);
				// 拖动把手
				int knobX = bx + (int)(bw * s.getRatio());
				g.fill(knobX - 3, y + 4, knobX + 3, y + 17,
					0xFFFFFFFF);
			}
			else
			{
				g.drawString(MC.font, s.name, 10, y + 5,
					0xFFFFFFFF);
				g.drawString(MC.font,
					s.boolValue ? "开" : "关",
					width - 50, y + 5,
					s.boolValue ? 0xFF00FF00 : 0xFF888888);
			}
			y += 24;
		}
		
		// 滚动条
		int total = hack.getSettings().size() * 24;
		int view = height - 40;
		if(total > view)
		{
			int barH = Math.max(20, view * view / total);
			int barY = 30 + (view - barH) * scrollY
				/ (total - view);
			g.fill(width - 4, barY, width - 1, barY + barH,
				0xFF666666);
		}
		
		// 提示：右键调节滑动条
		g.drawString(MC.font, "左键点击进度条调节",
			8, height - 14, 0xFF888888);
	}
	
	@Override
	public boolean mouseClicked(
		net.minecraft.client.input.MouseButtonEvent event, boolean bl)
	{
		double mouseX = event.x();
		double mouseY = event.y();
		if(event.button() == 0) // 左键
		{
			int y = 30 - scrollY;
			for(Setting s : hack.getSettings())
			{
				if(mouseY >= y && mouseY <= y + 20)
				{
					if(s.isEnum)
					{
						s.cycle();
					}
					else if(s.isSlider)
					{
						// 只在进度条区域点击才调值（防误触）
						int bx = width - 170;
						int bw = 150;
						if(mouseX >= bx && mouseX <= bx + bw)
						{
							float ratio = (float)((mouseX - bx) / bw);
							if(ratio < 0) ratio = 0;
							if(ratio > 1) ratio = 1;
							s.setByRatio(ratio);
						}
					}
					else
					{
						s.boolValue = !s.boolValue;
					}
					return true;
				}
				y += 24;
			}
		}
		return super.mouseClicked(event, bl);
	}
	
	@Override
	public boolean mouseDragged(net.minecraft.client.input.MouseButtonEvent
		event, double dragX, double dragY)
	{
		if(event.button() == 0)
		{
			double mouseX = event.x();
			double mouseY = event.y();
			int y = 30 - scrollY;
			for(Setting s : hack.getSettings())
			{
				if(s.isSlider
					&& mouseY >= y - 4 && mouseY <= y + 24)
				{
					int bx = width - 170;
					int bw = 150;
					if(mouseX >= bx - 6 && mouseX <= bx + bw + 6)
					{
						float ratio = (float)((mouseX - bx) / bw);
						if(ratio < 0) ratio = 0;
						if(ratio > 1) ratio = 1;
						s.setByRatio(ratio);
						return true;
					}
				}
				y += 24;
			}
		}
		return super.mouseDragged(event, dragX, dragY);
	}
	
	@Override
	public boolean mouseScrolled(double mouseX, double mouseY,
		double horizontalAmount, double verticalAmount)
	{
		int total = hack.getSettings().size() * 24;
		int view = height - 40;
		if(total > view)
		{
			scrollY += (int)(-verticalAmount * 20);
			if(scrollY < 0)
				scrollY = 0;
			int maxScroll = total - view;
			if(scrollY > maxScroll)
				scrollY = maxScroll;
		}
		return true;
	}
	
	@Override
	public boolean keyPressed(net.minecraft.client.input.KeyEvent event)
	{
		if(event.key() == 265)
		{
			scrollY = Math.max(0, scrollY - 24);
			return true;
		}
		if(event.key() == 264)
		{
			scrollY += 24;
			return true;
		}
		if(event.key() == 256) // Esc 返回
		{
			MC.setScreen(new ClickGuiScreen());
			return true;
		}
		return super.keyPressed(event);
	}
	
	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}
