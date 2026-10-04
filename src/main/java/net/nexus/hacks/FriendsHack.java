package net.nexus.hacks;

import net.nexus.hack.Hack;

import java.util.LinkedHashSet;
import java.util.Set;

public final class FriendsHack extends Hack
{
	private static final Set<String> FRIENDS =
		new LinkedHashSet<>();
	
	public FriendsHack()
	{
		super("Friends", "其他");
	}
	
	public static boolean isFriend(String name)
	{
		return FRIENDS.contains(name.toLowerCase());
	}
	
	public static void addFriend(String name)
	{
		FRIENDS.add(name.toLowerCase());
	}
	
	public static void removeFriend(String name)
	{
		FRIENDS.remove(name.toLowerCase());
	}
	
	public static String getFriends()
	{
		return String.join(", ", FRIENDS);
	}
}
