package de.herrbockwurst.heroleague.Listeners.Player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Grass implements Listener {
	
	@EventHandler(priority=EventPriority.HIGH)
	public void GrassMovement(PlayerMoveEvent ev) {
		Player p = ev.getPlayer();
		Material matTo = ev.getTo().getBlock().getType();
		Material matFrom =ev.getFrom().getBlock().getType();
		
		//check ob pos anders
		double fromX = ev.getFrom().getX();
		double fromY = ev.getFrom().getY();
		double fromZ = ev.getFrom().getZ();
		double toX = ev.getTo().getX();
		double toY = ev.getTo().getY();
		double toZ = ev.getTo().getZ();
		if(fromX == toX && fromY == toY && fromZ == toZ) return;
		
		//geht ins grass
		if(matFrom != Material.DOUBLE_PLANT && matTo == Material.DOUBLE_PLANT) {
			for(Player oplayer : Bukkit.getOnlinePlayers()) {
				if(!(dontHideCheck(p, oplayer))) {
					oplayer.hidePlayer(p);
				}
			}
		}
		
		//geht aus Grass
		if(matFrom == Material.DOUBLE_PLANT && matTo != Material.DOUBLE_PLANT) {
			for(Player oplayer : Bukkit.getOnlinePlayers()) {
				//TODO hier check ob Spieler durch skill unsichtbar 
				oplayer.showPlayer(p);
				
				//Andere spieler im grass verbergen
				if(oplayer.getLocation().getBlock().getType() == Material.DOUBLE_PLANT) p.hidePlayer(oplayer);			
				
			}
		}
		
		//bewegt sich im Grass
		if(matFrom == Material.DOUBLE_PLANT && matTo == Material.DOUBLE_PLANT) {
			for (Player oplayer : Bukkit.getOnlinePlayers()) {
				if(!(dontHideCheck(p, oplayer))) {
					oplayer.hidePlayer(p);
					if(oplayer.getLocation().getBlock().getType() == Material.DOUBLE_PLANT) p.hidePlayer(oplayer);
				} else {
					oplayer.showPlayer(p);
					if(oplayer.getLocation().getBlock().getType() == Material.DOUBLE_PLANT) p.showPlayer(oplayer);
				}
			}
		}
	}
	
	private boolean dontHideCheck(Player p, Player o) {
		Location ploc = p.getLocation();
		Location oloc = o.getLocation();
		if(p.getEntityId() == o.getEntityId()) return true;
		if(ploc.getBlock().getType() == Material.DOUBLE_PLANT && oloc.getBlock().getType() == Material.DOUBLE_PLANT) {
			//Beide sind im Grass
			if(ploc.distance(oloc) <= 10) {
				//Beide sind näher als 10 Blöcke, nicht hide
				return true;
			}
		}
		return false;
	}
	
}
