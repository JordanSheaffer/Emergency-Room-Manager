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
public class PediatricPatientTest {
	
	private Patient child;
	private Ward ward;

	/**
	 * Setup method for edu.ncsu.csc216.er.patient.PediatricPatientTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Room.setRoomNumberingTo101();
		child = new PediatricPatient("Child", 3);
		ward = new Ward();
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.PediatricPatient.pickRoom
	 */
	@Test
	public final void testPickRoom() {
		for (int i = 0; i < 5; i++) {
			try {
				child.pickRoom(ward);
			} catch (NoAvailableRoomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertTrue(ward.getRoomAt(ward.getSize() - 1 - i).isOccupied());
		}
		for (int i = 0; i < ward.getSize(); i++) {
			System.out.println(ward.getRoomAt(i).toString());
		}
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.PediatricPatient.toString
	 */
	@Test
	public final void testToString() {
		assertEquals("P 3 Child", child.toString());
	}

}
