package ua.nure.koval.hotel.util;

import java.util.Comparator;

import ua.nure.koval.hotel.entity.Room;

public class RoomClassComparator implements Comparator<Room> {

	@Override
	public int compare(Room room1, Room room2) {
		return assignValue(room1) - assignValue(room2);
	}
	
	private int assignValue(Room room) {
		if (room.getrClass().getName().equalsIgnoreCase("standard")) {
			return 1;
		} else if (room.getrClass().getName().equalsIgnoreCase("deluxe")) {
			return 2;
		}
		return 3;
	}

}
