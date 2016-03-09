package de.herrbockwurst.heroleague.Heroes;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import de.herrbockwurst.heroleague.Main;

public class HeroHandler {
	
	private ArrayList<IHeroes> HeroHandler = new ArrayList<IHeroes>();
	
	public HeroHandler () {
		HeroHandler.add(new Allan());
		HeroHandler.add(new Mina());
	}

	public void doSkill1(Player p) {
		for(IHeroes hero : HeroHandler) {
			hero.ItemSkill1();
		}
	}
	
	public String getSkinID(Player p) {
		for(IHeroes hero : HeroHandler) {
			if(Main.PlayerHeroes.get(p.getUniqueId()).equalsIgnoreCase(hero.getName()))
				return hero.getSkinID();
		}
		return null;
	}
	
	public String getSkinID(String s) {
		for(IHeroes hero : HeroHandler) {
			if(hero.getName().equalsIgnoreCase(s)) 
				return hero.getSkinID();			
		}
		return null;
	}
}
