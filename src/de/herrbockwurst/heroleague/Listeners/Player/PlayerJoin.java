package de.herrbockwurst.heroleague.Listeners.Player;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.InternalAPI.Methods;

public class PlayerJoin implements Listener {
		
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent ev) {
		
		Player p = ev.getPlayer(); 
		
		//check if game is already running
		if((Boolean) Main.game.get("isRunning")) {
			//tp to team spawn
		} else {
			
			//Spieler Team zuweisen
			if(Main.TeamBlau.size() < Main.TeamRot.size()) {
				Main.TeamBlau.add(p.getUniqueId().toString());
			} else {
				Main.TeamRot.add(p.getUniqueId().toString());
			}
			p.sendMessage(ChatColor.DARK_GREEN + "#####################");
			p.sendMessage(ChatColor.DARK_GREEN + "#   " + ChatColor.WHITE + "Willkommen in" + ChatColor.DARK_GREEN + "   #");
			p.sendMessage(ChatColor.DARK_GREEN + "#    " + ChatColor.YELLOW + Methods.getPluginName(false) + ChatColor.DARK_GREEN + "    #");			
			p.sendMessage(ChatColor.DARK_GREEN + "#     " + ChatColor.WHITE + Main.game.get("GameType") + ChatColor.DARK_GREEN + "      #");
			p.sendMessage(ChatColor.DARK_GREEN + "#####################");
			p.sendMessage("");
			
			if (Main.TeamBlau.contains(p.getUniqueId().toString())) {
				p.sendMessage(ChatColor.DARK_GREEN + "Du bist in Team " + ChatColor.BLUE + "Blau" + ChatColor.DARK_GREEN + "!" );
			} else {
				p.sendMessage(ChatColor.DARK_GREEN + "Du bist in Team " + ChatColor.RED + "Rot" + ChatColor.DARK_GREEN + "!" );
			}
				
			
			//Spieler Held zuweisen
			Random rnd = new Random();
			int crnd = rnd.nextInt(Main.Heroes.size() -1);
			
			p.sendMessage(ChatColor.DARK_GREEN + "Dein Held ist " + Main.Heroes.get(crnd) + "!");
			
			//Items Spieler geben
			
			//tp to lobby
			
		}
	}
	
}
