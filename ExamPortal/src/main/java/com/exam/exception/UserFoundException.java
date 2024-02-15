package com.exam.exception;

public class UserFoundException extends Exception {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*public UserFoundException() {
		//super("User with this Username is alerady there in DB !!! try  with another name");
	}*/

	public UserFoundException(String msg) {
		super(msg);
	}
}
