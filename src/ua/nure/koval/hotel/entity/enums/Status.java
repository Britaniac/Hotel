package ua.nure.koval.hotel.entity.enums;

public enum Status {
	FREE, BOOKED, OCCUPIED, UNAVAILABLE;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static Status fromString(String status) {
		return Status.valueOf(status.toUpperCase());
	}
}
