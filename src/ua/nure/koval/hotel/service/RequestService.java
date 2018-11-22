package ua.nure.koval.hotel.service;

import java.time.LocalDate;
import java.util.List;

import ua.nure.koval.hotel.db.dao.RequestDAO;
import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.entity.enums.RoomClass;
import ua.nure.koval.hotel.entity.enums.Status;

public class RequestService {
	private RequestDAO reqDao = new RequestDAO();
	private RoomService roomServ = new RoomService();
	private InvoiceService invServ = new InvoiceService();
	
	public RequestService() {
	}
	
	public List<Request> getAllRequests() {
		return reqDao.getAll();
	}
	
	public List<Request> getUnpaidRequests(){
		return reqDao.getUnpaid();
	}
	
	public List<Request> getByUser(User user){
		return reqDao.getByUserId(user.getId());
	}

	public Request getRequestById(Long requestId) {
		return reqDao.getById(requestId);
	}

	public boolean deleteRequest(Request req) {
		if (req.getRoomId() != 0L || req.getRoomId() != null) {
			roomServ.updateStatus(req.getRoomId(), Status.FREE);
		}
		return reqDao.delete(req);		
	}

	public List<Request> getUnprocessed() {
		if (invServ.getAllInvoices().size() < 1) {
			return reqDao.getAll();
		}
		return reqDao.getUnprocessed();
	}

	public boolean createFromRoom(Room room, int duration, Long userId) {
		Request r = new Request();
		r.setCapacity(room.getCapacity());
		r.setrClass(room.getrClass());
		r.setRoomId(room.getId());
		r.setDuration(duration);
		r.setUserID(userId);
		return save(r);	
	}

	private boolean save(Request r) {
		return reqDao.save(r);
	}

	public boolean createFromParams(int capacity, RoomClass rClass, int duration, Long id) {
		Request r = new Request();
		r.setCapacity(capacity);
		r.setrClass(rClass);
		r.setDuration(duration);
		r.setUserID(id);
		return save(r);
	}

	public boolean updateRequest(Request req) {
		return reqDao.update(req);		
	}
}
