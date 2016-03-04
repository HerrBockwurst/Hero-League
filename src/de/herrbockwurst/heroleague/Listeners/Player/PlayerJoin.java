package de.herrbockwurst.heroleague.Listeners.Player;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.InternalAPI.Methods;
import de.herrbockwurst.heroleague.Shedulars.GameStartCountdown;

public class PlayerJoin implements Listener {
		
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent ev) {
		
		Player p = ev.getPlayer(); 
		
		//check if game is already running
		if(Boolean.valueOf(Main.game.get("isRunning").toString())) {
			//tp to team spawn
		} else {
			//if(GameStartCountdown.start())
			//Spieler Team zuweisen
			if(Main.TeamBlau.size() < Main.TeamRot.size()) {
				Main.TeamBlau.add(p.getUniqueId());
			} else {
				Main.TeamRot.add(p.getUniqueId());
			}
			p.sendMessage(ChatColor.DARK_GREEN + "#####################");
			p.sendMessage(ChatColor.DARK_GREEN + "#   " + ChatColor.WHITE + "Willkommen in" + ChatColor.DARK_GREEN + "   #");
			p.sendMessage(ChatColor.DARK_GREEN + "#    " + ChatColor.YELLOW + Methods.getPluginName(false) + ChatColor.DARK_GREEN + "    #");			
			p.sendMessage(ChatColor.DARK_GREEN + "#     " + ChatColor.WHITE + Main.game.get("GameType") + ChatColor.DARK_GREEN + "      #");
			p.sendMessage(ChatColor.DARK_GREEN + "#####################");
			p.sendMessage("");
			
			if (Main.TeamBlau.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.DARK_GREEN + "Du bist in Team " + ChatColor.BLUE + "Blau" + ChatColor.DARK_GREEN + "!" );
			} else {
				p.sendMessage(ChatColor.DARK_GREEN + "Du bist in Team " + ChatColor.RED + "Rot" + ChatColor.DARK_GREEN + "!" );
			}
				
			
			//Spieler Held zuweisen
			Random rnd = new Random();
			int crnd = rnd.nextInt(Main.HeroList.size() -1); 
			
			p.sendMessage(ChatColor.DARK_GREEN + "Dein Held ist " + Main.HeroList.get(crnd) + "!");
			Main.PlayerHeroes.put(p.getUniqueId(), Main.HeroList.get(crnd));

			//Items Spieler geben
			gibItems(p);

			//tp to lobby
			World world = Bukkit.getWorld((String) Main.game.get("lobbyWorld"));
			double X = Double.valueOf(Main.game.get("lobbyX").toString());
			double Y = Double.valueOf((String) Main.game.get("lobbyY").toString());
			double Z = Double.valueOf((String) Main.game.get("lobbyZ").toString());
			float Pitch = Float.valueOf((String) Main.game.get("lobbyPitch").toString());
			float Yaw = Float.valueOf((String) Main.game.get("lobbyYaw").toString());
			
			Location lobbyloc = new Location(world, X, Y, Z, Yaw, Pitch);
			
			p.teleport(lobbyloc);
			
		}
	}

	private void gibItems(Player p) {
		ItemStack item;
		SkullMeta skullmeta;
		BannerMeta bannermeta;
		
		//Skull
		item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		skullmeta = (SkullMeta) item.getItemMeta();
		skullmeta.setOwner(p.getName());
		skullmeta.setDisplayName("Hero wählen");
		item.setItemMeta(skullmeta);
		
		p.getInventory().setItem(0, item);
		
		//Team
		item = new ItemStack(Material.BANNER, 1);
		bannermeta = (BannerMeta) item.getItemMeta();
		bannermeta.setDisplayName("Team wählen");
		
		if (Main.TeamBlau.contains(p.getUniqueId()))
			bannermeta.setBaseColor(DyeColor.BLUE);
		else
			bannermeta.setBaseColor(DyeColor.RED);
		item.setItemMeta(bannermeta);
		
		p.getInventory().setItem(1, item);
		
		
	}
	
}
