package ua.nure.koval.hotel.service;

import java.util.List;

import ua.nure.koval.hotel.db.dao.RequestDAO;
import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.User;

public class RequestService {
	RequestDAO rd = null;
	
	public RequestService() {
		rd = new RequestDAO();
	}
	
	public List<Request> getAllRequests() {
		return rd.getAll();
	}
	
	public List<Request> getUnpaidRequests(){
		return rd.getUnpaid();
	}
	
	public List<Request> getByUser(User user){
		return rd.getByUserId(user.getId());
	}

	public Request getRequestById(Long requestId) {
		return rd.getById(requestId);
	}

	public boolean deleteRequest(Request req) {
		return rd.delete(req);		
	}

	public List<Request> getUnprocessed() {
		return rd.getUnprocessed();
	}
}
