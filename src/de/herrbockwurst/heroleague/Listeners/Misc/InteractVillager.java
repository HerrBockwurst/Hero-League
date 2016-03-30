package de.herrbockwurst.heroleague.Listeners.Misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.InternalAPI.Upgrades;
import de.herrbockwurst.heroleague.InternalAPI.VillagerShops;
import net.md_5.bungee.api.ChatColor;

public class InteractVillager implements Listener {
	
	VillagerShops shops = new VillagerShops();
	static HashMap<UUID, List<String>> buyed = new HashMap<>();
	Upgrades Effects = new Upgrades();
	
	
	public static void registerPlayers() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			List<String> a = new ArrayList<String>();
			buyed.put(p.getUniqueId(), a);
		}
	}
	
	public static void addBuyed(Player p, String name) {
		List<String> liste = buyed.get(p.getUniqueId());
		liste.add(name);
		buyed.replace(p.getUniqueId(), liste);
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onInteract (EntityInteractEvent ev) {
		if(ev.getEntity().getType() == EntityType.VILLAGER) {
			ev.setCancelled(true);
			shops.openInventory((Player) ev.getEntity(), 0);
		}
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onInventoryClick (InventoryClickEvent ev) {
		if(!(ev.getClickedInventory().getTitle().equalsIgnoreCase("Dealer"))) return; //Go further only if the Inv is the Dealer!
		ev.setCancelled(true);
		ItemStack item = ev.getCurrentItem();
		
		if(item.getItemMeta().getDisplayName().equalsIgnoreCase("Waffen")) {
			ev.getWhoClicked().closeInventory();
			shops.openInventory((Player) ev.getWhoClicked(), 1);
			return;
		}
		if(item.getItemMeta().getDisplayName().equalsIgnoreCase("Magie")) {
			ev.getWhoClicked().closeInventory();
			shops.openInventory((Player) ev.getWhoClicked(), 2);
			return;
		}
		if(item.getItemMeta().getDisplayName().equalsIgnoreCase("Rüstung")) {
			ev.getWhoClicked().closeInventory();
			shops.openInventory((Player) ev.getWhoClicked(), 3);
			return;
		}
		if(item.getItemMeta().getDisplayName().equalsIgnoreCase("Nützliches")) {
			ev.getWhoClicked().closeInventory();
			shops.openInventory((Player) ev.getWhoClicked(), 4);
			return;
		}
		if(item.getItemMeta().getDisplayName().equalsIgnoreCase("Tränke")) {
			ev.getWhoClicked().closeInventory();
			shops.openInventory((Player) ev.getWhoClicked(), 5);
			return;
		}
		checkWeapons(item, ev);
		checkMagic(item, ev);
		checkArmor(item, ev);
		checkTools(item, ev);
		checkPotions(item, ev);
		
		
	}

	private void checkPotions(ItemStack item, InventoryClickEvent ev) {
		// TODO Auto-generated method stub
		
	}

	private void checkTools(ItemStack item, InventoryClickEvent ev) {
		// TODO Auto-generated method stub
		
	}

	private void checkArmor(ItemStack item, InventoryClickEvent ev) {
		// TODO Auto-generated method stub
		
	}

	private void checkMagic(ItemStack item, InventoryClickEvent ev) {
		// TODO Auto-generated method stub
		
	}

	private void checkWeapons(ItemStack item, InventoryClickEvent ev) {
		if(ev.getInventory().getItem(11).getDurability() == (short) DyeColor.LIME.ordinal()) return; //Check if weapon menu is opened		
		if(checkBuyed((Player) ev.getWhoClicked(), item.getItemMeta().getDisplayName())) return; //Checks if the Upgrade was buyed
		if(checkConditions((Player) ev.getWhoClicked(), item.getItemMeta())) return; //Check if the Upgrade conditons are ok
		
		ev.getWhoClicked().closeInventory();
		for(String lore : item.getItemMeta().getLore()) { //Subtract Money
			if(lore.startsWith("(")) {
				Integer cost = Integer.valueOf(lore.substring(1, lore.length() - 1));
				Main.PlayerMoney.subMoney((Player) ev.getWhoClicked(), cost);
			}
		}
		Effects.apply(item.getItemMeta().getDisplayName(), (Player) ev.getWhoClicked()); 
		
		
		
	}
	
	

	private boolean checkConditions(Player p, ItemMeta item) {
		for(String lore : item.getLore()) { //Check if he has enough Money
			if(lore.startsWith("(")) {
				Integer cost = Integer.valueOf(lore.substring(1, lore.length() - 1));
				if(Main.PlayerMoney.getMoney(p) < cost) {
					p.sendMessage(ChatColor.RED + "Du hast nicht genug Gold!");
					return true;
				}				
			}			
		}
		
		
		
		return false;
	}

	private Boolean checkBuyed(Player p, String displayName) {
		if(buyed.get(p.getUniqueId()).contains(displayName)) {
			p.sendMessage(ChatColor.RED + "Du kannst " + ChatColor.DARK_GRAY + displayName + ChatColor.RED + " nicht mehrfach kaufen!");
			return true;
		}
		return false;
	}
}
