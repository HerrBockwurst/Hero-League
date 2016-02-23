package de.herrbockwurst.heroleague.InternalAPI;


public class MySQLDefaults {
	public static void create() {
		//Table creation
		MySQL.query("CREATE TABLE IF NOT EXISTS `globalconfig` (`conf` VARCHAR(100), value TEXT)");
		MySQL.query("CREATE TABLE IF NOT EXISTS `stats_mineleague` (`UUID` VARCHAR(100), `name` VARCHAR(100))");
		
		//Inhalt einfügen
		//MySQL.query("INSERT INTO `globalconfig` (`conf`, `value`) VALUES ('servers', 'a;b')");
	}
}
