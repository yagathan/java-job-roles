package com.example.challenge.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table
@Entity
public class Setor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8680813036679841738L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private UUID id;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name", unique = true)
	private String name;
	
	@OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
	private List<Trabalhador> trabalhadores;
}
