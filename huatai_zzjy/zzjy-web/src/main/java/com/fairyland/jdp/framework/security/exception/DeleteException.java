package com.fairyland.jdp.framework.security.exception;

public class DeleteException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6755402156387036041L;

	public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
