package de.herrbockwurst.heroleague.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.herrbockwurst.heroleague.Main;
import de.herrbockwurst.heroleague.InternalAPI.Methods;

public class setCoord implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			System.out.println(Methods.getPluginName(true) + " " + " Du kannst das nicht als Konsole tun!" );
			return true;
		}
		
		if(args.length == 1) {
			if(!(args[0].equalsIgnoreCase("lobby")) && !(args[0].equalsIgnoreCase("rot")) && !(args[0].equalsIgnoreCase("blau"))) {
				return false;
			}
			Player p = (Player) sender;
			int X = p.getLocation().getBlockX();
			int Y = p.getLocation().getBlockY();
			int Z = p.getLocation().getBlockZ();
			float pitch = p.getLocation().getPitch();
			float yaw = p.getLocation().getYaw();
			String world = p.getLocation().getWorld().getName();
			
			String welcheCoord = "";
			if(args[0].equalsIgnoreCase("lobby")) {
				welcheCoord = "Game.Lobby";
			} else if(args[0].equalsIgnoreCase("rot")) {
				welcheCoord = "InGame.Rot";
			} else {
				welcheCoord = "InGame.Blau";
			}
				
			Main.config.set(welcheCoord + ".world", world);
			Main.config.set(welcheCoord + ".X", X);
			Main.config.set(welcheCoord + ".Y", Y);
			Main.config.set(welcheCoord + ".Z", Z);
			Main.config.set(welcheCoord + ".Pitch", pitch);
			Main.config.set(welcheCoord + ".Yaw", yaw);
			Main.config.saveConfig();
			
			p.sendMessage(Methods.getPluginName(true) + " Koordinaten gesetzt!");
			
			Main.game.put("lobbyX", Main.config.get("Game.Lobby.X"));
			Main.game.put("lobbyY", Main.config.get("Game.Lobby.Y"));
			Main.game.put("lobbyZ", Main.config.get("Game.Lobby.Z"));
			Main.game.put("lobbyPitch", Main.config.get("Game.Lobby.Pitch"));
			Main.game.put("lobbyYaw", Main.config.get("Game.Lobby.Yaw"));
			Main.game.put("lobbyWorld", Main.config.get("Game.Lobby.World"));

			Main.game.put("IngameWorld", Main.config.get("Game.Ingame.World"));
			
			Main.game.put("RotX", Main.config.get("Game.Ingame.Rot.X"));
			Main.game.put("RotY", Main.config.get("Game.Ingame.Rot.Y"));
			Main.game.put("RotZ", Main.config.get("Game.Ingame.Rot.Z"));
			Main.game.put("RotPitch", Main.config.get("Game.Ingame.Rot.Pitch"));
			Main.game.put("RotYaw", Main.config.get("Game.Ingame.Rot.Yaw"));

			Main.game.put("BlauX", Main.config.get("Game.Ingame.Blau.X"));
			Main.game.put("BlauY", Main.config.get("Game.Ingame.Blau.Y"));
			Main.game.put("BlauZ", Main.config.get("Game.Ingame.Blau.Z"));
			Main.game.put("BlauPitch", Main.config.get("Game.Ingame.Blau.Pitch"));
			Main.game.put("BlauYaw", Main.config.get("Game.Ingame.Blau.Yaw"));
			
			return true;
			
			
		}
		
		
		
		
		
		return false;
	}

}
