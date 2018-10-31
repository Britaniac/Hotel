package ua.nure.koval.hotel.entity;

public enum RoomClass {
	STANDARD, DELUXE, SUITE;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public RoomClass fromString(String rClass) {
		return RoomClass.valueOf(rClass.toUpperCase());
	}
}
