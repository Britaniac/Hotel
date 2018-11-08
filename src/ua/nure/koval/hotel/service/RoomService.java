package ua.nure.koval.hotel.service;

import java.util.List;

import ua.nure.koval.hotel.db.dao.RoomDAO;
import ua.nure.koval.hotel.entity.Room;

public class RoomService {
	
	public List<Room> getAllRooms(){
		List<Room> rooms = null;
		RoomDAO rd = new RoomDAO();
		rooms = rd.getAll();
		return rooms;
	}
}
