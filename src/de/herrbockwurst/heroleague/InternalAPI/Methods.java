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
	public static String getLangString(String name) {
		if(Main.thisclass.lang.contains(name)) {
			return Main.thisclass.lang.getString(name); 
		}
		
		return "String not found";
	}
}
