package com.exampe.challenge.exception;

public class CargoDuplicadoException extends RuntimeException{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2272519719422586060L;

	public CargoDuplicadoException() {
	        super("Cargo já existe!");
	 }
}