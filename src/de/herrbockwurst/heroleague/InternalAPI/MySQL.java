package de.herrbockwurst.heroleague.InternalAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.herrbockwurst.heroleague.Main;

public class MySQL {
	
	public static String host = Main.config.getString("MySQL.host");
	public static String port = Main.config.getString("MySQL.port");
	public static String db = Main.config.getString("MySQL.database");
	public static String user = Main.config.getString("MySQL.user");
	public static String pass = Main.config.getString("MySQL.pass");
	public static Connection con;
	
	public static void connect() {
		if(!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, pass);
				System.out.println(Methods.getPluginName(true) + " " + "MySQL Verbindung erfolgreich aufgebaut!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public static void disconnect() {
		if(isConnected()) {
			try {
				con.close();
				System.out.println(Methods.getPluginName(true) + " " + "MySQL Verbindung getrennt!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static boolean isConnected() {
		return (con == null ? false : true);
	}
	public static void query(String query) {
		try {
			Statement st = con.createStatement();
			st.executeQuery(query);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static ResultSet queryResult(String query) {
		ResultSet rs = null;
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
