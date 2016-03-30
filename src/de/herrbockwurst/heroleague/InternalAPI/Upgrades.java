package de.herrbockwurst.heroleague.InternalAPI;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.herrbockwurst.heroleague.Listeners.Misc.InteractVillager;

public class Upgrades {

	public void apply(String name, Player p) {
		InteractVillager.addBuyed(p, name);
		if(name.equalsIgnoreCase("Waffenschliff I")) {
			ItemStack weapon = p.getInventory().getItem(0);
			weapon.addEnchantment(Enchantment.DAMAGE_ALL, 1);
			ItemMeta meta = weapon.getItemMeta();
			List<String> lore = meta.getLore();
			lore.add(ChatColor.RED + "Waffenschliff I");
			meta.setLore(lore);
			weapon.setItemMeta(meta);
			p.getInventory().setItem(0, weapon);
			
			return;
		}
		if(name.equalsIgnoreCase("Waffenschliff II")) {
			ItemStack weapon = p.getInventory().getItem(0);
			weapon.addEnchantment(Enchantment.DAMAGE_ALL, 2);
			ItemMeta meta = weapon.getItemMeta();
			List<String> lore = meta.getLore();
			for(int i = 0; i < lore.size(); i++) {
				if(lore.get(i).contains("Waffenschliff I"))	lore.set(i, ChatColor.RED + "Waffenschliff II");
			}
			meta.setLore(lore);
			weapon.setItemMeta(meta);
			p.getInventory().setItem(0, weapon);
			
			return;
		}
		
	}
}
