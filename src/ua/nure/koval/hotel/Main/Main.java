package ua.nure.koval.hotel.Main;

import java.sql.SQLException;
import java.util.List;

import ua.nure.koval.hotel.DB.DBManager;

public class Main {

	public static void main(String...strings) throws SQLException {
		DBManager dbm = DBManager.getInstance();
		printList(dbm.findAllUsers());

	}

	private static <T> void printList(List<T> list) {
		for (T element : list) {
			System.out.println(element);
		}
	}

}

