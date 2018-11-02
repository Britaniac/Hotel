package ua.nure.koval.hotel.entity;

import java.time.LocalDate;

import ua.nure.koval.hotel.entity.enums.RoomClass;

public class Request extends Entity {
	private static final long serialVersionUID = -4983628543644100712L;
	private int capacity;
	private RoomClass rClass;
	private Long roomId;
	private LocalDate from;
	private LocalDate to;
	
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
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public RoomClass getrClass() {
		return rClass;
	}
	public void setrClass(RoomClass rClass) {
		this.rClass = rClass;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	
	
}
