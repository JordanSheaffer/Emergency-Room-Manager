package edu.ncsu.csc216.er.room;

import edu.ncsu.csc216.er.patient.Patient;

/**
 * Represents a pediatric room in the exam room list
 * 
 * @author Josh Stetson
 */
public class PediatricRoom extends Room {
	
	/**
	 * Null constructor for PediatricRoom
	 */
	public PediatricRoom() {
		super("P");
	}
	
	/**
	 * Overrides occupy method of Room class
	 * Assigns a pediatric patient to an exam room
	 * @param p Patient assigned to room
	 * @throws RoomOccupiedException 
	 * @throws RoomPatientMismatchException 
	 * @override Room.occupy
	 */
	public void occupy(Patient p) throws RoomOccupiedException, RoomPatientMismatchException  {
		char patientKind = p.toString().charAt(0);
		if (isOccupied()) {
			throw new RoomOccupiedException();
		} else if (patientKind != 'P') {
			throw new RoomPatientMismatchException();
		} else {
			super.occupy(p);
		}
		
	}

}
