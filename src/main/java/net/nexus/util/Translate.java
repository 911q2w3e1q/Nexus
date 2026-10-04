package net.nexus.util;

import java.util.HashMap;
import java.util.Map;

public final class Translate
{
	private static final Map<String, String> MAP = new HashMap<>();
	
	public static void put(String key, String value)
	{
		MAP.put(key, value);
	}
	
	public static String tr(String key)
	{
		String value = MAP.get(key);
		return value != null ? value : key;
	}
	
	public static String name(String englishName)
	{
		return tr("name." + englishName.toLowerCase());
	}
}
