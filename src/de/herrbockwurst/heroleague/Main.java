package de.herrbockwurst.heroleague;

import org.bukkit.plugin.java.JavaPlugin;

import de.herrbockwurst.heroleague.InternalAPI.SimpleConfig;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfigManager;

public class Main extends JavaPlugin {

	public static Main thisclass = null;
    public SimpleConfigManager manager;
    public SimpleConfig config;
    public SimpleConfig lang;
	
	@Override
	public void onEnable() {
		thisclass = this;
		
		getConf();
	}

	private void getConf() {
		
		manager = new SimpleConfigManager(this);
		config = manager.getNewConfig("conf.yml");
		
		FixConfig.fix(this);
		
	}
}
