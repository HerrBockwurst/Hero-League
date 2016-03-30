package de.herrbockwurst.heroleague.InternalAPI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class VillagerShops {
	
	/*
	 * Invetorys
	 * 	Main
	 * 	Waffen
	 * 	Rüstung
	 * 	Tools
	 * 	Nahrung
	 * 	Tränke
	 * 
	 */
	
	List<Inventory> shops = new ArrayList<Inventory>();
	
	public VillagerShops() {		
		shops.add(createMain(null));
		shops.add(createWeapons());
		shops.add(createMagic());
		shops.add(createArmor());
		shops.add(createTools());
		shops.add(createPotions());
		
	}
	
	public void openInventory(Player p, Integer invID) {
		p.openInventory(shops.get(invID));
	}


	private Inventory createMagic() {
		Inventory inv = createMain(12);
		inv.setItem(18, getItem(Material.BLAZE_ROD,
								"Feuermantel",
								ChatColor.YELLOW + "(1000);" + ChatColor.WHITE + "Wenn du Schaden erhälst, erhält dein Gegner ebenfalls 2 Schaden!;" + ChatColor.WHITE + "Cooldown: 10 Sekunden!", 1));		
		return inv;
		
	}


	private Inventory createTools() {
		Inventory inv = createMain(14);
		inv.setItem(18, getItem(Material.WOOD_SWORD,
								"Waffenschliff I",
								ChatColor.YELLOW + "(100);" + ChatColor.WHITE + "Erhöht Schaden um 1!", 1));		
		return inv;
		
	}


	private Inventory createPotions() {
		Inventory inv = createMain(15);
		inv.setItem(18, getItem(Material.WOOD_SWORD,
								"Waffenschliff I",
								ChatColor.YELLOW + "(100);" + ChatColor.WHITE + "Erhöht Schaden um 1!", 1));		
		return inv;
		
	}

	private Inventory createArmor() {
		Inventory inv = createMain(13);
		inv.setItem(18, getItem(Material.WOOD_SWORD,
								"Waffenschliff I",
								ChatColor.YELLOW + "(100);" + ChatColor.WHITE + "Erhöht Schaden um 1!", 1));		
		return inv;
		
	}


	private Inventory createWeapons() {
		Inventory inv = createMain(11);
		inv.setItem(18, getItem(Material.WOOD_SWORD,
								"Waffenschliff I",
								ChatColor.YELLOW + "(100);" + ChatColor.WHITE + "Erhöht Schaden um 1.25!", 1));
		inv.setItem(19, getItem(Material.STONE_SWORD,
								"Waffenschliff II",
								ChatColor.YELLOW + "(400);" + ChatColor.WHITE + "Erhöht Schaden um 2.5!", 2));
		return inv;
	}


	private Inventory createMain(Integer selection) {
		Inventory inv = Bukkit.createInventory(null, 36, "Dealer");
		// 0 1 2 3 4 5 6 7 8
		// 0 0 w m a t p 0 0
		// 9 10 11 12 13 14 15 16 17
		// 0 0  w  m  a  t  p  0  0
		inv.setItem(2, getItem(	Material.IRON_SWORD,
								"Waffen",
								ChatColor.WHITE + "Waffen und Offensives!", 1));
		inv.setItem(3, getItem(	Material.ENCHANTMENT_TABLE,
								"Magie",
								ChatColor.WHITE + "Auren für Jedermann!", 1));
		inv.setItem(4, getItem(	Material.IRON_CHESTPLATE,
								"Rüstung",
								ChatColor.WHITE + "Für ein längers Leben!", 1));		
		inv.setItem(5, getItem(	Material.IRON_PICKAXE,
								"Nützliches",
								ChatColor.WHITE + "Nützliches Items!", 1));	
		inv.setItem(6, getItem(	Material.POTION,
								"Tränke",
								ChatColor.WHITE + "Temporäre Boost's!;" + ChatColor.WHITE + "Schnell und günstig!", 1));
		for(int i = 9; i <= 17; i++) {
			if(selection == i) {
				ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE);
				pane.setDurability((short) DyeColor.LIME.ordinal());				
				inv.setItem(i, pane);
			} else {
				ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE);
				pane.setDurability((short) DyeColor.BLACK.ordinal());				
				inv.setItem(i, pane);
			}
			
		}
		return inv;
	}
	
	private ItemStack getItem(Material itemname, String name, String lorestring, Integer amount) {
		List<String> lore = new ArrayList<String>();
		ItemStack item = new ItemStack(itemname, 2);
		if(lorestring.contains(";")) {
			String[] loretemp = lorestring.split(";");
			for(int i = 0; i < loretemp.length; i++) {
				lore.add(loretemp[i]);
			}
		} else if(lorestring != null) {
			lore.add(lorestring);
		}
		
		ItemMeta meta = item.getItemMeta();
		if(name != null) meta.setDisplayName(name);
		if(lorestring != null) meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	
}
