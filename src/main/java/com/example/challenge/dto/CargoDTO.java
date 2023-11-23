package com.example.challenge.dto;

import java.io.Serializable;
import java.util.UUID;

public class CargoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2910511470695731504L;
	
	private UUID id;
    private String name;
    private UUID setorId;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID getSetorId() {
		return setorId;
	}
	public void setSetorId(UUID setorId) {
		this.setorId = setorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public CargoDTO() {
    }

}
