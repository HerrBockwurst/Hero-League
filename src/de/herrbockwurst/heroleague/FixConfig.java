package de.herrbockwurst.heroleague;

import java.util.ArrayList;
import java.util.List;

import de.herrbockwurst.heroleague.InternalAPI.Methods;


public class FixConfig {
	public static void fix() {
		fixMain();
	}

	private static void fixMain() {
		
		List<String> keys = new ArrayList<>();
		List<String> values = new ArrayList<>();
		
		//MySQL Wert
		keys.add("MySQL.host");			values.add("localhost");
		keys.add("MySQL.database");		values.add("database");
		keys.add("MySQL.user");			values.add("Username");
		keys.add("MySQL.password");		values.add("Password");
		keys.add("MySQL.port");			values.add("3306");
		
		//Koordinaten
		keys.add("Game.Lobby.World");		values.add("world");
		keys.add("Game.Lobby.X");			values.add("0");
		keys.add("Game.Lobby.Y");			values.add("0");
		keys.add("Game.Lobby.Z");			values.add("0");
		keys.add("Game.Lobby.Yaw");			values.add("0");
		keys.add("Game.Lobby.Pitch");		values.add("0");
		
		keys.add("Game.InGame.World");			values.add("arena");
		keys.add("Game.InGame.Rot.X");			values.add("0");
		keys.add("Game.InGame.Rot.Y");			values.add("0");
		keys.add("Game.InGame.Rot.Z");			values.add("0");
		keys.add("Game.InGame.Rot.Yaw");		values.add("0");
		keys.add("Game.InGame.Rot.Pitch");		values.add("0");
		keys.add("Game.InGame.Blau.X");			values.add("0");
		keys.add("Game.InGame.Blau.Y");			values.add("0");
		keys.add("Game.InGame.Blau.Z");			values.add("0");
		keys.add("Game.InGame.Blau.Yaw");		values.add("0");
		keys.add("Game.InGame.Blau.Pitch");		values.add("0");
		
		/*
		keys.add("");		values.add("");
		 */
		
		if(keys.size() != values.size()) {
			System.out.println(Methods.getPluginName(true) + " Default Keys und Values sind unterschiedlich gross!");
		}
		
		for(int i = 0; i < keys.size() ; i++) {
			if(!(Main.config.contains(keys.get(i)))) {
				Main.config.set(keys.get(i), values.get(i));
				Main.config.saveConfig();
			}
		}
		
	}
	
}
