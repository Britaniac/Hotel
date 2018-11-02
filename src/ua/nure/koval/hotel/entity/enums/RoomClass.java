package ua.nure.koval.hotel.entity.enums;

public enum RoomClass {
	STANDARD, DELUXE, SUITE;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static RoomClass fromString(String rClass) {
		return RoomClass.valueOf(rClass.toUpperCase());
	}
}
