package de.herrbockwurst.heroleague.Listeners.Misc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.herrbockwurst.heroleague.InternalAPI.Methods;

public class BlockBreak implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onBlockBreak (BlockBreakEvent ev ) {
		ev.setCancelled(true);
		if(ev.getPlayer() instanceof Player) {
			ev.getPlayer().sendMessage(Methods.createMessage("cannotDoThat", ChatColor.RED, true));
		}
	}

}
