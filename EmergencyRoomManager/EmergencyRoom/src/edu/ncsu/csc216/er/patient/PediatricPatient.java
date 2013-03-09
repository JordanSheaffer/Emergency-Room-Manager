package edu.ncsu.csc216.er.patient;

import edu.ncsu.csc216.er.room.NoAvailableRoomException;
import edu.ncsu.csc216.er.room.RoomOccupiedException;
import edu.ncsu.csc216.er.room.RoomPatientMismatchException;
import edu.ncsu.csc216.er.room.Ward;

/**
 * Concrete class that represents a pediatric patient in 
 * the emergency room.  Responsible for handling its priority 
 * in the list and picking an exam room.
 * 
 * @author Josh Stetson
 */
public class PediatricPatient extends Patient {
	
	/**
	 * Constructor for PediatricPatient
	 * @param name Name of patient
	 * @param priority Priority of patient
	 */
	public PediatricPatient(String name, int priority) {
		super(name, priority);
	}
	
	/**
	 * Allows patient to pick an examination room
	 * @param w List of examination rooms
	 */
	public void pickRoom(Ward w) throws NoAvailableRoomException {
		boolean roomFound = false;
		for (int i = w.getSize() - 1; i >= 0; i--) {
			try {
				w.getRoomAt(i).occupy(this);
				roomFound = true;
				break;
			} catch (RoomOccupiedException e) {
				//e.printStackTrace();
			} catch (RoomPatientMismatchException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		if (!roomFound) {
			throw new NoAvailableRoomException();
		}
	}
	
	
	/**
	 * Overrides toString for PediatricPatient class
	 * @return String representation of Patient
	 */
	public String toString() {
		return "P " + getPriority() + " " + getName();
		
	}
	

}
