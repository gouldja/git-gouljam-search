package com.gouldja.common.framework;

/**
 * Wrapper class for runtime exceptions thrown by specific types of accessor.
 */
public class AccessorException extends Exception {

	private static final long serialVersionUID = -1047316244714936129L;
	
	public AccessorException(String message, Throwable t) {
		super(message,t);
	}

}
