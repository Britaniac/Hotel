package ua.nure.koval.hotel.entity;

import java.time.LocalDate;

public class Invoice extends Entity {

	private static final long serialVersionUID = 3782768787902029479L;
	private double sum;
	private LocalDate created;
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
	
}
