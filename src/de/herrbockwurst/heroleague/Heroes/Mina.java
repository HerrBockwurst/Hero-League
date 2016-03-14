package de.herrbockwurst.heroleague.Heroes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class Mina extends AHero {

	@Override
	public void ItemSkill1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ItemSkill2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ItemSkill3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ItemSkill4() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Mina";
	}
	

	@Override
	public String getSkinFile() {
		return "http://textures.minecraft.net/texture/d8b34798b630afec3398598c617cc10ef90decda33cd07f8bded7a543393";
	}

	@Override
	public List<String> getLore() {
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.WHITE + "Typ: " + ChatColor.BLUE + " Krieger");
		lore.add(ChatColor.WHITE + "Schwierigkeit:" + ChatColor.GREEN + " Einfach");
		lore.add(ChatColor.WHITE + "Rolle:" + ChatColor.YELLOW + " Supporter");
		return lore;
	}



}
