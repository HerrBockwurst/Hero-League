package de.herrbockwurst.heroleague.InternalAPI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.Heroes.HeroHandler;

public class Methods {
	
	public static String getPluginName(Boolean form) {
		if(form == false) {
			return Main.thisclass.getDescription().getName();
		} else {
			return ChatColor.DARK_GREEN + "[" + Main.thisclass.getDescription().getName() + "]" + ChatColor.WHITE;
		}		
	}
	
	public static void InvAddItem(Material material, Inventory inv, int Slot, String name, String[] lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> Lore = new ArrayList<String>();
		for(String clore : lore) {
			Lore.add(clore);
		}
		meta.setLore(Lore);
		item.setItemMeta(meta);
		 
		inv.setItem(Slot, item); 
		 
	}

	public static void InvAddBanner(Inventory inv, int Slot, String name, String[] lore, DyeColor color) {
		ItemStack item = new ItemStack(Material.BANNER);
		BannerMeta meta = (BannerMeta) item.getItemMeta();
		meta.setDisplayName(name);
		meta.setBaseColor(color);
		List<String> Lore = new ArrayList<String>();
		for(String clore : lore) {
			Lore.add(clore);
		}
		meta.setLore(Lore);
		item.setItemMeta(meta);
		 
		inv.setItem(Slot, item); 
		 
	}
	
	public static void InvAddSkull(Inventory inv, int Slot, String name, String[] lore, String SkullOwner) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		HeroHandler h = new HeroHandler();
		meta.setDisplayName(name);
		List<String> Lore = new ArrayList<String>();
		for(String clore : lore) {
			Lore.add(clore);
		}
		meta.setLore(Lore);
		
		meta.setOwner(h.getSkinID(SkullOwner));
		System.out.println(h.getSkinID(SkullOwner));
		item.setItemMeta(meta);
		
		 
		inv.setItem(Slot, item); 
		 
	}

}
