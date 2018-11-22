package ua.nure.koval.hotel.service;

import java.util.List;

import ua.nure.koval.hotel.db.dao.InvoiceDAO;
import ua.nure.koval.hotel.entity.Invoice;
import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.entity.enums.Status;

public class InvoiceService {
	private InvoiceDAO invDao = new InvoiceDAO();
    private RoomService roomServ = new RoomService();
    
	public InvoiceService() {
	}
	
	public List<Invoice> getAllInvoices() {
		return invDao.getAll();
	}
	
	public Invoice getById(Long id) {
		return invDao.getById(id);
	}
	
	public void saveInvoice(Invoice invoice) {
		invDao.save(invoice);
	}

	public String createFromRequest(Request req) {
		Room room = roomServ.getById(req.getRoomId());
		System.out.println(room);
		Invoice inv = new Invoice();
		double sum = room.getCost() * req.getDuration();
		inv.setSum(sum);
		inv.setPaid(false);
		inv.setRequestID(req.getId());
		if (save(inv)) {
			room.setStatus(Status.BOOKED);
			roomServ.updateRoom(room);
			return "Success";
		} 
		return "Failure";
	}

	private boolean save(Invoice inv) {
		return invDao.save(inv);		
	}

	public List<Invoice> getByUser(User user) {
		return invDao.getByUserId(user.getId());
	}

	public boolean update(Invoice invoice) {
		return invDao.update(invoice);
	}

	public boolean delete(Invoice inv) {
		return invDao.delete(inv);
	}

}
