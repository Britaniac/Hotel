package ua.nure.koval.hotel.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.koval.hotel.db.DBManager;
import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.entity.enums.Role;

public class UserDAO implements DAO<User> {
	
	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES(DEFAULT, ?,?,?,?,?,?,?)";
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
	private static final String SQL_UPDATE_USER = "UPDATE users SET login=?, password=?, first_name=?, last_name=?, email=?, locale=?, role=?"
			+ "WHERE ID=?";
	private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id=?";
	private static final String SQL_GET_PASSWORD = "SELECT password FROM users WHERE login=?";
	
	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		try (Connection con = DBManager.getInstance().getConnection();
			Statement stmt = con.createStatement()){
			ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
			UserMapper mapper = new UserMapper();
			while (rs.next()) {
				User user = mapper.mapRow(rs);
				users.add(user);
			}
/*			stmt.close();
			con.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User findByLogin(String login) {
		try (Connection con = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN)){
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				UserMapper mapper = new UserMapper();
				return mapper.mapRow(rs);
			}
/*			pstmt.close();
			con.close();*/
		} catch (SQLException e) {

		}
		return null;
	}
	
	public User findByEmail(String email) {
		try (Connection con = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_EMAIL)){			
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				UserMapper mapper = new UserMapper();
				User user = mapper.mapRow(rs);
				return user;
			}
/*			pstmt.close();
			con.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	@Override
	public User getById(Long id) {
		User user = new User();
		try (Connection con = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID)){
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			UserMapper mapper = new UserMapper();
			user = mapper.mapRow(rs);
/*			pstmt.close();
			con.close();*/
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean save(User user){
		try (Connection con = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)){
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
/*			pstmt.close();
			con.close();*/
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(User user) {
		try (Connection con = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER)){
			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getFirstName());
			pstmt.setString(k++, user.getLastName());
			pstmt.setString(k++, user.getEmail());
			pstmt.setString(k++, user.getLocaleName());
			pstmt.setString(k++, user.getRole().getName());
			pstmt.setLong(k++, user.getId());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}	
/*			pstmt.close();
			con.close();*/
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(User user) {
		try (Connection con = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_USER)){
			int k = 1;
			pstmt.setLong(k++, user.getId());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}	
/*			pstmt.close();
			con.close();*/
		} catch(SQLException e) {
			e.printStackTrace();
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
	
	public String getPasswordByLogin(String login) {
		try (Connection con = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_GET_PASSWORD)){
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
/*			pstmt.close();
			con.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
