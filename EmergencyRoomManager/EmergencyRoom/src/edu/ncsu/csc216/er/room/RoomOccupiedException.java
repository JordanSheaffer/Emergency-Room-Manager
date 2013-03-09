package edu.ncsu.csc216.er.room;

/**
 * RoomOccupiedException
 * Thrown when trying to add a patient to an occupied room
 * 
 * @author Josh Stetson
 */
public class RoomOccupiedException extends Exception {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Null constructor
	 */
	public RoomOccupiedException() {
		super("Exception: Room Occupied");
	}
	
	/**
	 * Constructor
	 * @param arg Optional string message
	 */
	public RoomOccupiedException(String arg) {
		super(arg);
	}
}
