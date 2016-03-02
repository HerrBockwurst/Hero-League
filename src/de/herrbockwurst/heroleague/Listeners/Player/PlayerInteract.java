package de.herrbockwurst.heroleague.Listeners.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerInteract implements Listener {
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void PlayerInteractor (PlayerInteractEvent ev) {
		Player p = ev.getPlayer();
		PlayerInventory inv = p.getInventory();
		ItemStack HandItem = inv.getItemInMainHand();
		
		
		return;
		
	}

}
