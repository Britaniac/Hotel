package ua.nure.koval.hotel.entity;

import ua.nure.koval.hotel.entity.enums.Role;

public class User extends Entity {
	private static final long serialVersionUID = -3856409799378222226L;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String localeName;
	private String email;
	private Role role;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLocaleName() {
		return localeName;
	}
	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String toString() {
		return firstName + " " + lastName +" " + email + " " + role.toString();
	}
}
