package edu.ncsu.csc216.er.patient;

import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.er.util.SimpleIterator;

/**
 * Represents a list of patients in the ER waiting room.
 * Implements a linked list to manage the patient list.
 * 
 * @author Josh Stetson
 */
public class PatientList {
	
	/** Head of linked list */
	private Node head;
	
	/**
	 * PatientList constructor
	 * @param s Scanner from an input text file
	 */
	public PatientList(Scanner s) {
		head = null;
		String kind = "";
		int priority = 0;
		String  name = "";
		
		while (s.hasNextLine()) {
			kind = "";
			priority = 0;
			name = "";
			@SuppressWarnings("resource")
			Scanner lineScanner = new Scanner(s.nextLine());
			// check the format of the kind character
			if (lineScanner.hasNext()) {
				kind = lineScanner.next().toUpperCase();
				if (!kind.equals("A") && !kind.equals("P")) {
					continue;
				}
			} else {
				continue;
			}
			// check the format of the priority
			if (lineScanner.hasNextInt()) {
				priority = lineScanner.nextInt();
				if (priority > 5) {
					priority = 5;
				}
				if (priority < 1) {
					priority = 1;
				}
			} else {
				continue;
			}
			// check the format of the name
			if (lineScanner.hasNext()) {
				name = lineScanner.next();
				while (lineScanner.hasNext()) {
					name += " " + lineScanner.next();
				}
			} else {
				continue;
			}
			// if it makes it this far, create new patient
			// if kind = P, create pediatric patient
			if (kind.equals("P")) {
				Patient p = new PediatricPatient(name, priority);
				this.add(p);
			}
			if (kind.equals("A")) {
				Patient p = new AdultPatient(name, priority);
				this.add(p);
			}
		}
	}
	
	/**
	 * Null constructor for PatientList
	 */
	public PatientList() {
		head = null;
	}
	
	/**
	 * Implements a SimpleIterator to traverse the PatientList
	 * @return Iterator for traversing a linked list
	 */
	public SimpleIterator<Patient> iterator() {
		return new Cursor();
	}
	
	/**
	 * Removes a patient from the list 
	 * @param filter String to compare against name in list
	 * @param position Position of patient in filtered list
	 * @return Patient removed from the list
	 */
	public Patient remove(String filter, int position) {
		Node current = head;
		Node previous = null;
		Patient p = null;
		int index = 0;
		while (current != null && index <= position) {
			//if (current.data.meetsFilter(filter)) {
				index++;
			//}
			if (index > position) {
				break;
			}
			previous = current;
			current = current.link;
		}
		if (current != null) {
			p = current.data;
			if (current == head) {
				head = head.link;
			} else {
				previous.link = current.link;
			}
		}
		return p;
	}
	
	/**
	 * Adds a patient to the list based first on priority, then on waiting time.
	 * @param patient Patient to add to the list
	 */
	public void add(Patient patient) {
		Node current = head;
		Node previous = null;
		while (current != null && current.data.compareToPriority(patient) >= 0) {
			previous = current;
			current = current.link;
		}
		if (current == head) {
			head = new Node(patient, head);
		} else {
			previous.link = new Node(patient, current);
		}

	}
	
	/**
	 * Changes the priority of a patient in the list
	 * @param filter String to compare against name in list
	 * @param position Position of patient in filtered list
	 * @param priority New priority of selected patient
	 */
	public void changePriority(String filter, int position, int priority) {
		Patient oldPatient = this.remove(filter,  position);
		oldPatient.setPriority(priority);
		this.add(oldPatient);		
	}
	
	/**
	 * Generated a string representation of a list of patients
	 * matching the filter
	 * @param filter String to compare against name in list
	 * @return List of patients matching the filter
	 */
	public String filteredList(String filter) {
		Node current = head;
		String result = "";
		while (current != null) {
			Patient p = current.data;
			if (p.meetsFilter(filter)) {
				result += p.toString() + "\n";
			}
			current = current.link;
		}
		return result;
		
	}
	
	/**
	 * Node of a linked list
	 * @author Josh Stetson
	 *
	 */
	private class Node {
		public Patient data;
		public Node link;
		
		public Node(Patient data, Node link) {
			this.data = data;
			this.link = link;
		}
		
	}
	
	/**
	 * Utilizes a simple iterator interface to create a cursor that
	 * traverses a linked list
	 * @author Josh Stetson
	 */
	private class Cursor implements SimpleIterator<Patient> {
		
		private Node listCursor = head;

		public boolean hasNext() {
			return listCursor != null;
		}

		public Patient next() {
			if (listCursor == null)
				throw new NoSuchElementException();
			Patient p = listCursor.data;
			listCursor = listCursor.link;
			return p;
		}
		
	}

}
