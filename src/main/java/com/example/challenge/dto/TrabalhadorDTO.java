package com.example.challenge.dto;

import java.io.Serializable;
import java.util.UUID;

public class TrabalhadorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -784729986231420392L;

	private String cpf;
	private String name;
	private UUID setorId;
	private UUID cargoId;

	public UUID getSetorId() {
		return setorId;
	}

	public void setSetorId(UUID setorId) {
		this.setorId = setorId;
	}

	public UUID getCargoId() {
		return cargoId;
	}

	public void setCargoId(UUID cargoId) {
		this.cargoId = cargoId;
	}

	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrabalhadorDTO() {
	}

}
