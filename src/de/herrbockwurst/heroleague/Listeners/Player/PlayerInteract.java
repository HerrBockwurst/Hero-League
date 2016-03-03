package de.herrbockwurst.heroleague.Listeners.Player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.InternalAPI.Methods;

public class PlayerInteract implements Listener {
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void PlayerInteractor (PlayerInteractEvent ev) {
		Player p = ev.getPlayer();
		PlayerInventory inv = p.getInventory();
		ItemStack HandItem = inv.getItemInMainHand();
		
		if(!((boolean) Main.game.get("isRunning"))) {
			if(HandItem.getType() == Material.SKULL_ITEM) HeroSelector(p);
			else if(HandItem.getType() == Material.BANNER) TeamSelector(p);
			
			ev.setCancelled(true);
		}		
	}

	private void TeamSelector(Player p) {
		Inventory TeamSelect = Bukkit.createInventory(null, 9, "Wähle dein Team!");
		String TeamRotLore = "[" + Main.TeamRot.size() + "/5]";
		String TeamBlauLore = "[" + Main.TeamBlau.size() + "/5]";
		Methods.InvAddItem(Material.BANNER, TeamSelect, 3, "Team Rot", TeamRotLore);
		Methods.InvAddItem(Material.BANNER, TeamSelect, 5, "Team Blau", TeamBlauLore);
		p.openInventory(TeamSelect);
	}

	private void HeroSelector(Player p) {
		// TODO Auto-generated method stub
		
	}

}
