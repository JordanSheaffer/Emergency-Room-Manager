package edu.ncsu.csc216.er.gui;

public class UserChoiceException extends Exception {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Null constructor
	 */
	public UserChoiceException() {
		super("Exception: No Available Room");
	}
	
	/**
	 * Constructor
	 * @param arg Optional string message
	 */
	public UserChoiceException(String arg) {
		super(arg);
	}
}
