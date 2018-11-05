package ua.nure.koval.hotel.Main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.koval.hotel.db.DBManager;
import ua.nure.koval.hotel.db.dao.RoomDAO;
import ua.nure.koval.hotel.db.dao.UserDAO;
import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.entity.enums.Role;
import ua.nure.koval.hotel.entity.enums.RoomClass;
import ua.nure.koval.hotel.entity.enums.Status;

public class Main {

	public static void main(String...strings) throws SQLException {
		RoomDAO rd = new RoomDAO();
		Room room = rd.getById((long) (64));
		room.setCost(299);
		rd.delete(room);
		
	}

	private static <T> void printList(List<T> list) {
		for (T element : list) {
			System.out.println(element);
		}
	}

}

