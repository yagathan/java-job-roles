package com.example.challenge.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.challenge.entity.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, UUID> {

	Boolean existsByName (String name);

}
