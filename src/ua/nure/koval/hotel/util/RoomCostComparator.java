package ua.nure.koval.hotel.util;

import java.util.Comparator;

import ua.nure.koval.hotel.entity.Room;

public class RoomCostComparator implements Comparator<Room> {

	@Override
	public int compare(Room room1, Room room2) {
		if (room1.getCost() - room2.getCost() > 0) {
			return 1;
		} else if (room1.getCost() - room2.getCost() < 0) {
			return -1;
		}
		return 0;
	}

}
