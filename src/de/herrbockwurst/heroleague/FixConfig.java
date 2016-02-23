package de.herrbockwurst.heroleague;

import de.herrbockwurst.heroleague.InternalAPI.SimpleConfig;

public class FixConfig {
	public static void fix(Main main) {
		fixMain(main);
		fixLang(main.lang);
	}

	private static void fixLang(SimpleConfig lang) {
		if(lang.contains("Error.cannotDoThat")) {
			lang.set("Error.cannotDoThat", "Du kannst das nicht tun!");
		}
		
	}

	private static void fixMain(Main main) {
		
		
	}
	
}
