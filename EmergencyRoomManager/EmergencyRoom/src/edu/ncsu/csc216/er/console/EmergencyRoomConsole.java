package edu.ncsu.csc216.er.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.er.hospital.EmergencyRoom;
import edu.ncsu.csc216.er.hospital.HospitalAdmissionsManager;

/**
 * Simple commmand-line interface for a hospital management system.
 * The file "patients.txt" can serve as a source of patient data.
 * @author Jo Perry
 *
 */
public class EmergencyRoomConsole {
	
	private HospitalAdmissionsManager er; // Emergency room backend	
	private String menu =  // Menu of choices
			"1) admit patient\n2) reassign priority\n3) remove patient\n4) fill ward\n" +
			"5) release from ward\n6) print patient list\n7) print ward\n8) add new room\n"
			+ "9) quit\n\nEnter Selection: ";
	private String filter = ""; // Restricts the patient display to only those whose names begin with the filter
	private Scanner s = new Scanner(System.in); // Scans user input

	/**
	 * Creates a new console with patients drawn from "patients.txt" and 8 rooms.
	 * If "patients.txt" is not available, the patient list is initially empty
	 */
	public EmergencyRoomConsole() {
		File f = new File("patients.txt");
		try {
			er = new EmergencyRoom(new Scanner(f));
		} catch (FileNotFoundException e) {
			System.err.println("EmergencyRoomConsole constructor: Starting with an empty file.");
			er = new EmergencyRoom();
		}
	}
	
	/**
	 * Prints the menu of user choices.
	 * @return the user's menu choice. Errors result in re-calling printMenu().
	 */
	public int printMenu() {
		System.out.print(menu);
		Scanner s1 = new Scanner(System.in);
		try {
			return s1.nextInt();	 
		}
		catch (Exception e)	{
			return printMenu();
		}
	}
	
	/**
	 * Displays the menu and carries out the user's menu choice:<br>
	 *   1. Admit new patient <br>
	 *   2. Change priority of patient<br>
	 *   3. Remove a patient from the waiting room <br>
	 *   4. Fill the ward examination rooms with patients in the waiting room <br>
	 *   5. Release a patient from the ward<br>
	 *   6. Print the patients in the waiting room <br>
	 *   7. Print the contents of the ward's examination rooms<br>
	 *   8. Add a new examination room<br>
	 *   9. Quit
	 */
	public void run() {	
		switch (printMenu()) {
		case 1: //	Admit a new patient to the waiting room:  public void admit(Patient p)
			System.out.print("\nADMIT PATIENT\n\nPatient name: ");
			String name = s.nextLine().trim();
			System.out.print("Patient priority: ");
			int priority = s.nextInt();
			System.out.print("Pediatric patient? <y/n>: ");
			String kind = s.next();
			if (kind.equalsIgnoreCase("y"))
				er.admit("Child", priority, name);
			else
				er.admit("Adult", priority, name);	
			break;
		case 2: //	Change the priority of a patient in the waiting room: public void reassignPriority(String filter, int position, int newPriority)
			System.out.print("\nREASSIGN PRIORITY\n\nAssign priority to patient a location on list (as most recently shown): ");
			int psn = s.nextInt();
			System.out.print("New priority: ");
			priority = s.nextInt();
			er.reassignPriority(filter, psn, priority);
			break;
		case 3: //	Remove a patient from the ER waiting room: public Patient remove(String filter, int position)
			System.out.print("\nREMOVE\n\nRemove patient at location on list (as most recently shown): ");
			psn = s.nextInt();
			er.remove(filter, psn);
			break;
		case 4: //	Fill the ward rooms with patients in the waiting room:  public void fillWard()
			    //  Anyone who enter a ward room also leave the waiting room.
			er.fillRooms();
			break;
		case 5: //	Release a patient from a ward room: public Patient releaseFromWard(int roomPosition)
			System.out.print("\nRELEASE\n\nRelease from room with index: ");
			psn = s.nextInt();
			er.releaseFromRoom(psn);
			break;
		case 6: //	Print the list of patients in the waiting room: public String printPatients(String filter)
			System.out.print("\nPRINT FILTERED PATIENT LIST\n\nEnter filter (return for no filter): ");
			filter = s.nextLine().trim();
			System.out.println("\n" + er.printPatients(filter));
			break;
		case 7: //	Print the contents of the ward (rooms, patients): public String printWard()
			System.out.println("\nPRINT ROOMS\n\n" + er.printWard());
			break;
		case 8: //  Add a new room to the ward: public void addNewRoom(); 
			er.addNewRoom();
			break;	
		case 9: // Exit the program
			System.exit(0);
			break;
		default:
			break;
		}
		run();
	}
	
	/**
	 * Main method to start program execution.
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("Use this simple application to do preliminary functional testing on the Emergency Room project.\n "
				+ "There is no no error checking.\n"
				+ "The cursor location varies according to the menu selection.\n\n");
		(new EmergencyRoomConsole()).run();
	}
	
}