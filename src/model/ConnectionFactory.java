package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection(){
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			 return DriverManager.getConnection(/*Put connection with DB*/);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
