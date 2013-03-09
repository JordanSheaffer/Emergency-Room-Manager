package edu.ncsu.csc216.er.hospital;

import java.util.Scanner;

import edu.ncsu.csc216.er.patient.AdultPatient;
import edu.ncsu.csc216.er.patient.Patient;
import edu.ncsu.csc216.er.patient.PatientList;
import edu.ncsu.csc216.er.patient.PediatricPatient;
import edu.ncsu.csc216.er.room.NoAvailableRoomException;
import edu.ncsu.csc216.er.room.Ward;
import edu.ncsu.csc216.er.util.SimpleIterator;

/**
 * Manages the operations of an emergency room.  Responsible for 
 * handling patient waiting list, exam room list, and admitting/removing
 * patients from both lists.
 * 
 * @author Josh Stetson
 *
 */
public class EmergencyRoom implements HospitalAdmissionsManager {
	
	/** List of patients in waiting room */
	private PatientList waitingRoom;
	/** List of examination rooms */
	private Ward ward;
	
	/** 
	 * Null constructor for EmergencyRoom
	 * Creates a new PatientList and Ward
	 */
	public EmergencyRoom() {
		waitingRoom = new PatientList();
		ward = new Ward();
	}
	
	/**
	 * Constructor for EmergencyRoom
	 * Loads a patient list from a file
	 * @param s Scanner for file
	 */
	public EmergencyRoom(Scanner s) {
		waitingRoom = new PatientList(s);
		ward = new Ward();
	}
	
	/**
	 * Reassigns the priority of a patient in the waiting room
	 * @param filter Filters the patient list based on string entered by user
	 * @param position Position of patient in list to be reassigned
	 * @param priority New priority to assign to patient
	 */
	public void reassignPriority(String filter, int position, int priority) {
		waitingRoom.changePriority(filter,  position,  priority);
	}
	
	/**
	 * Fills empty examination rooms with patients from waiting room
	 */
	public void fillRooms() {

		SimpleIterator<Patient> iter = waitingRoom.iterator();
		int count = 0;
		while (iter.hasNext()) {
			Patient p = iter.next();
			try {
				p.pickRoom(ward);
				waitingRoom.remove(p.getName(), count);
			} catch (NoAvailableRoomException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				count++;
			}
		}
	}
	
	/**
	 * Removes a patient from the waiting list
	 * @param filter Filters the patient list based on string entered by user
	 * @param position Position of patient in list to be removed
	 * @return Patient removed from the waiting list
	 */
	public Patient remove(String filter, int position) {
		return waitingRoom.remove(filter, position);
	}
	
	/**
	 * Removes a patient from an examination room
	 * @param roomNumber Number of room for patient to be released
	 * @return Patient removed from the room
	 */
	public Patient releaseFromRoom(int roomNumber) {
		return ward.release(roomNumber);
	}
	
	/**
	 * Adds a new examination room to the list (max of 30)
	 */
	public void addNewRoom() {
		ward.addRoom();
	}
	
	/**
	 * Generates a list of patients matching the string entered by user
	 * @param filter Filters the patient list based on string entered by user
	 * @return List of patients that match the entered filter
	 */
	public String printPatients(String filter) {
		return waitingRoom.filteredList(filter);
	}
	
	/**
	 * Generates a list of examination rooms with their status
	 * @return List of examination rooms
	 */
	public String printWard() {
		String result = "";
		for (int i = 0; i < ward.getSize(); i++) {
			result += ward.getRoomAt(i).toString() + "\n";
		}
		return result;
	}
	
	/**
	 * Admits a patient to an examination room and removes them from
	 * the waiting list
	 * @param kind Kind of patient admitted (pediatric/adult)
	 * @param priority Priority of patient admitted
	 * @param name Name of patient admitted
	 */
	public void admit(String kind, int priority, String name) {
		
		if (kind.equals("Child")) {
			waitingRoom.add(new PediatricPatient(name, priority));
		} else {
			waitingRoom.add(new AdultPatient(name, priority));
		}

	}	

}
