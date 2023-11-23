package com.exampe.challenge.exception;

public class CPFExistenteException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8614998087272680014L;

	public CPFExistenteException() {
        super("CPF já existe!");
 }

}
