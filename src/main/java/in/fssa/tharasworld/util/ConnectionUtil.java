package in.fssa.tharasworld.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {

public static Connection getConnection() {
		
	 String url;
     String username;
     String password;

     if (System.getenv("CI") != null) {
         url = System.getenv("DATABASE_HOSTNAME");
         username = System.getenv("DATABASE_USERNAME");
         password = System.getenv("DATABASE_PASSWORD");
     } else {
         Dotenv env = Dotenv.load();
         url = env.get("DATABASE_HOSTNAME");
         username = env.get("DATABASE_USERNAME");
         password = env.get("DATABASE_PASSWORD");
     }
		 
		Connection connection = null;

		
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password); 
			
			
			 
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return connection;
	}
	
	public static void close(Connection connection, PreparedStatement ps) {
		try {
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
