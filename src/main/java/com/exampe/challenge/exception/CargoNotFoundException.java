package com.exampe.challenge.exception;

public class CargoNotFoundException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7479423246987444991L;

	public CargoNotFoundException() {
        super("Cargo não encontrado!");
 }
}
