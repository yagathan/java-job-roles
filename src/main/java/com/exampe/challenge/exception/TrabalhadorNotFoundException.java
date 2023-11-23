package com.exampe.challenge.exception;

public class TrabalhadorNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5640606142402774361L;

	public TrabalhadorNotFoundException() {
        super("Trabalhador não encontrado!");
 }


}
