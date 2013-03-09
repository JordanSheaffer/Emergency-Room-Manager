package edu.ncsu.csc216.er.room;

import edu.ncsu.csc216.er.patient.Patient;

/**
 * Represents a general room in the exam room list.
 * Can be used by any patient.
 * 
 * @author Josh Stetson
 */
public class Room {

	/** Occupied status of room */
	private boolean occupied;
	/** Patient occupying a room */
	private Patient patient;
	/** String representation of exam room number */
	private String roomNumber;
	/** Starting point for general exam room number */
	private static int roomGenerator = 101;
	
	/**
	 * Resets the room numbering to start at 101
	 */
	public static void setRoomNumberingTo101() {
		roomGenerator = 101;
	}
	
	/**
	 * Null constructor for Room
	 */
	public Room() {
		this.occupied = false;
		this.patient = null;
		this.roomNumber = "" + roomGenerator++;
	}
	
	/**
	 * Constructor for Room
	 * @param prefix Prefix to assign to the room number
	 */
	public Room(String prefix) {
		this.occupied = false;
		this.patient = null;
		String number = "" + roomGenerator++;
		String room = "";
		if (prefix.length() > 0) {
			room = prefix.charAt(0) + number.substring(1);
			this.roomNumber = room;
		} else {
			this.roomNumber = number;
		}
	}
	
	/**
	 * Getter for room number string
	 * @return Room number
	 */
	public String getRoomNumber() {
		return roomNumber;
	}
	
	/**
	 * Getter for occupied status
	 * @return True if room is occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}
	
	/**
	 * Removes a patient from an exam room
	 * @return Patient removed from room
	 */
	public Patient release() {
		// makes sure a patient is in the room
		if (patient == null) {
			return null;
		}
		// assigns patient to p and removes from room
		Patient p = patient;
		this.patient = null;
		this.occupied = false;
		return p;	
	}
	
	/**
	 * Assigns a patient to an exam room
	 * @param p Patient assigned to room
	 * @throws RoomOccupiedException 
	 * @throws RoomPatientMismatchException 
	 */
	public void occupy(Patient p) throws RoomOccupiedException, RoomPatientMismatchException  {
		if (isOccupied()) {
			throw new RoomOccupiedException();
		} else {
			patient = p;
			occupied = true;
		}
	}
	
	/**
	 * Overrides toString for Room class
	 * @return String representation of Room
	 */
	public String toString() {
		if (!isOccupied()) {
			return getRoomNumber() + ": EMPTY";
		} 
		return getRoomNumber() + ": " + patient.getName();
	}
}
