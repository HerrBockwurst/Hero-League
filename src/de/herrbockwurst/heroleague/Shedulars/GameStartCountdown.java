package de.herrbockwurst.heroleague.Shedulars;

import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.herrbockwurst.heroleague.Main;

public class GameStartCountdown extends Main {
	
	int waiting = 120; //Startzeit in Sekunden
	
	public void start () {

		
		new BukkitRunnable() {

			public void run() {
				if(waiting > 0) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.setLevel(waiting);
						if(waiting == 30) {
							p.sendMessage("Noch " + waiting + " Sekunden bis zum Start!");
							p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(3, Tone.D));
						} else if (waiting <= 10) {
							p.sendMessage("Noch " + waiting + " Sekunden bis zum Start!");
							p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(3, Tone.D));
							StartGame();
						}
					}
					waiting--;
				} else {
					this.cancel();
				}
			}
			
		}.runTaskTimerAsynchronously(this, 0, 20);
	}
	
	public void StartGame() {
		Main.thisclass.game.replace("isRunning", true);
		//Teleporte alle ins Spiel
		for (Player p : Bukkit.getOnlinePlayers()) {
			
		}
		//Starte Spiel Shedular
	}
	
}
