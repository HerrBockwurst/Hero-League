package de.herrbockwurst.heroleague;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import de.herrbockwurst.heroleague.InternalAPI.Methods;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfig;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfigManager;
import de.herrbockwurst.heroleague.Listeners.Player.PlayerJoin;

public class Main extends JavaPlugin {

	public static Main thisclass = null;
    public SimpleConfigManager manager;
    public SimpleConfig config;
    public SimpleConfig lang;
    public HashMap<String, Object> game = new HashMap<>();
	
	@Override
	public void onEnable() {
		thisclass = this;
		
		getConf();
		Methods.initGame(game);
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
}
