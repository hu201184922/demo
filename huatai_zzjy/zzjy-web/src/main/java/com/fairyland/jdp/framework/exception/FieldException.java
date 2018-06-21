package com.fairyland.jdp.framework.exception;

public class FieldException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FieldException() {
		super();
	}

	public FieldException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public FieldException(String message) {
		super(message);
	}

	public FieldException(Throwable throwable) {
		super(throwable);
	}
	
	public Throwable fillInStackTrace(){
		return null;
	}

}
