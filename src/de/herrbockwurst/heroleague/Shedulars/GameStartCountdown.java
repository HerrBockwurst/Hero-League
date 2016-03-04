package de.herrbockwurst.heroleague.Shedulars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.InternalAPI.Methods;

public class GameStartCountdown extends Main {
	/*	
	private BukkitTask shedular;
	
	private void shedular(BukkitTask shedular) {
		shedular = Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
			int waiting = 120; //Startzeit in Sekunden
			
			@Override
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
					//Stop Shedular
				}
				
			}
			
		}, 0, 20);
	}
	

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
		*/
	
	public static void StartGame() {
		Main.game.replace("isRunning", true);

		//Teleporte alle ins Spiel
		World world = Bukkit.getWorld((String) Main.game.get("Game.Ingame.World"));
		double RotX = Double.valueOf(Main.game.get("Game.Ingame.Rot.X").toString()); 
		double RotY = Double.valueOf(Main.game.get("Game.Ingame.Rot.Y").toString());
		double RotZ = Double.valueOf(Main.game.get("Game.Ingame.Rot.Z").toString());
		float RotPitch = Float.valueOf(Main.game.get("Game.Ingame.Rot.Pitch").toString());
		float RotYaw = Float.valueOf(Main.game.get("Game.Ingame.Rot.Yaw").toString());

		double BlauX = Double.valueOf(Main.game.get("Game.Ingame.Blau.X").toString()); 
		double BlauY = Double.valueOf(Main.game.get("Game.Ingame.Blau.Y").toString());
		double BlauZ = Double.valueOf(Main.game.get("Game.Ingame.Blau.Z").toString());
		float BlauPitch = Float.valueOf(Main.game.get("Game.Ingame.Blau.Pitch").toString());
		float BlauYaw = Float.valueOf(Main.game.get("Game.Ingame.Blau.Yaw").toString());
		
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
