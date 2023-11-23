package com.example.challenge.service;

import java.util.UUID;

import com.example.challenge.dto.CargoDTO;

public interface CargoService {
	
	public CargoDTO insert(CargoDTO cargoDTO);

	public CargoDTO update(UUID id, CargoDTO cargoDTO);

	public void delete(UUID id);

}
