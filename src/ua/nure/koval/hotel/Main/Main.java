package ua.nure.koval.hotel.Main;

import java.sql.SQLException;
import java.util.List;

import ua.nure.koval.hotel.db.DBManager;
import ua.nure.koval.hotel.db.dao.UserDAO;
import ua.nure.koval.hotel.entity.Role;
import ua.nure.koval.hotel.entity.User;

public class Main {

	public static void main(String...strings) throws SQLException {
		UserDAO ud = new UserDAO();
		User user = new User();
		user.setFirstName("Name");
		user.setLastName("LastName");
		user.setEmail("email@gmail.com");
		user.setLogin("login");
		user.setPassword("1234");
		user.setRole(Role.CLIENT);
		ud.insertUser(user);
		
		user = ud.findByEmail("email@gmail.com");
		System.out.println(user);

	}

	private static <T> void printList(List<T> list) {
		for (T element : list) {
			System.out.println(element);
		}
	}

}

