package de.herrbockwurst.heroleague;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import de.herrbockwurst.heroleague.Commands.setCoord;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfig;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfigManager;
import de.herrbockwurst.heroleague.Listeners.Player.PlayerJoin;

public class Main extends JavaPlugin {

	public static Main thisclass = null;
    public SimpleConfigManager manager;
    public static SimpleConfig config;;
    public static HashMap<String, Object> game = new HashMap<>();
	public static List<String> TeamRot = new ArrayList<String>();
	public static List<String> TeamBlau = new ArrayList<String>();
	public static HashMap<String, Object> PlayerHeroes = new HashMap<>(); 
	public static List<Integer> Heroes = new ArrayList<Integer>();
	
	@Override
	public void onEnable() {
		thisclass = this;
		
		getConf();
		initGame();
		registerEvents();
		registerCommands();
		registerHeroes();
		
	}

	private void registerHeroes() {
		//Hier weiter!
	}

	private void registerCommands() {
		getCommand("setcoord").setExecutor(new setCoord());
		
	}

	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		
	}

	private void getConf() {
		
		manager = new SimpleConfigManager(this);
		config = manager.getNewConfig("conf.yml");
		
		FixConfig.fix();
		
	}
	
	private static void initGame() {
		game.put("isRunning", false);
		if(config.getBoolean("Ranked")) {
			game.put("GameType", " Ranked ");
		} else {
			game.put("GameType", "Unranked");
		}
		
		game.put("lobbyX", config.get("Game.Lobby.X"));
		game.put("lobbyY", config.get("Game.Lobby.Y"));
		game.put("lobbyZ", config.get("Game.Lobby.Z"));
		game.put("lobbyPitch", config.get("Game.Lobby.Pitch"));
		game.put("lobbyYaw", config.get("Game.Lobby.Yaw"));
		game.put("lobbyWorld", config.get("Game.Lobby.World"));

		game.put("IngameWorld", config.get("Game.Ingame.World"));
		
		game.put("RotX", config.get("Game.Ingame.Rot.X"));
		game.put("RotY", config.get("Game.Ingame.Rot.Y"));
		game.put("RotZ", config.get("Game.Ingame.Rot.Z"));
		game.put("RotPitch", config.get("Game.Ingame.Rot.Pitch"));
		game.put("RotYaw", config.get("Game.Ingame.Rot.Yaw"));

		game.put("BlauX", config.get("Game.Ingame.Blau.X"));
		game.put("BlauY", config.get("Game.Ingame.Blau.Y"));
		game.put("BlauZ", config.get("Game.Ingame.Blau.Z"));
		game.put("BlauPitch", config.get("Game.Ingame.Blau.Pitch"));
		game.put("BlauYaw", config.get("Game.Ingame.Blau.Yaw"));
		
	}
}
