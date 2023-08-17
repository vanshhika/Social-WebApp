package com.Db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	private static Connection conn;
	public static Connection getConn() {
		try {
			if(conn == null) {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection("jdbc:sqlserver://VANSHIKA2408;databaseName=SocialDB;integratedSecurity=true;encrypt=false;");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
}
