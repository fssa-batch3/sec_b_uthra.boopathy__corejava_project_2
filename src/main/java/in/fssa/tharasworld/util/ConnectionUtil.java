package in.fssa.tharasworld.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {

	/**
	 * Establishes a connection to a MySQL database using the provided environment
	 * variables.
	 *
	 * @return A JDBC Connection object representing the database connection.
	 * @throws RuntimeException       If any errors occur during the database
	 *                                connection process.
	 *
	 * @throws ClassNotFoundException If the MySQL JDBC driver class cannot be
	 *                                found.
	 */

	public static Connection getConnection() {

		String url;
		String username;
		String password;

//		url = System.getenv("DATABASE_HOSTNAME");
//		username = System.getenv("DATABASE_USERNAME");
//		password = System.getenv("DATABASE_PASSWORD");
		
		url = "jdbc:mysql://localhost:3306/project";
		username = "root";
		password = "Uthra#1210";

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return connection;
	}

	/**
	 * Closes a JDBC Connection and PreparedStatement, releasing associated
	 * resources.
	 *
	 * @param connection The JDBC Connection to be closed.
	 * @param ps         The PreparedStatement to be closed.
	 */

	public static void close(Connection connection, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes a JDBC Connection, PreparedStatement, and ResultSet, releasing
	 * associated resources.
	 *
	 * @param connection The JDBC Connection to be closed.
	 * @param ps         The PreparedStatement to be closed.
	 * @param rs         The ResultSet to be closed.
	 */

	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
