package ua.nure.koval.hotel.entity;

import ua.nure.koval.hotel.entity.enums.RoomClass;
import ua.nure.koval.hotel.entity.enums.Status;

public class Room extends Entity {
	private static final long serialVersionUID = -6703945838881235066L;
	private int capacity;
	private double cost;
	private RoomClass rClass;
	private Status status;
	private Long userId;
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public RoomClass getrClass() {
		return rClass;
	}
	public void setrClass(RoomClass rClass) {
		this.rClass = rClass;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String toString() {
		return "id: " + getId() + " capacity: " + capacity + " cost: " + cost + " " + rClass.getName() + " " + status.getName();
	}
}
