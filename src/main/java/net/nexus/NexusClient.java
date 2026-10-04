package net.nexus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.KeyMapping.Category;
import net.nexus.hack.Hack;
import net.nexus.hack.HackList;
import net.nexus.util.Translate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public final class NexusClient implements ClientModInitializer
{
	public static final String NAME = "Nexus Client";
	public static final String VERSION = "a0.6";
	public static final String MC_VERSION = "1.21.11";
	
	private static NexusClient INSTANCE;
	public final Minecraft MC = Minecraft.getInstance();
	public final HackList hackList = new HackList();
	private boolean guiKeyWasDown;
	
	@Override
	public void onInitializeClient()
	{
		INSTANCE = this;
		System.out.println("[NEXUS] onInitializeClient OK");
		
		// 自定义按键分类：键位设置里显示独立的 "Nexus Client" 分组
		Category nexusCat = Category.register(
			net.minecraft.resources.Identifier.fromNamespaceAndPath("nexus", "nexus"));
		
		// 注册按键：右Ctrl=选项界面、右Shift=搜索界面（可在原版键位设置里改）
		openGuiKey = KeyBindingHelper.registerKeyBinding(
			new KeyMapping("key.nexus.gui", 345, nexusCat));
		openSearchKey = KeyBindingHelper.registerKeyBinding(
			new KeyMapping("key.nexus.search", 344, nexusCat));
		
		// 沙箱测试钩子：仅 NEXUS_TEST_GUI=1 环境变量时触发
		if(System.getenv("NEXUS_TEST_GUI") != null)
			testGuiMode = 1;
		
		// 每 tick
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			onTick();
			handleGuiKey();
		});
		
		// HUD 渲染
		HudRenderCallback.EVENT.register((graphics, tickDelta) -> {
			renderHud(graphics, tickDelta.getGameTimeDeltaPartialTick(true));
		});
	}
	
	public static NexusClient getInstance()
	{
		return INSTANCE;
	}
	
	public void onTick()
	{
		hackList.onTick();
		runTestHook();
	}
	
	private void runTestHook()
	{
		if(testGuiMode < 0 || MC.player == null)
			return;
		
		testGuiTicks++;
		if(testGuiTicks % 700 == 200)
			MC.setScreen(new net.nexus.gui.ClickGuiScreen());
		else if(testGuiTicks % 700 == 500)
			MC.setScreen(null);
	}
	
	private KeyMapping openGuiKey;
	private KeyMapping openSearchKey;
	private int testGuiMode = -1;
	private int testGuiTicks;
	
	private void handleGuiKey()
	{
		// 右Ctrl = 选项界面
		if(openGuiKey.consumeClick())
		{
			if(MC.screen == null)
				MC.setScreen(new net.nexus.gui.ClickGuiScreen());
			else if(MC.screen instanceof net.nexus.gui.ClickGuiScreen)
				MC.setScreen(null);
		}
		
		// 右Shift = 搜索界面
		if(openSearchKey.consumeClick())
		{
			if(MC.screen == null)
				MC.setScreen(new net.nexus.gui.NavigatorScreen());
			else if(MC.screen instanceof net.nexus.gui.NavigatorScreen)
				MC.setScreen(null);
		}
	}
	
	private void renderHud(GuiGraphics graphics, float tickDelta)
	{
		if(MC.options.hideGui)
			return;
		
		// 左上角标题
		graphics.drawString(MC.font, NAME + " " + VERSION + " " + MC_VERSION,
			2, 2, 0xFFFFFFFF);
		
		// 已启用功能列表（带背景防重叠）
		int y = 14;
		for(Hack hack : hackList.getAll())
		{
			if(!hack.isEnabled())
				continue;
			String text = Translate.name(hack.getName());
			int w = MC.font.width(text) + 4;
			graphics.fill(2, y - 1, 2 + w, y + 10, 0x66000000);
			graphics.drawString(MC.font, text, 4, y, 0xFF00FF00);
			y += 12;
			if(y > 90)
				break;
		}
		
		// ESP 渲染
		net.nexus.render.EspRenderer.render(graphics, tickDelta);

	}
}
