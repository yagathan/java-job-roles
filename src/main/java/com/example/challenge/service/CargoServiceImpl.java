package com.example.challenge.service;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exampe.challenge.exception.CargoDuplicadoException;
import com.exampe.challenge.exception.CargoNotFoundException;
import com.exampe.challenge.exception.SetorNotFoundException;
import com.example.challenge.dto.CargoDTO;
import com.example.challenge.entity.Cargo;
import com.example.challenge.entity.Setor;
import com.example.challenge.repository.CargoRepository;
import com.example.challenge.repository.SetorRepository;

@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SetorRepository setorRepository;

	public CargoDTO insert(CargoDTO cargoDTO) {

		Optional<Setor> setorOptional = setorRepository.findById(cargoDTO.getSetorId());
		if (!setorOptional.isPresent()) {
			throw new SetorNotFoundException();
		}

		Cargo cargo = modelMapper.map(cargoDTO, Cargo.class);

		cargo.setSetor(setorOptional.get());

		if (cargoRepository.existsByName(cargoDTO.getName())) {
			throw new CargoDuplicadoException();
		}

		cargo = cargoRepository.save(cargo);

		return modelMapper.map(cargo, CargoDTO.class);
	}

	@Override
	public CargoDTO update(UUID id, CargoDTO cargoDTO) {

		Cargo cargoExistente = cargoRepository.findById(id).orElseThrow(() -> new CargoNotFoundException());

		Optional<Setor> setorOptional = setorRepository.findById(cargoDTO.getSetorId());
		if (!setorOptional.isPresent()) {
			throw new SetorNotFoundException();
		}

		modelMapper.map(cargoDTO, cargoExistente);

		if (cargoRepository.existsByName(cargoDTO.getName())) {
			throw new CargoDuplicadoException();
		}

		cargoExistente = cargoRepository.save(cargoExistente);

		return modelMapper.map(cargoExistente, CargoDTO.class);
	}

	@Override
	public void delete(UUID id) {

		Cargo cargoExistente = cargoRepository.findById(id).orElseThrow(() -> new CargoNotFoundException());

		cargoRepository.delete(cargoExistente);
	}
}
