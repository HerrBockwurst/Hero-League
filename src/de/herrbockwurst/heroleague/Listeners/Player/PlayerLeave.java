package de.herrbockwurst.heroleague.Listeners.Player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.Shedulars.GameStartCountdown;

public class PlayerLeave implements Listener {
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerLeave (PlayerQuitEvent ev) {
		Player p =ev.getPlayer();
		
		if(Bukkit.getOnlinePlayers().size() == 1) {
			if (PlayerJoin.cd != null) GameStartCountdown.stop();
		}
		if(!(Boolean.getBoolean(Main.game.get("isRunning").toString()))) {
			
			//Spieler aus Team entfernen
			if(Main.TeamBlau.contains(p.getUniqueId())) Main.TeamBlau.remove(p.getUniqueId());
			else Main.TeamRot.remove(p.getUniqueId());
			
		}
		if(Boolean.getBoolean(Main.game.get("isRunning").toString())) {
			Main.PlayerDeath.put(p.getUniqueId(), -1);
		}
	}

}
