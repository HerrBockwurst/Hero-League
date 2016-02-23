package de.herrbockwurst.heroleague.InternalAPI;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	
	public static String createMessage(String string, ChatColor color) {
		String msg;
		ResultSet result = MySQL.queryResult("SELECT `string` FROM `language` WHERE `key` = '" + string + "'");
			
		try {
			msg = getPluginName(true) + " " + color + result.getString("string");
			return msg;
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		System.out.println(getPluginName(true) + " [ERROR] Invalid string \"" + string + "\"");
		return null;
	}
}
