package ua.nure.koval.hotel.util;

import java.util.Comparator;

import ua.nure.koval.hotel.entity.Room;

public class RoomCapacityComparator implements Comparator<Room> {

	@Override
	public int compare(Room room1, Room room2) {
		return room1.getCapacity() - room2.getCapacity();
	}

}
