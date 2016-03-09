package de.herrbockwurst.heroleague.Listeners.Player;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
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
import de.herrbockwurst.heroleague.Heroes.EHeroes;
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
		String[] TeamRotLore = {"[" + Main.TeamRot.size() + "/5]"};
		String[] TeamBlauLore = {"[" + Main.TeamBlau.size() + "/5]"};
		Methods.InvAddBanner(TeamSelect, 3, "Team Rot", TeamRotLore, DyeColor.RED);
		Methods.InvAddBanner(TeamSelect, 5, "Team Blau", TeamBlauLore, DyeColor.BLUE);
		p.openInventory(TeamSelect);
	}

	private void HeroSelector(Player p) {
		Inventory HeroSelect = Bukkit.createInventory(null, 54, "Wähle deinen Held");
		int i = 0;
		String[] lore = {""};
		for(EHeroes h : EHeroes.values()) {
			
			Methods.InvAddSkull(HeroSelect, i, h.toString(), lore, h.toString());
			i++;
		}
		p.openInventory(HeroSelect);
		
		
		
	}

}
