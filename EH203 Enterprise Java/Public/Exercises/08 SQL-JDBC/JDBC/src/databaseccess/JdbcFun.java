package databaseccess;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.JDBC4Connection;

public class JdbcFun {
	
	
	private Connection connection;
	private static JdbcFun jdbcFun;
	
	public static JdbcFun getInstance() {
		if (jdbcFun == null) {
			return new JdbcFun();
		}
		
		return jdbcFun;
	}
	
	public Connection getConnection() throws SQLException {
		String connectionUrl = "jdbc:mysql://localhost:3308/Projects";
		connection = null;
		
		String username = "root";
		String password = "Admin123";
		connection = (Connection) DriverManager.getConnection(connectionUrl, username, password);
		
		return connection;
	}
	
}
