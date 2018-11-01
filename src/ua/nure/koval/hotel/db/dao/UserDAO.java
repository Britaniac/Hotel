package ua.nure.koval.hotel.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.koval.hotel.db.DBManager;
import ua.nure.koval.hotel.entity.Role;
import ua.nure.koval.hotel.entity.User;

public class UserDAO {
	
	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES(DEFAULT, ?,?,?,?,?,?,?)";
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
	
	public List<User> findAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		Connection con = DBManager.getInstance().getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
		UserMapper mapper = new UserMapper();
		while (rs.next()) {
			User user = mapper.mapRow(rs);
			users.add(user);
		}
		return users;
	}
	
	public User findByLogin(String login) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
		pstmt.setString(1, login);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		UserMapper mapper = new UserMapper();
		User user = mapper.mapRow(rs);
		return user;
	}
	
	public User findByEmail(String email) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_EMAIL);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		UserMapper mapper = new UserMapper();
		User user = mapper.mapRow(rs);
		return user;
	}
	
	public User findById(Long id) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		UserMapper mapper = new UserMapper();
		User user = mapper.mapRow(rs);
		return user;
	}

	public boolean insertUser(User user) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
		int k = 1;
		pstmt.setString(k++, user.getLogin());
		pstmt.setString(k++, user.getPassword());
		pstmt.setString(k++, user.getFirstName());
		pstmt.setString(k++, user.getLastName());
		pstmt.setString(k++, user.getEmail());
		pstmt.setString(k++, user.getLocaleName());
		pstmt.setString(k++, user.getRole().getName());
		if (pstmt.executeUpdate() > 0) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getLong(1));
				return true;
			}
		}
		return false;
	}
	
	private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
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
    }

}
