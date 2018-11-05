package ua.nure.koval.hotel.Main;

import java.util.ArrayList;
import java.util.List;

import ua.nure.koval.hotel.db.dao.RequestDAO;
import ua.nure.koval.hotel.entity.Request;

public class PopulateRooms {

	public static void main(String[] args) {
		List<Request> reqs  = new ArrayList<>();
		RequestDAO rd = new RequestDAO();
		
		System.out.println(reqs.size());
		for (Request req: reqs) {
			System.out.println(rd.save(req));
		}	
	}

}
