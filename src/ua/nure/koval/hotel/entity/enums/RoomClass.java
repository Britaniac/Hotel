package ua.nure.koval.hotel.entity.enums;

public enum RoomClass {
	STANDARD, DELUXE, SUITE;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static RoomClass fromString(String rClass) {
		if(rClass != null) {
			return RoomClass.valueOf(rClass.toUpperCase());
		}
		return null;
	}
}
