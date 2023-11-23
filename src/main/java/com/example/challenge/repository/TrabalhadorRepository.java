package com.example.challenge.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.challenge.entity.Trabalhador;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador, UUID>{
	
	Boolean existsByCpf (String cpf);

}
