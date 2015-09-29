package com.elecnor.issue.tracker.helper;

public class ExcessFileSizeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcessFileSizeException(){}
	
	public ExcessFileSizeException(String message){
		super(message);
	}

}
