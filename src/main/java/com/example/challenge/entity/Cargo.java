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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table
@Entity
public class Cargo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 310769906025662684L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private UUID id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;
	
	@OneToMany(mappedBy = "", cascade = CascadeType.ALL)
	private List<Trabalhador> trabalhadores;
	
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

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

}