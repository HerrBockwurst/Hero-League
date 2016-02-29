package de.herrbockwurst.heroleague.Listeners.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.herrbockwurst.heroleague.Main;

public class PlayerJoin implements Listener {
	
	private Main main = Main.thisclass;
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent ev) {
		
		Player p = ev.getPlayer(); 
		
		//check if game is already running
		if((Boolean) main.game.get("isRunning")) {
			//tp to team spawn
		} else {
			if(main.TeamBlau.size() < main.TeamRot.size()) {
				
			} else {
				
			}
			//tp to lobby
		}
	}
	
}
