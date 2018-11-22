package ua.nure.koval.hotel.util;

import ua.nure.koval.hotel.entity.Invoice;
import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.enums.Status;
import ua.nure.koval.hotel.service.InvoiceService;
import ua.nure.koval.hotel.service.RequestService;
import ua.nure.koval.hotel.service.RoomService;

public class Payment {
	
	public boolean payForInvoice(Invoice invoice) {
		InvoiceService invServ = new InvoiceService();
		RequestService reqServ = new RequestService();
		RoomService roomServ = new RoomService();
		invoice.setPaid(true);
		Request request = reqServ.getRequestById(invoice.getRequestID());
		roomServ.updateStatus(request.getRoomId(), Status.OCCUPIED);
		return invServ.update(invoice);
	}
}
