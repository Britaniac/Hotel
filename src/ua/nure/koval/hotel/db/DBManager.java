package ua.nure.koval.hotel.db;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.koval.hotel.connection_pool.DBCPDataSource;
import ua.nure.koval.hotel.connection_pool.HikariCPDataSource;

public class DBManager {

	private static DBManager instance; 
	
	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	DBManager() {
	}

	public Connection getConnection() throws SQLException {
		//Connection con = DBCPDataSource.getConnection();
		Connection con = HikariCPDataSource.getConnection();
		return con;
	}
}
