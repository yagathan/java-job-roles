package com.example.challenge.service;

import java.util.UUID;

import com.example.challenge.dto.SetorDTO;

public interface SetorService {

	public SetorDTO insert(SetorDTO setorDTO);

	public SetorDTO update(UUID id, SetorDTO setorDTO);

	public void delete(UUID id);

}
