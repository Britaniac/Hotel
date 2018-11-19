package ua.nure.koval.hotel.entity;

import java.time.LocalDate;

public class Invoice extends Entity {

	private static final long serialVersionUID = 3782768787902029479L;
	private double sum;
	private LocalDate created;
	private boolean paid = false;
	private Long requestID;
	
	public Invoice() {
		created = LocalDate.now();
	}
	
	public Long getRequestID() {
		return requestID;
	}
	public void setRequestID(Long requestID) {
		this.requestID = requestID;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
}
