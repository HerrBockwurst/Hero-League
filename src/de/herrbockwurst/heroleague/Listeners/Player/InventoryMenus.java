package de.herrbockwurst.heroleague.Listeners.Player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.SkullMeta;
import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.Heroes.HeroHandler;

public class InventoryMenus implements Listener {
	
	Main plugin = Main.thisclass;
	
	@EventHandler(priority=EventPriority.HIGH)
	public void clickEvent (InventoryClickEvent ev) {
		if(ev.getClickedInventory().getName().equalsIgnoreCase("Wähle deinen Held")) HeroSelectEvent(ev);
		else if(ev.getClickedInventory().getName().equalsIgnoreCase("Wähle dein Team!")) TeamSelectEvent(ev);
	}

	private void TeamSelectEvent(InventoryClickEvent ev) {
		Player p = (Player) ev.getWhoClicked();
		ItemStack item = ev.getCurrentItem();
		BannerMeta meta = (BannerMeta) item.getItemMeta();
		ev.setCancelled(true);
		p.closeInventory();
		
		if(meta.getDisplayName().equalsIgnoreCase("Team Rot")) {
			if(Main.TeamRot.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Du bist bereits in diesen Team!");
				return;
			}
			if(Main.TeamRot.size() == 5) {
				p.sendMessage(ChatColor.RED + "Das Team ist voll!");
				return;
			}
			Main.TeamRot.add(p.getUniqueId());
			Main.TeamBlau.remove(p.getUniqueId());
			
			p.sendMessage(ChatColor.GREEN + "Du bist nun in Team " + ChatColor.RED + "Rot!");
		}
		
		if(meta.getDisplayName().equalsIgnoreCase("Team Blau")) {
			if(Main.TeamBlau.contains(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "Du bist bereits in diesen Team!");
				return;
			}
			if(Main.TeamBlau.size() == 5) {
				p.sendMessage(ChatColor.RED + "Das Team ist voll!");
				return;
			}
			Main.TeamBlau.add(p.getUniqueId());
			Main.TeamRot.remove(p.getUniqueId());
			p.sendMessage(ChatColor.GREEN + "Du bist nun in Team " + ChatColor.BLUE + "Blau!");
		}
		updateBanner(p);
	}
	
	private void updateBanner(Player p) {
		ItemStack item = new ItemStack(Material.BANNER, 1);
		BannerMeta bannermeta = (BannerMeta) item.getItemMeta();
		bannermeta.setDisplayName("Team wählen");
		
		if (Main.TeamBlau.contains(p.getUniqueId()))
			bannermeta.setBaseColor(DyeColor.BLUE);
		else
			bannermeta.setBaseColor(DyeColor.RED);
		item.setItemMeta(bannermeta);
		
		p.getInventory().setItem(1, item);
	}

	private void HeroSelectEvent(InventoryClickEvent ev) {
		Player p = (Player) ev.getWhoClicked();
		ItemStack item = ev.getCurrentItem();
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		ev.setCancelled(true);
		p.closeInventory();
		
		if(Main.PlayerHeroes.get(p.getUniqueId()).equalsIgnoreCase(meta.getDisplayName())) {
			p.sendMessage(ChatColor.RED + "Du spielst bereits als " + meta.getDisplayName() + "!");
			return;
		}
		if(Main.PlayerHeroes.containsValue(meta.getDisplayName())) {
			for(Player cplayer : Bukkit.getOnlinePlayers()) {
				if(Main.PlayerHeroes.get(cplayer.getUniqueId()).equalsIgnoreCase(meta.getDisplayName())) {
					if(Main.game.get("GameType").toString().equalsIgnoreCase("Unranked")) {
						if(Main.TeamRot.contains(p.getUniqueId()) && Main.TeamRot.contains(cplayer.getUniqueId())) {
							p.sendMessage(ChatColor.RED + meta.getDisplayName() + " wird bereits von " + cplayer.getDisplayName() + " gespielt!");
							return;
						} else if(Main.TeamBlau.contains(p.getUniqueId()) && Main.TeamBlau.contains(cplayer.getUniqueId())) {
							p.sendMessage(ChatColor.RED + meta.getDisplayName() + " wird bereits von " + cplayer.getDisplayName() + " gespielt!");
							return;
						}
					} else {
						p.sendMessage(ChatColor.RED + meta.getDisplayName() + " wird bereits von " + cplayer.getDisplayName() + " gespielt!");
						return;	
					}
									
				}
			}
			
		}
		//TODO Check ob Held freigeschalten wurde!
		
		Main.PlayerHeroes.replace(p.getUniqueId(), meta.getDisplayName());
		p.sendMessage(ChatColor.GREEN + "Du spielst nun als " + meta.getDisplayName() + "!");
		
		//apply skin
		HeroHandler hh = new HeroHandler();
		
		//check wich hero was chosen
		int x = -1;
		for(int i = 0; i < hh.getSize(); i++) {
			if(meta.getDisplayName().equalsIgnoreCase(hh.getHeroName(i))) x = i;
		}
		if (x == -1) {
			p.sendMessage(ChatColor.RED + "Es ist ein Fehler aufgetreten!");
			return;
		}
		
		
		//TODO Skin ÄNDERN
	}
	
}
