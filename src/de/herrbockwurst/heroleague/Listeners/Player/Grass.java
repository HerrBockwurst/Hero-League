package de.herrbockwurst.heroleague.Listeners.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Grass implements Listener {
	
	public List<UUID> inGrass = new ArrayList<>();
	
	@EventHandler(priority=EventPriority.HIGH)
	public void IsInGrass(PlayerMoveEvent ev) {
		Player p = ev.getPlayer();
		Location loc = ev.getTo();
		if(loc.getBlock().getType() == Material.LONG_GRASS) {
			for(Player oplayer : Bukkit.getOnlinePlayers()) {
				if(inGrass.contains(oplayer.getUniqueId())) {
					if(oplayer.getLocation().distance(loc) <= 10) {
						return;
					}
				}
				oplayer.hidePlayer(p);
				inGrass.add(p.getUniqueId());
			}
		}
	}
}
