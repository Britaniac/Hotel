package ua.nure.koval.hotel.util;

import java.sql.SQLException;

import ua.nure.koval.hotel.db.dao.UserDAO;
import ua.nure.koval.hotel.entity.User;

public class Authentication {
	
	public static boolean check(String login, String password) {
		UserDAO ud = new UserDAO();
		if (password.equals(ud.getPasswordByLogin(login))) {
			return true;
		}
		return false;
	}
}
