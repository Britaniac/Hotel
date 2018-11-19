package ua.nure.koval.hotel.service;

import ua.nure.koval.hotel.db.dao.UserDAO;
import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.entity.enums.Role;

public class UserService {
	private UserDAO ud;
	
	public UserService() {
		ud = new UserDAO();
	}
	
	public String registerUser(String login, String password, String fName, String lName, String email, String locale) {
		if (ud.findByEmail(email) == null) {
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setFirstName(fName);
			user.setLastName(lName);
			user.setEmail(email);
			user.setLocaleName(locale);
			user.setRole(Role.CLIENT);
			ud.save(user);
			return "Registration succesful";
		} else {
			return "Account with this email already exists";
		}
	}
	
	public User getUserByLogin(String login) {
		return ud.findByLogin(login);
	}

	public boolean updateUser(User user) {
		return ud.update(user);		
	}
}
