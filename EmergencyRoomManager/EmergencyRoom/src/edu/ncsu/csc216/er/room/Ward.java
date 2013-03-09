package edu.ncsu.csc216.er.room;

import edu.ncsu.csc216.er.patient.Patient;

/**
 * Represents a list of examination rooms in the ER.
 * Responsible for adding rooms as needed and releasing
 * patients.
 * 
 * @author Josh Stetson
 */
public class Ward {

	/** Maximum number of rooms allowed */
	private static final int MAX_ROOMS = 30;
	/** Default number of rooms in the ward */
	private static final int DEFAULT_SIZE = 8;
	/** Number of occupied rooms in the ward */
	private int size = 0;
	/** List of rooms in the ward */
	private Room[] room = new Room[MAX_ROOMS];
	
	/**
	 * Null constructor for Ward
	 */
	public Ward() {
		initRooms(DEFAULT_SIZE);
	}
	
	private void initRooms(int howMany) {
		for (int i = 0; i < DEFAULT_SIZE; i++) {
			addRoom();
		}
	}
	
	/**
	 * Adds a room to the list
	 */
	public void addRoom() {
		if (size < MAX_ROOMS) {
			if (canAddGeneralRoom()) {
				for (int i = size; i > 0; i--) {
					room[i] = room[i - 1];
				}
				room[0] = new Room();
				size++;
			} else {
				room[size++] = new PediatricRoom();
			}
		}
	}
	
	/**
	 * Getter for number of empty rooms
	 * @return Number of empty rooms
	 */
	public int numberOfEmptyRooms() {
		int emptyRooms = 0;
		for (int i = 0; i < size; i++) {
			if (!room[i].isOccupied()) {
				emptyRooms++;
			}
		}
		return emptyRooms;
	}
	
	/**
	 * Getter for room at specified list index
	 * @param index Index of room requested
	 * @return Room at specified index
	 */
	public Room getRoomAt(int index) {
		if (room[index] == null) {
			return null;
		}
		return room[index];
	}
	
	/**
	 * Getter for number of occupied rooms
	 * @return Number of occupied rooms
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Removes a patient from the room at the given index
	 * @param index Index of room to remove patient
	 * @return Patient removed from the room
	 */
	public Patient release(int index) {
		if (index < this.getSize() && index >= 0) {
			return room[index].release();
		}
		return null;
	}
	
	private boolean canAddGeneralRoom() {
		// new size of list after adding potential room
		int newSize = size + 1;
		int pediatricCount = 0;
		double threshhold = 1.0 / 3.0;
		// count all pediatric rooms in the ward
		for (int i = 0; i < size; i++) {
			if (room[i].getRoomNumber().charAt(0) == 'P') {
				pediatricCount++;
			}
		}
		// check the new ratio of rooms
		if (((double)pediatricCount / newSize) >= threshhold) {
			return true;
		}
		return false;
	}
	
}
