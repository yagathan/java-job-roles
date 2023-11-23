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

import com.exampe.challenge.exception.NomeDeSetorDuplicadoException;
import com.exampe.challenge.exception.SetorNotFoundException;
import com.example.challenge.dto.SetorDTO;
import com.example.challenge.entity.Setor;
import com.example.challenge.repository.SetorRepository;
import com.example.challenge.service.SetorService;

@RestController
@RequestMapping("/setor")
public class SetorController {
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private SetorService setorService;

	@PostMapping(value = "/include", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> insert(@RequestBody SetorDTO setorDTO) {
			try {
				SetorDTO setorInserido = setorService.insert(setorDTO);
				return ResponseEntity.status(HttpStatus.CREATED).body(setorInserido);
			} catch (NomeDeSetorDuplicadoException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
                     
	}

	
	@GetMapping("/getAll")
	public ResponseEntity<List<Setor>> getSetor(){
		
		List<Setor> setor = setorRepository.findAll();
		return ResponseEntity.ok(setor);
	}
	
	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SetorDTO setorDTO) {
	    try {
	        SetorDTO setorAtualizado = setorService.update(id, setorDTO);
	        return ResponseEntity.status(HttpStatus.OK).body(setorAtualizado);
	    } catch (SetorNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + id);
	    } catch (NomeDeSetorDuplicadoException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable UUID id) {
		    try {
		        setorService.delete(id);
		        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		    } catch (SetorNotFoundException e) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		    }
		}
	}
	
	


