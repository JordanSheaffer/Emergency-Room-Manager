package edu.ncsu.csc216.er.room;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.er.patient.AdultPatient;
import edu.ncsu.csc216.er.patient.Patient;
import edu.ncsu.csc216.er.patient.PediatricPatient;

/**
 * Test class for PediatricRoom
 * @author Josh Stetson
 */
public class PediatricRoomTest {
	
	private Room roomTwo;
	private Patient adult;
	private Patient pediatric;

	/**
	 * Setup method for edu.ncsu.csc216.er.room.PediatricRoomTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		roomTwo = new PediatricRoom();
		adult = new AdultPatient("Adult", 5);
		pediatric = new PediatricPatient("Child", 1);
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.PediatricRoom.occupy
	 */
	@Test
	public final void testOccupy() {		
		
		try {
			roomTwo.occupy(adult);
			fail("occupy(adult) should throw RoomPatientMismatchException");
		} catch (Exception e) {
			// occupy(adult) should generate an exception
		}
		assertFalse(roomTwo.isOccupied());
		try {
			roomTwo.occupy(pediatric);
		} catch (Exception e) {
			// This should not throw an exception
		}
		try {
			roomTwo.occupy(pediatric);
			fail("occupy(pediatric) should throw a RoomOccupiedException");
		} catch (Exception e) {
			// occupy(pediatric) should generate an exception
		}
		assertTrue(roomTwo.isOccupied());
	}
}
