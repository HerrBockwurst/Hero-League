package de.herrbockwurst.heroleague.Heroes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class Allan extends AHero {

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
		return "Allan";
	}

	@Override
	public String getSkinFile() {
		return "http://textures.minecraft.net/texture/44f9ecbe666dd12159910546ed082a7f332a3d775a147326252ec88d4042";
	}

	@Override
	public List<String> getLore() {
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.WHITE + "Typ: " + ChatColor.BLUE + " Krieger");
		lore.add(ChatColor.WHITE + "Schwierigkeit:" + ChatColor.GREEN + " Einfach");
		lore.add(ChatColor.WHITE + "Rolle:" + ChatColor.GRAY + " Off-Tank");
		return lore;
	}
	

}
