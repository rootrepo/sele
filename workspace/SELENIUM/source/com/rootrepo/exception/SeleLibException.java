package com.rootrepo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleLibException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2167618072272523042L;
	static Logger logger = LoggerFactory.getLogger(SeleLibException.class);
	
	private String message;

	public SeleLibException(String message) {
		this.setMessage(message);		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
}
