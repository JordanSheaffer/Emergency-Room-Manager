package edu.ncsu.csc216.er.room;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.er.patient.AdultPatient;
import edu.ncsu.csc216.er.patient.Patient;

/**
 * Test class for Room
 * @author Josh Stetson
 */
public class RoomTest {
	
	private Room roomOne;
	private Room roomTwo;
	private Room roomThree;
	private Patient p;

	/**
	 * Setup method for edu.ncsu.csc216.er.room.RoomTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Room.setRoomNumberingTo101();
		roomOne = new Room();
		roomTwo = new Room("P");
		roomThree = new Room("P");
		p = new AdultPatient("Josh", 3);
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Room.setRoomNumberingTo101
	 */
	@Test
	public final void testSetRoomNumberingTo101() {
		assertEquals("101", roomOne.getRoomNumber());
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Room.getRoomNumber
	 */
	@Test
	public final void testGetRoomNumber() {
		assertEquals("101", roomOne.getRoomNumber());
		assertEquals("P02", roomTwo.getRoomNumber());
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Room.isOccupied
	 */
	@Test
	public final void testIsOccupied() {
		assertFalse(roomOne.isOccupied());
		try {
			roomOne.occupy(p);
		} catch (Exception e) {

			e.printStackTrace();
		}
		assertTrue(roomOne.isOccupied());
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Room.release
	 */
	@Test
	public final void testRelease() {
		try {
			roomOne.occupy(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(roomOne.isOccupied());
		Patient released = roomOne.release();
		assertFalse(roomOne.isOccupied());
		assertEquals("Josh", released.getName());
		assertEquals(3, released.getPriority());
		assertNull("Empty room should return null", roomTwo.release());
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Room.occupy
	 */
	@Test
	public final void testOccupy() {
		assertFalse(roomOne.isOccupied());
		try {
			roomOne.occupy(p);
		} catch (Exception e) {

			e.printStackTrace();
		}
		assertTrue(roomOne.isOccupied());
		try {
			roomOne.occupy(p);
			fail("occupy(p) should throw an exception");
		} catch (Exception e) {
			// test should catch exception
		}
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Room.toString
	 */
	@Test
	public final void testToString() {

		try {
			roomOne.occupy(p);
			roomTwo.occupy(p);
		} catch (Exception e) {
			// Test should not throw an exception here
		}

	
		assertEquals("101: Josh", roomOne.toString());
		assertEquals("P02: Josh", roomTwo.toString());
		assertEquals("P03: EMPTY", roomThree.toString());
	}

}
