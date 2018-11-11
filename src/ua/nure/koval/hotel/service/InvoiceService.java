package ua.nure.koval.hotel.service;

import java.util.List;

import ua.nure.koval.hotel.db.dao.InvoiceDAO;
import ua.nure.koval.hotel.entity.Invoice;

public class InvoiceService {
	private InvoiceDAO invdao = null;
	
	public InvoiceService() {
		invdao = new InvoiceDAO(); 
	}
	
	public List<Invoice> getAllInvoices() {
		return invdao.getAll();
	}
	
	public Invoice getById(Long id) {
		return invdao.getById(id);
	}
	
	public void saveInvoice(Invoice invoice) {
		invdao.save(invoice);
	}
}
