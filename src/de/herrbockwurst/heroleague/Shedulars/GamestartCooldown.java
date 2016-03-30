package de.herrbockwurst.heroleague.Shedulars;

import org.bukkit.Bukkit;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.Listeners.Misc.InteractVillager;
import net.md_5.bungee.api.ChatColor;

public class GamestartCooldown {
	static Main mainplugin = Main.thisclass;
	
	public static void StartDelay() {
		Main.game.replace("FreezeAll", true);
		InteractVillager.registerPlayers();
		Main.PlayerMoney.regPlayers();
		Bukkit.getScheduler().runTaskLater(mainplugin, new Runnable() {
			
			@Override
			public void run() {
				Main.game.replace("FreezeAll", false);
				Bukkit.broadcastMessage(ChatColor.GREEN + "Das Spiel beginnt!");
			}
		}, 20*10);
	}
}
