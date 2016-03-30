package de.herrbockwurst.heroleague.InternalAPI;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GoldSystem {
	HashMap<UUID, Integer> money = new HashMap<>();
	
	public void regPlayers() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			money.put(p.getUniqueId(), 300);
		}
	}
	
	public void addMoney(Player p, Integer amount) {
		money.replace(p.getUniqueId(), getMoney(p) + amount);
	}
	
	public void subMoney(Player p, Integer amount) {
		money.replace(p.getUniqueId(), getMoney(p) - amount);
	}
	
	public Integer getMoney(Player p) {
		return money.get(p.getUniqueId());
	}
}
