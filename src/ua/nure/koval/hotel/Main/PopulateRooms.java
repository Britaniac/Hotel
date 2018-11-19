package ua.nure.koval.hotel.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ua.nure.koval.hotel.db.dao.RequestDAO;
import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.entity.enums.RoomClass;
import ua.nure.koval.hotel.entity.enums.Status;
import ua.nure.koval.hotel.service.RoomService;
import ua.nure.koval.hotel.util.RoomClassComparator;

public class PopulateRooms {

	public static void main(String[] args) {
		List<Room> rooms  = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Room room = new Room();
			room.setCost(i * 100);
			room.setCapacity(i%3 + 1);
			room.setrClass(RoomClass.values()[i%3]);
			room.setStatus(Status.FREE);
			rooms.add(room);
		}
		
		Collections.sort(rooms, new RoomClassComparator());
		for (Room r: rooms) {
			System.out.println(r);
		}
		
		/*	RoomService rs = new RoomService();
		
	for (Room r: rooms) {
			System.out.println(rs.save(r));
		}*/
	}
	


}
