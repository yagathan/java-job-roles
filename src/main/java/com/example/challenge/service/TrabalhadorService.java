package com.example.challenge.service;

import java.util.UUID;

import com.example.challenge.dto.TrabalhadorDTO;

public interface TrabalhadorService {
	

	public TrabalhadorDTO insert(TrabalhadorDTO trabalhadorDTO);

	public TrabalhadorDTO update(UUID id, TrabalhadorDTO trabalhadorDTO);

	public void delete(UUID id);

}
