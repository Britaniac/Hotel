package ua.nure.koval.hotel.service;

import java.util.List;

import ua.nure.koval.hotel.db.dao.RequestDAO;
import ua.nure.koval.hotel.entity.Request;

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
	
	public List<Request> getNewRequests(){
		return rd.getNew();
	}

	public Request getRequestById(Long requestId) {
		return rd.getById(requestId);
	}
}
