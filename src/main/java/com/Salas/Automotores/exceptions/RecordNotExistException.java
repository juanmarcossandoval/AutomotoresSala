package com.Salas.Automotores.exceptions;

public class RecordNotExistException extends Exception{
    
	private static final long serialVersionUID = -1263260811056662797L;

	public RecordNotExistException(String s) {
        super(s);
    }
}
