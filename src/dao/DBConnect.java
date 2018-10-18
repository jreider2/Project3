package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** @author Nathneal A*/
public class DBConnect {
	
	public static Connection getDatabaseConnection() {
		return openConnection();
	}
	
	private static Connection openConnection() {
		Connection connection = null;
		DBConfig config = new DBConfig();
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(config.getDatabaseURL(),config.getUsername(),config.getPassword());
			
		}catch(SQLException exception) {
			exception.printStackTrace();
		}//catch(ClassNotFoundException cnException) {
		//	cnException.printStackTrace();
		//}
		
		return connection;
	}

}
