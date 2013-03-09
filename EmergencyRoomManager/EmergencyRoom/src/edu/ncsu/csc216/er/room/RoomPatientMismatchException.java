package edu.ncsu.csc216.er.room;

/**
 * RoomPatientMismatchException
 * Thrown when trying to add a patient to the wrong type of room
 * 
 * @author Josh Stetson
 */
public class RoomPatientMismatchException extends Exception {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Null constructor
	 */
	public RoomPatientMismatchException() {
		super("Exception: Room-Patient Mismatch");
	}
	
	/**
	 * Constructor
	 * @param arg Optional string message
	 */
	public RoomPatientMismatchException(String arg) {
		super(arg);
	}

}
