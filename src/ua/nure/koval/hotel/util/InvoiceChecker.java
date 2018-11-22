package ua.nure.koval.hotel.util;

import java.time.LocalDate;
import java.util.List;

import ua.nure.koval.hotel.entity.Invoice;
import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.service.InvoiceService;
import ua.nure.koval.hotel.service.RequestService;

public class InvoiceChecker implements Runnable{
	private LocalDate now = null;
	private InvoiceService invServ = null;
	private RequestService reqServ = null;
	
	public InvoiceChecker() {
		now = LocalDate.now();
		invServ = new InvoiceService();
		reqServ = new RequestService();
	}
	
	public void run() {
		System.out.println("Checking stuff");
		List<Invoice> invoices = invServ.getAllInvoices();
		for (Invoice inv: invoices) {
			if (now.minusDays(3L).isAfter(inv.getCreated()) && !inv.isPaid()) {
				System.out.println(inv.getRequestID());
				Request req = reqServ.getRequestById(inv.getRequestID());
				System.out.println(reqServ.deleteRequest(req));
			}
		}
	}
}
