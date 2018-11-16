package ua.nure.koval.hotel.service;

import java.time.LocalDate;
import java.util.List;

import ua.nure.koval.hotel.db.dao.RequestDAO;
import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.Room;
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

	public boolean createFromRoom(Room room, LocalDate to, Long userId) {
		Request r = new Request();
		r.setCapacity(room.getCapacity());
		r.setrClass(room.getrClass());
		r.setRoomId(room.getId());
		r.setTo(to);
		r.setUserID(userId);
		return save(r);	
	}

	private boolean save(Request r) {
		return rd.save(r);
	}
}
