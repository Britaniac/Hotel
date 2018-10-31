package ua.nure.koval.hotel.entity;

public enum Role {
	MANAGER, CLIENT, UNREGISTERED;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static Role fromString(String role) {
		return Role.valueOf(role.toUpperCase());
	}
	
}
