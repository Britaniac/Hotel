package ua.nure.koval.hotel.util;

import java.util.Comparator;

import ua.nure.koval.hotel.entity.Room;

public class RoomStatusComparator implements Comparator<Room> {

	@Override
	public int compare(Room room1, Room room2) {
		return assignValue(room1) - assignValue(room2);
	}
	
	private int assignValue(Room room) {
		if (room.getStatus().getName().equalsIgnoreCase("free")) {
			return 1;
		} else if (room.getStatus().getName().equalsIgnoreCase("booked")) {
			return 2;
		} else if (room.getStatus().getName().equalsIgnoreCase("occupied")) {
			return 3;
		}
		return 4;
	}
}
