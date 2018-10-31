package ua.nure.koval.hotel.entity;

import java.time.LocalDate;

public class Booking extends Entity {
	private static final long serialVersionUID = 2300244106638887663L;
	private Long roomId;
	private Long userId;
	private LocalDate created;
	private LocalDate from;
	private LocalDate to;
	private boolean paid;
	private Long invoiceId;
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public LocalDate getFrom() {
		return from;
	}
	public void setFrom(LocalDate from) {
		this.from = from;
	}
	public LocalDate getTo() {
		return to;
	}
	public void setTo(LocalDate to) {
		this.to = to;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
