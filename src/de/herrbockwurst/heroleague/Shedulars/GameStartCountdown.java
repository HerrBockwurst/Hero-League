package de.herrbockwurst.heroleague.Shedulars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.InternalAPI.Methods;

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
		Main.game.replace("isRunning", true);

		//Teleporte alle ins Spiel
		World world = Bukkit.getWorld((String) Main.game.get("Game.Ingame.world"));
		double RotX = (double) Main.game.get("Game.Ingame.Rot.X"); 
		double RotY = (double) Main.game.get("Game.Ingame.Rot.Y");
		double RotZ = (double) Main.game.get("Game.Ingame.Rot.Z");
		float RotPitch = (float) Main.game.get("Game.Ingame.Rot.Pitch");
		float RotYaw = (float) Main.game.get("Game.Ingame.Rot.Yaw");

		double BlauX = (double) Main.game.get("Game.Ingame.Blau.X"); 
		double BlauY = (double) Main.game.get("Game.Ingame.Blau.Y");
		double BlauZ = (double) Main.game.get("Game.Ingame.Blau.Z");
		float BlauPitch = (float) Main.game.get("Game.Ingame.Blau.Pitch");
		float BlauYaw = (float) Main.game.get("Game.Ingame.Blau.Yaw");
		
		Location TeamRot = new Location(world, RotX, RotY, RotZ, RotYaw, RotPitch);
		Location TeamBlau = new Location(world, BlauX, BlauY, BlauZ, BlauYaw, BlauPitch);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(Main.TeamBlau.contains(p.getUniqueId().toString())) {
				p.teleport(TeamRot);
			} else {
				p.teleport(TeamBlau);
			}
			p.sendMessage(Methods.getPluginName(true) + ChatColor.GREEN + " Das Spiel beginnt!");
		}
		//Starte Spiel Shedular
	}
	
}
