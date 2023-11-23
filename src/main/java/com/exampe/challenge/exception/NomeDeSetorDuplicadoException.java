package com.exampe.challenge.exception;

public class NomeDeSetorDuplicadoException extends RuntimeException{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 771082841441913157L;

	public NomeDeSetorDuplicadoException() {
	        super("Setor já existe!");
	 }

}
