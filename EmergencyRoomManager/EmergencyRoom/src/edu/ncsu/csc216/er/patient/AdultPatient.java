package edu.ncsu.csc216.er.patient;

import edu.ncsu.csc216.er.room.NoAvailableRoomException;
import edu.ncsu.csc216.er.room.RoomOccupiedException;
import edu.ncsu.csc216.er.room.RoomPatientMismatchException;
import edu.ncsu.csc216.er.room.Ward;

/**
 * Concrete class for an AdultPatient.  Responsible for defining how
 * to pick an examination room.
 * 
 * @author Josh Stetson
 */
public class AdultPatient extends Patient {
	
	/**
	 * Constructor for AdultPatient
	 * @param name Name of patient
	 * @param priority Priority of patient
	 */
	public AdultPatient(String name, int priority) {
		super(name, priority);
	}
	
	/**
	 * Allows patient to pick an examination room
	 * @param w List of examination rooms
	 */
	public void pickRoom(Ward w) throws NoAvailableRoomException {
		for (int i = 0; i < w.getSize(); i++) {
			if (w.getRoomAt(i).getRoomNumber().charAt(0) == '1') {
				try {
					w.getRoomAt(i).occupy(this);
					break;
				} catch (RoomOccupiedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (RoomPatientMismatchException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			} else {
				throw new NoAvailableRoomException();
			}
		}
	}
	
	/**
	 * Overrides toString for AdultPatient class
	 * @return String representation of AdultPatient
	 */
	public String toString() {
		return "A " + getPriority() + " " + getName();
	}

}
