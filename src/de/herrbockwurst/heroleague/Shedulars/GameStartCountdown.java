package de.herrbockwurst.heroleague.Shedulars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.herrbockwurst.heroleague.Main;

public class GameStartCountdown extends Main {

	
	
	public void start () {

		new BukkitRunnable() {
			int waiting = 121;

			public void run() {
				if(waiting > 0) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.setLevel(waiting);
						if(waiting == 30) {
							p.sendMessage(Main.thisclass.lang.get("Count"));
						}
					}
					waiting--;
				} else {
					this.cancel();
				}
			}
			
		}.runTaskTimerAsynchronously(this, 0, 20);
	}
	
}
