package ua.nure.koval.hotel.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 4035549590691712857L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
