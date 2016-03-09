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
import org.bukkit.scheduler.BukkitTask;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.InternalAPI.Methods;
import de.herrbockwurst.heroleague.Listeners.Player.PlayerJoin;

public class GameStartCountdown {
	
	Main mainplugin = Main.thisclass;
	
	static BukkitTask counter = null;
	static int waiting = 120;
	  
    public GameStartCountdown() {
    	
    	      
        counter = new BukkitRunnable() {
            
            @Override
            public void run() {
                if(waiting > 0) {
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        p.setLevel(waiting);
                        if(waiting == 30) {
                            p.sendMessage("Noch " + waiting + " Sekunden bis zum Start!");
                            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(1, Tone.D));
                        } else if (waiting <= 10) {
                            p.sendMessage("Noch " + waiting + " Sekunden bis zum Start!");
                            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(1, Tone.D));                            
                        }
                    }
                    waiting--;
                } else {
                	for (Player p : Bukkit.getOnlinePlayers()) p.setLevel(0);
                	StartGame();
                }
              
            }
        }.runTaskTimerAsynchronously(mainplugin, 0, 20);
    }
    
    public static void stop() {
    	counter.cancel();
    	counter = null;
    	PlayerJoin.cd = null;
    	waiting = 13;
    }
	
	public static void StartGame() {
		//Main.game.replace("isRunning", true);
    	stop();
		Bukkit.broadcastMessage("Starte Spiel!");

		//Teleporte alle ins Spiel
		World world = Bukkit.getWorld(Main.game.get("Game.Ingame.World").toString());
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
