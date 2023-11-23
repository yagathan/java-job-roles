package com.example.challenge.service;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exampe.challenge.exception.CPFExistenteException;
import com.exampe.challenge.exception.CargoNotFoundException;
import com.exampe.challenge.exception.SetorNotFoundException;
import com.exampe.challenge.exception.TrabalhadorNotFoundException;
import com.example.challenge.dto.TrabalhadorDTO;
import com.example.challenge.entity.Cargo;
import com.example.challenge.entity.Setor;
import com.example.challenge.entity.Trabalhador;
import com.example.challenge.repository.CargoRepository;
import com.example.challenge.repository.SetorRepository;
import com.example.challenge.repository.TrabalhadorRepository;

@Service
public class TrabalhadorServiceImpl implements TrabalhadorService {

	@Autowired
	private TrabalhadorRepository trabalhadorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SetorRepository setorRepository;

	@Autowired
	private CargoRepository cargoRepository;

	public TrabalhadorDTO insert(TrabalhadorDTO trabalhadorDTO) {

		if (trabalhadorRepository.existsByCpf(trabalhadorDTO.getCpf())) {
			throw new CPFExistenteException();
		}

		Optional<Setor> setorOptional = setorRepository.findById(trabalhadorDTO.getSetorId());
		if (!setorOptional.isPresent()) {
			throw new SetorNotFoundException();
		}

		Optional<Cargo> cargoOptional = cargoRepository.findById(trabalhadorDTO.getCargoId());
		if (!cargoOptional.isPresent()) {
			throw new CargoNotFoundException();
		}

		Trabalhador trabalhador = modelMapper.map(trabalhadorDTO, Trabalhador.class);

		trabalhador.setSetor(setorOptional.get());
		trabalhador.setCargo(cargoOptional.get());

		trabalhador = trabalhadorRepository.save(trabalhador);

		return modelMapper.map(trabalhador, TrabalhadorDTO.class);
	}

	@Override
	public TrabalhadorDTO update(UUID id, TrabalhadorDTO trabalhadorDTO) {
		Optional<Trabalhador> trabalhadorOptional = trabalhadorRepository.findById(id);
		if (trabalhadorOptional.isPresent()) {
			Trabalhador trabalhador = trabalhadorOptional.get();

			Trabalhador updatedTrabalhador = trabalhadorRepository.save(trabalhador);
			return modelMapper.map(updatedTrabalhador, TrabalhadorDTO.class);
		} else {
			throw new TrabalhadorNotFoundException();
		}
	}
	
	@Override
	public void delete(UUID id) {
		Optional<Trabalhador> trabalhadorOptional = trabalhadorRepository.findById(id);
		if (trabalhadorOptional.isPresent()) {
			Trabalhador trabalhador = trabalhadorOptional.get();
			trabalhadorRepository.delete(trabalhador);
		} else {
			throw new TrabalhadorNotFoundException();
		}
	}

}