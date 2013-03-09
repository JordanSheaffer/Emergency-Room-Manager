package edu.ncsu.csc216.er.room;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.er.patient.Patient;
import edu.ncsu.csc216.er.patient.PediatricPatient;

/**
 * Test class for Ward
 * @author Josh Stetson
 */
public class WardTest {
	
	private Ward ward;
	private Patient child;

	/**
	 * Setup method for edu.ncsu.csc216.er.room.WardTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ward = new Ward();
		child = new PediatricPatient("Child", 1);
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Ward.addRoom
	 */
	@Test
	public final void testAddRoom() {
		assertEquals(8, ward.numberOfEmptyRooms());
		ward.addRoom();
		assertEquals(9, ward.numberOfEmptyRooms());
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Ward.numberOfEmptyRooms
	 */
	@Test
	public final void testNumberOfEmptyRooms() {
		assertEquals(8, ward.numberOfEmptyRooms());
		
		// add patient to a room
		try {
			ward.getRoomAt(1).occupy(child);
		} catch (Exception e) {
			// catches exception
		}
		assertEquals(7, ward.numberOfEmptyRooms());
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Ward.getRoomAt
	 */
	@Test
	public final void testGetRoomAt() {
		assertEquals('1', ward.getRoomAt(0).getRoomNumber().charAt(0));
		assertEquals('P', ward.getRoomAt(5).getRoomNumber().charAt(0));
		assertNull(ward.getRoomAt(10));
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Ward.getSize
	 */
	@Test
	public final void testGetSize() {
		assertEquals(8, ward.getSize());
	}

	/**
	 * Test method for edu.ncsu.csc216.er.room.Ward.release
	 */
	@Test
	public final void testRelease() {
		try {
			ward.getRoomAt(5).occupy(child);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(7, ward.numberOfEmptyRooms());
		Patient temp = ward.release(5);
		assertEquals(8, ward.getSize());
		assertEquals("Child", temp.getName());
		
		
	}

}
