package edu.ncsu.csc216.er.hospital;

import edu.ncsu.csc216.er.patient.Patient;

/**
 * Operations that must be supported to manage a hospital ward. The manager
 *    is responsible for handling the rooms as well as the patients who
 *    have been admitted but are still waiting for rooms.
 * @author Jo Perry
 *
 */
public interface HospitalAdmissionsManager {
	
	
	/**
	 * Puts a new patient on the list of patients waiting for a room in the 
	 *    hospital ward.
	 * @param kind kind of patient
	 * @param priority priority of patient
	 * @param name name of patient
	 */
	public void admit(String kind, int priority, String name);
	
	/**
	 * Changes the priority on a patient who has already been admitted but is 
	 *   awaiting a room. The patient to change is determined by the position of the
	 *   patient in the list of all waiting patients who meet the given filter.
	 * @param filter  Patients meeting this filter are subject to priority change
	 * @param position  Position in the filtered list of the patient to be changed
	 * @param newPriority New value for the priority of the patient to be changed
	 */
	public void reassignPriority(String filter, int position, int newPriority);
	
	/**
	 * Removes the patient from the waiting list of patients meeting the given filter.
	 * @param filter  determines which patients are considered for removal
	 * @param position  Position in the filtered list of the patient to be removed
	 * @return  The patient who was removed, or null if nobody was removed
	 */
	public Patient remove(String filter, int position);
	
	/**
	 * Fills the beds in the ward with patients who have been admitted but
	 *   who are still waiting for a room.
	 */
	public void fillRooms();
	
	/**
	 * Releases the patient in the given room from the ward.
	 * @param room Room of the patient to be released.
	 * @return  Patient who was released, or null if the room was empty.
	 */
	public Patient releaseFromRoom(int room);
	
	/**
	 * Adds a new room to the ward.
	 */
	public void addNewRoom();
	
	/**
	 * A string representation of the list of patients in the waiting room who meet 
	 *    the given filter.
	 * @param filter determines which patients are represented on the return value.
	 * @return String representation of the patients who meet the filter
	 */
	public String printPatients(String filter);
	
	/**
	 * Gets information about each room in the ward.
	 * @return String representation about room information
	 */
	public String printWard();
}