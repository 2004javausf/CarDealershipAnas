package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnFactory {

	// Singleton factory
	
		// private static instance of itself
		private static ConnFactory cf = new ConnFactory();
		
		// private no argument constructor
		private ConnFactory() {
			super();
		}
		
		// public static synchronized "getter method"
		public static synchronized ConnFactory getInstance() {
			
			if(cf == null) {
				cf = new ConnFactory();
			}
			
			return cf;
		}
		
		// Methods that do stuff
		// Get a connection to the db (database)
		public Connection getConnection() {
			
			String url = "jdbc:oracle:thin:@java2004usf.cx9wqqh6avpe.us-east-2.rds.amazonaws.com:1521:ORCL";
			String user = "anasdaud";
			String password = "p4ssw0rd";
			
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return conn;
		}
}
