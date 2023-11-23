package com.example.challenge.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
public class Trabalhador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8285320207799218382L;

	@Id
	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "setor_id")
	private Setor setor;

	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
