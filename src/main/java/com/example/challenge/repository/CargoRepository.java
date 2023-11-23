package com.example.challenge.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.challenge.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, UUID> {

	Boolean existsByName (String cargo);

}
