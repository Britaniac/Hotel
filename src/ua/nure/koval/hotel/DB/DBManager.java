package ua.nure.koval.hotel.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.koval.hotel.connection_pool.DBCPDataSource;
import ua.nure.koval.hotel.entity.Role;
import ua.nure.koval.hotel.entity.User;

public class DBManager {
	
	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
	///////////////////////////////
	// singleton

	private static DBManager instance; // == null

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	DBManager() {
	}

	public List<User> findAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
		while (rs.next()) {
			User user = userMapper(rs);
			users.add(user);
		}
		return users;
	}

	private User userMapper(ResultSet rs) {
		User user = new User();
		int k = 1;
		try {
			user.setId(rs.getLong(k++));
			user.setLogin(rs.getString(k++));
			user.setPassword(rs.getString(k++));
			user.setFirstName(rs.getString(k++));
			user.setLastName(rs.getString(k++));
			user.setEmail(rs.getString(k++));
			user.setLocaleName(rs.getString(k++));
			user.setRole(Role.fromString(rs.getString(k++)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

/*	public boolean insertUser(User user) throws SQLException {
		Connection con = getConnection();

		PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
		int k = 1;
		pstmt.setString(k++, user.getLogin());

		if (pstmt.executeUpdate() > 0) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				return true;
			}
		}
		return false;*/


	public Connection getConnection() throws SQLException {
		Connection con = DBCPDataSource.getConnection();
		return con;
	}
}
