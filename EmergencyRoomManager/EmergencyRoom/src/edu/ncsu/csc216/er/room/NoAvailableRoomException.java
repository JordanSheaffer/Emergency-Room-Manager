package edu.ncsu.csc216.er.room;

/**
 * NoAvailableRoomException
 * Thrown when trying to add a room to a full list of rooms
 * 
 * @author Josh Stetson
 */
public class NoAvailableRoomException extends Exception {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Null constructor
	 */
	public NoAvailableRoomException() {
		super("Exception: No Available Room");
	}
	
	/**
	 * Constructor
	 * @param arg Optional string message
	 */
	public NoAvailableRoomException(String arg) {
		super(arg);
	}

}
