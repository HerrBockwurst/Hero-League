package de.herrbockwurst.heroleague;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import de.herrbockwurst.heroleague.Commands.setCoord;
import de.herrbockwurst.heroleague.Heroes.EHeroes;
import de.herrbockwurst.heroleague.Heroes.IHeroes;
import de.herrbockwurst.heroleague.InternalAPI.GoldSystem;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfig;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfigManager;
import de.herrbockwurst.heroleague.Listeners.Player.*;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

	public static Main thisclass = null;
    public SimpleConfigManager manager;
    public static SimpleConfig config;;
    public static HashMap<String, Object> game = new HashMap<>();
	public static List<UUID> TeamRot = new ArrayList<UUID>();
	public static List<UUID> TeamBlau = new ArrayList<UUID>();
	public static HashMap<UUID, String> PlayerHeroes = new HashMap<>();
	public static HashMap<UUID, Integer> PlayerDeath = new HashMap<>();
	public static GoldSystem PlayerMoney = new GoldSystem();
	
	
	@Override
	public void onEnable() {
		thisclass = this;
		
		getConf();
		initGame();
		registerEvents();
		registerCommands();
		
	}
		
	

	private void registerCommands() {
		getCommand("setcoord").setExecutor(new setCoord());
		
	}

	private void registerEvents() {
		
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerDamage(), this);
		getServer().getPluginManager().registerEvents(new InventoryMenus(), this);
		getServer().getPluginManager().registerEvents(new Grass(), this);
	}

	private void getConf() {
		
		manager = new SimpleConfigManager(this);
		config = manager.getNewConfig("conf.yml");
		
		FixConfig.fix();
		
	}
	
	private static void initGame() {
		game.put("isRunning", false);
		game.put("FreezeAll", false);
		if(config.getBoolean("Ranked")) {
			game.put("GameType", "Ranked");
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
