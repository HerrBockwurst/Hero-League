package de.herrbockwurst.heroleague.Heroes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

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
	
	public String getSkinID(Integer i) {
		if(HeroHandler.get(i).getSkinFile() != null) {
			return HeroHandler.get(i).getSkinFile();
		}
		return null;
	}
	
	public String getHeroName(Integer i) {
		if(HeroHandler.get(i).getSkinFile() != null) {
			return HeroHandler.get(i).getName();
		}
		return null;
	}
	
	public List<String> getLore(Integer i) {
		if(HeroHandler.get(i).getLore() != null) {
			return HeroHandler.get(i).getLore();
		}
		return null;
	}
	
	public Integer getSize() {
		return HeroHandler.size();
	}
}
