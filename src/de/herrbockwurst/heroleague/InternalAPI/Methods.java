package de.herrbockwurst.heroleague.InternalAPI;

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
	
	
	public static String createMessage(String string, ChatColor color, Boolean WithPrefix) {
		String msg = null;
		
		if(Main.thisclass.lang.containsKey(string)) {
			if(WithPrefix) {
				msg = getPluginName(true) + " " + color + Main.thisclass.lang.get(string);
			} else {
				msg = color + Main.thisclass.lang.get(string);
			}
		} else {
			System.out.println(getPluginName(true) + " [ERROR] Invalid string \"" + string + "\"");
			return null;
		}

		
		return msg;
		
	}
}
