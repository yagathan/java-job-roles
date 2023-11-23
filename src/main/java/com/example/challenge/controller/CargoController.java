package com.example.challenge.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exampe.challenge.exception.CargoDuplicadoException;
import com.exampe.challenge.exception.CargoNotFoundException;
import com.exampe.challenge.exception.SetorNotFoundException;
import com.example.challenge.dto.CargoDTO;
import com.example.challenge.entity.Cargo;
import com.example.challenge.repository.CargoRepository;
import com.example.challenge.service.CargoService;

@RestController
@RequestMapping("/cargo")
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@Autowired
	private CargoRepository cargoRepository;

	// Endpoint para incluir um novo cargo
	@PostMapping(value = "/include/{setorId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@PathVariable UUID setorId, @RequestBody CargoDTO cargoDTO) {
		try {
			cargoDTO.setSetorId(setorId);
			CargoDTO cargoInserido = cargoService.insert(cargoDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(cargoInserido);
		} catch (CargoDuplicadoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (SetorNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Cargo>> getCargo() {

		List<Cargo> cargo = cargoRepository.findAll();
		return ResponseEntity.ok(cargo);
	}

	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody CargoDTO cargoDTO) {
		try {
			CargoDTO cargoAtualizado = cargoService.update(id, cargoDTO);
			return ResponseEntity.status(HttpStatus.OK).body(cargoAtualizado);
		} catch (CargoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (CargoDuplicadoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (SetorNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable UUID id) {
		try {
			cargoService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (CargoNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
