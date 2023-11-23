package com.example.challenge.dto;

import java.io.Serializable;
import java.util.UUID;

public class SetorDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5018575280216882795L;
	
	public SetorDTO() {
    }
	private UUID id;
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
