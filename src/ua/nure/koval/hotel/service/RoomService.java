package ua.nure.koval.hotel.service;

import java.util.List;

import ua.nure.koval.hotel.db.dao.RoomDAO;
import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.entity.enums.Status;

public class RoomService {
	private RoomDAO rd = null;
	
	public RoomService() {
		rd = new RoomDAO();
	}
	
	public List<Room> getAllRooms(){
		return rd.getAll();
	}

	public List<Room> getFreeRooms() {
		return rd.getByStatus(Status.FREE);
	}

	public Room getById(Long roomId) {
		return rd.getById(roomId);
	}

	public boolean updateRoom(Room room) {
		return rd.update(room);		
	}
}
