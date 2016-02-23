package de.herrbockwurst.heroleague;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import de.herrbockwurst.heroleague.InternalAPI.MySQL;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfig;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfigManager;
import de.herrbockwurst.heroleague.Listeners.Player.PlayerJoin;

public class Main extends JavaPlugin {

	public static Main thisclass = null;
    public SimpleConfigManager manager;
    public SimpleConfig config;;
    public HashMap<String, Object> game = new HashMap<>();
    public HashMap<String, String> lang = new HashMap<>();
	
	@Override
	public void onEnable() {
		thisclass = this;
		
		getConf();
		initGame(game);
		initLang(lang);
		registerEvents();
		
	}

	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		
	}

	private void getConf() {
		
		manager = new SimpleConfigManager(this);
		config = manager.getNewConfig("conf.yml");
		
		FixConfig.fix(this);
		
	}
	
	public static void initLang(HashMap<String, String> lang) {
		ResultSet result = MySQL.queryResult("SELECT * FROM `language`");
		try {
			while (result.next()) {
				lang.put(result.getString("key"), result.getString("string"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void initGame(HashMap<String, Object> game) {
		game.put("isRunning", false);
	}
}
