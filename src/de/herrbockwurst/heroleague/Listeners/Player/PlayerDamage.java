package de.herrbockwurst.heroleague.Listeners.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import de.herrbockwurst.heroleague.Main;

public class PlayerDamage implements Listener {
	
	//Kein Schaden in Team
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerDamage(EntityDamageByEntityEvent ev) {
		if(ev.getEntity() instanceof Player && ev.getDamager() instanceof Player) {
			Player p = (Player) ev.getEntity();
			Player o = (Player) ev.getDamager();
			
			if(Main.TeamBlau.contains(p.getUniqueId()) && Main.TeamBlau.contains(o.getUniqueId())) {
				ev.setCancelled(true);
			} else if(Main.TeamRot.contains(p.getUniqueId()) && Main.TeamRot.contains(o.getUniqueId())) {
				ev.setCancelled(true);
				
						
			}
				
		}
	}
	
}
