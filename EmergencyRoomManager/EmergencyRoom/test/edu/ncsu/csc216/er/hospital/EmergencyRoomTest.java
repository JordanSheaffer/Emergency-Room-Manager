package edu.ncsu.csc216.er.hospital;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.er.room.Room;

/**
 * Test class for EmergencyRoom
 * @author Josh Stetson
 */
public class EmergencyRoomTest {

	/** EmergencyRoom object */
	private EmergencyRoom er;
	
	/**
	 * Setup method for edu.ncsu.csc216.er.hospital.EmergencyRoom
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Room.setRoomNumberingTo101();
		er = new EmergencyRoom();
	}

	/**
	 * Test method for edu.ncsu.csc216.er.hosptial.EmergencyRoom.EmergencyRoomScanner
	 */
	@Test
	public final void testEmergencyRoomScanner() {
		File file = new File("patients.txt");
		Scanner fileScanner;
		EmergencyRoom erFromFile = null;
		try {
			fileScanner = new Scanner(file);
			erFromFile = new EmergencyRoom(fileScanner);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] list = erFromFile.printPatients("").split("\n");
		assertEquals("A 1 Doe, John", list[0]);
	

	}

	/**
	 * Test method for edu.ncsu.csc216.er.hosptial.EmergencyRoom.reassignPriority
	 */
	@Test
	public final void testReassignPriority() {
		er.admit("Child", 1, "Child1");
		er.admit("Adult", 3, "Adult1");
		er.admit("Adult", 4, "Adult2");
		
		String patientOne = "P 1 Child1";
		String patientTwo = "A 3 Adult1";
		String patientThree = "A 4 Adult2";
		String list = patientOne + "\n" + patientTwo + "\n" + patientThree + "\n";
		
		assertEquals(list, er.printPatients(""));
		
		er.reassignPriority("", 0, 3);
		patientOne = "P 3 Child1";
		list = patientTwo + "\n" + patientOne + "\n" + patientThree + "\n";
		
		assertEquals(list, er.printPatients(""));
	}

	/**
	 * Test method for edu.ncsu.csc216.er.hosptial.EmergencyRoom.fillRooms
	 */
	@Test
	public final void testFillRooms() {
		er.admit("Child", 1, "Child1");
		er.admit("Adult", 3, "Adult1");
		er.admit("Adult", 4, "Adult2");
		er.admit("Adult", 4, "Adult1");
		er.admit("Adult", 4, "Adult1");
		er.admit("Adult", 4, "Adult1");
		
		er.fillRooms();
	
		er.admit("Adult", 4, "Adult1");
		
		er.fillRooms();
		
		String[] ward = er.printWard().split("\n");
		
		//assertEquals("", ER.printPatients(""));
		
		assertEquals("P07: Child1", ward[7]);
		
		assertEquals("108: Adult1", ward[0]);
		
		assertEquals("106: Adult2", ward[1]);
	}

	/**
	 * Test method for edu.ncsu.csc216.er.hosptial.EmergencyRoom.remove
	 */
	@Test
	public final void testRemove() {
		er.admit("Child", 1, "Child1");
		String[] list = er.printPatients("").split("\n");
		assertEquals("P 1 Child1", list[0]);
		er.remove("", 0);
		list = er.printPatients("").split("\n");
		assertEquals("", list[0]);
	}

	/**
	 * Test method for edu.ncsu.csc216.er.hosptial.EmergencyRoom.releaseFromRoom
	 */
	@Test
	public final void testReleaseFromRoom() {
		er.admit("Child", 1, "Child1");
		er.admit("Adult", 3, "Adult1");
		er.admit("Adult", 4, "Adult2");
		er.admit("Adult", 4, "Adult1");
		er.admit("Adult", 4, "Adult1");
		er.admit("Adult", 4, "Adult1");
		
		er.fillRooms();
		
		String[] ward = er.printWard().split("\n");
		assertEquals("P07: Child1", ward[7]);
		er.releaseFromRoom(7);
		ward = er.printWard().split("\n");
		assertEquals("P07: EMPTY", ward[7]);
	}

	/**
	 * Test method for edu.ncsu.csc216.er.hosptial.EmergencyRoom.addNewRoom
	 */
	@Test
	public final void testAddNewRoom() {
		String[] ward = er.printWard().split("\n");
		assertEquals(8, ward.length);
		er.addNewRoom();
		ward = er.printWard().split("\n");
		assertEquals(9, ward.length);
	}


}
