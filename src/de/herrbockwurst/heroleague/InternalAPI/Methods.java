package de.herrbockwurst.heroleague.InternalAPI;

import java.util.HashMap;

import org.bukkit.ChatColor;

import de.herrbockwurst.heroleague.Main;

public class Methods {
	
	public static String getPluginName(Boolean form) {
		if(form == false) {
			return Main.thisclass.getDescription().getName();
		} else {
			return ChatColor.DARK_GREEN + "[" + Main.thisclass.getDescription().getName() + "]";
		}		
	}
	public static String getLangString(String name) {
		if(Main.thisclass.lang.contains(name)) {
			return Main.thisclass.lang.getString(name); 
		}
		
		return "String not found";
	}
	public static void initGame(HashMap<String, Object> game) {
		game.put("isRunning", false);
	}
	public static String createMessage(String string, ChatColor color) {
		String msg;
		if(Main.thisclass.lang.contains(string)) {
			msg = getPluginName(true) + " " + color + Main.thisclass.lang.getString(string);
			return msg;
		}
		System.out.println(getPluginName(true) + " [ERROR] Invalid string \"" + string + "\"");
		return null;
	}
}
