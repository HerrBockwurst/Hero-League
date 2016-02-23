package de.herrbockwurst.heroleague;

import org.bukkit.plugin.java.JavaPlugin;

import de.herrbockwurst.heroleague.InternalAPI.SimpleConfig;
import de.herrbockwurst.heroleague.InternalAPI.SimpleConfigManager;

public class Main extends JavaPlugin {

	public static Main thisclass = null;
    public SimpleConfigManager manager;
    public SimpleConfig config;
    public SimpleConfig lang;
    public SimpleConfig lobbys;
	
	@Override
	public void onEnable() {
		thisclass = this;
	}
}
