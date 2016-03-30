package de.herrbockwurst.heroleague.Listeners.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.herrbockwurst.heroleague.Main;

public class PlayerFreezeCheck  implements Listener {
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerMove (PlayerMoveEvent ev) {
		
		double fromX = ev.getFrom().getX();
		double fromY = ev.getFrom().getY();
		double fromZ = ev.getFrom().getZ();
		double toX = ev.getTo().getX();
		double toY = ev.getTo().getY();
		double toZ = ev.getTo().getZ();
		if(fromX == toX && fromY == toY && fromZ == toZ) return;
		
		if(Boolean.valueOf(Main.game.get("FreezeAll").toString())) {
			ev.setCancelled(true);
		}
	}
}
