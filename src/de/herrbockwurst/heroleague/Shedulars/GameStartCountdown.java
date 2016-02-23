package de.herrbockwurst.heroleague.Shedulars;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import de.herrbockwurst.heroleague.Main;

public class GameStartCountdown extends Main {

	
	
	public void start () {
		int waiting = 121;
		
		BukkitTask starttimer = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
			public void run() {
				if(waiting == 0) {
					starttimer.cancel();
				}
			}
		} ,0, 20*1);
	}
	
}
