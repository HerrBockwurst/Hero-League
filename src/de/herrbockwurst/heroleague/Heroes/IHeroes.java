package de.herrbockwurst.heroleague.Heroes;

import java.util.List;

public interface IHeroes {
	void ItemBackport ();
	void ItemSkill1();
	void ItemSkill2();
	void ItemSkill3();
	void ItemSkill4();
	void ItemUse();
	List<String> getLore();
	String getName();
	String getSkinFile();
}
