package com.fairyland.jdp.framework.exception;

public class MetLifeCrmException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MetLifeCrmException() {
		super();
	}

	public MetLifeCrmException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public MetLifeCrmException(String message) {
		super(message);
	}

	public MetLifeCrmException(Throwable throwable) {
		super(throwable);
	}
	
	public Throwable fillInStackTrace(){
		return null;
	}

}
