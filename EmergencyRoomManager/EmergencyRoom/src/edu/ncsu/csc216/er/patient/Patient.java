package edu.ncsu.csc216.er.patient;

import edu.ncsu.csc216.er.room.NoAvailableRoomException;
import edu.ncsu.csc216.er.room.Ward;

/**
 * Abstract class that represents a patient in the emergency room.  
 * Responsible for handling its priority in the list and 
 * picking an exam room.
 * 
 * @author Josh Stetson
 */
public abstract class Patient implements Prioritized {
	
	/** Name of patient */
	private String name;
	/** Priority of patient */
	private int priority;
	/** Highest assignable priority */
	private static final int TOP_PRIORITY = 1;
	/** Lowest assignable priority */
	private static final int LOW_PRIORITY = 5;
	
	/**
	 * Constructor for Patient
	 * @param name Name of patient
	 * @param priority Priority of patient
	 */
	public Patient(String name, int priority) {
		this.name = name.trim();
		if (priority > 5) {
			this.priority = 5;
		} else if (priority < 1) {
			this.priority = 1;
		} else {
			this.priority = priority;
		}
	}
	
	/**
	 * Allows patient to pick an examination room
	 * @param w List of examination rooms
	 * @throws NoAvailableRoomException 
	 */
	public abstract void pickRoom(Ward w) throws NoAvailableRoomException;
	
	/**
	 * Checks whether a patient's name matches the entered filter
	 * @param filter String entered by user
	 * @return True of patient's name matches filter
	 */
	public boolean meetsFilter(String filter) {
		// true if filter is null or empty

		if (filter == null) {
			return true;
		}
		// compare name to filter
		if (this.getName().substring(0, filter.length()).equalsIgnoreCase(filter)) {
			return true;
		}
		return false;

	}
	
	/**
	 * Overrides toString for Patient class
	 * @return String representation of Patient
	 */
	public abstract String toString();
	
	/**
	 * Getter for patient name
	 * @return Patient name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for patient priority
	 * @return Patient priority
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Setter for patient priority
	 * @param priority New priority to assign to patient
	 */
	public void setPriority(int priority) {
		if (priority >= TOP_PRIORITY && priority <= LOW_PRIORITY) {
			this.priority = priority;
		} else if (priority < TOP_PRIORITY) {
			this.priority = 1;
		} else if (priority > LOW_PRIORITY) {
			this.priority = 5;
		}
	}
	
	/**
	 * Compares the priority of two patients
	 * @param p Patient to compare priority to
	 * @return Difference between priorities
	 */
	public int compareToPriority(Prioritized p) {
		return p.getPriority() - this.getPriority();
	}

}
