package edu.ncsu.csc216.er.patient;

/**
 * Describes behaviors of objects that have priorities
 * 
 * @author Josh Stetson
 *
 */
public interface Prioritized {
	
	/**
	 * Getter for patient priority
	 * @return Patient priority
	 */
	public int getPriority();
		
	/**
	 * Setter for patient priority
	 * @param priority New priority to assign to patient
	 */
	public void setPriority(int priority);
	
	/**
	 * Compares the priority of this patient to another patient.
	 * Returns a negative number if this priority is lower than another priority.
	 * Returns a positive number if this priority is higher than another priority.
	 * @param p Other patient to compare to this patient
	 * @return Difference between patient priorities
	 */
	public int compareToPriority(Prioritized p);

}
