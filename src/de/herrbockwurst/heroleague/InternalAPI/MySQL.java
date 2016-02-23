package de.herrbockwurst.heroleague.InternalAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.herrbockwurst.heroleague.Main;

public class MySQL {
	
	private static Main main = Main.thisclass;
	
	public static String host = main.config.getString("MySQL.host");
	public static String port = main.config.getString("MySQL.port");
	public static String db = main.config.getString("MySQL.database");
	public static String user = main.config.getString("MySQL.user");
	public static String pass = main.config.getString("MySQL.pass");
	public static Connection con;
	
	public static void connect() {
		if(!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, pass);
				System.out.println(Methods.getPluginName(true) + " " + main.lang.getString("MySQL.conOK"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public static void disconnect() {
		if(isConnected()) {
			try {
				con.close();
				System.out.println(Methods.getPluginName(true) + " " + main.lang.getString("MySQL.disconnected"));
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
