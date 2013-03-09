package edu.ncsu.csc216.er.patient;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Patient
 * @author Josh Stetson
 */
public class PatientTest {
	
	/** AdultPatient */
	private Patient adult;
	/** PediatricPatient */
	private Patient child;
	/** PediatricPatient */
	private Patient childTwo;

	/**
	 * Setup method for edu.ncsu.csc216.er.patient.PatientTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		adult = new AdultPatient("Adult", 5);
		child = new PediatricPatient("Child", 1);
		childTwo = new PediatricPatient("Child", 3);
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.Patient.meetsFilter
	 */
	@Test
	public final void testMeetsFilter() {
		assertTrue(child.meetsFilter("Chi"));
		assertTrue(child.meetsFilter("chi"));
		assertTrue(adult.meetsFilter(null));
		assertTrue(adult.meetsFilter(""));
		assertTrue(adult.meetsFilter("Adult"));
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.Patient.setPriority
	 */
	@Test
	public final void testSetPriority() {
		assertEquals(3, childTwo.getPriority());
		childTwo.setPriority(5);
		assertEquals(5, childTwo.getPriority());
		childTwo.setPriority(8);
		assertEquals(5, childTwo.getPriority());
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.Patient.compareToPriority
	 */
	@Test
	public final void testCompareToPriority() {
		assertEquals(2, child.compareToPriority(childTwo));
		assertEquals(-2, childTwo.compareToPriority(child));
		assertEquals(4, child.compareToPriority(adult));
		assertEquals(-4, adult.compareToPriority(child));
	}

}
