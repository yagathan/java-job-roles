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

import com.exampe.challenge.exception.CPFExistenteException;
import com.exampe.challenge.exception.CargoNotFoundException;
import com.exampe.challenge.exception.SetorNotFoundException;
import com.exampe.challenge.exception.TrabalhadorNotFoundException;
import com.example.challenge.dto.TrabalhadorDTO;
import com.example.challenge.entity.Trabalhador;
import com.example.challenge.repository.TrabalhadorRepository;
import com.example.challenge.service.TrabalhadorService;

@RestController
@RequestMapping("/trabalhadores")
public class TrabalhadorController {
	
	
	@Autowired
	TrabalhadorService trabalhadorService;
	
	@Autowired
	TrabalhadorRepository trabalhadorRepository;
	
	 @PostMapping(value = "/include", consumes = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> insert(@RequestBody TrabalhadorDTO trabalhadorDTO) {
	        try {
	        	TrabalhadorDTO createTrabalhador = trabalhadorService.insert(trabalhadorDTO);
	        	return ResponseEntity.status(HttpStatus.CREATED).body(createTrabalhador);
				
			} catch (CPFExistenteException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}catch (SetorNotFoundException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}catch (CargoNotFoundException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		 	
	    }
	 
	 	@GetMapping("/getAll")
		public ResponseEntity<List<Trabalhador>> getTrabalhador(){
			
			List<Trabalhador> trabalhador = trabalhadorRepository.findAll();
			return ResponseEntity.ok(trabalhador);
		}

	    @PutMapping("update/{id}")
	    public ResponseEntity<TrabalhadorDTO> updateTrabalhador(@PathVariable UUID id, @RequestBody TrabalhadorDTO trabalhadorDTO) {
	        try {
	            TrabalhadorDTO updatedTrabalhador = trabalhadorService.update(id, trabalhadorDTO);
	            return ResponseEntity.ok(updatedTrabalhador);
	        } catch (TrabalhadorNotFoundException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteTrabalhador(@PathVariable UUID id) {
	        try {
	            trabalhadorService.delete(id);	            
	            return ResponseEntity.noContent().build();
	        } catch (TrabalhadorNotFoundException e) {
	            return ResponseEntity.notFound().build();
	        }
    }
	}
