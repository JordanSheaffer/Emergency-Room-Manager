package edu.ncsu.csc216.er.patient;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.er.room.NoAvailableRoomException;
import edu.ncsu.csc216.er.room.Room;
import edu.ncsu.csc216.er.room.Ward;

/**
 * Test class for AdultPatient
 * @author Josh Stetson
 */
public class AdultPatientTest {
	
	/** AdultPatient */
	private Patient adult;
	/** Ward object */
	private Ward ward;

	/**
	 * Setup method for edu.ncsu.csc216.er.patient.AdultPatientTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Room.setRoomNumberingTo101();
		adult = new AdultPatient("Adult", 3);
		ward = new Ward();
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.AdultPatient.pickRoom
	 */
	@Test
	public final void testPickRoom() {
		for (int i = 0; i < 6; i++) {
			try {
				adult.pickRoom(ward);
			} catch (NoAvailableRoomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 5; i++) {
			assertTrue(ward.getRoomAt(i).isOccupied());
		}
		for (int i = 0; i < ward.getSize(); i++) {
			System.out.println(ward.getRoomAt(i).toString());
		}
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.AdultPatient.toString
	 */
	@Test
	public final void testToString() {
		assertEquals("A 3 Adult", adult.toString());
	}
	
}
