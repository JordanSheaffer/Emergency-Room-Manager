package edu.ncsu.csc216.er.patient;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.er.util.SimpleIterator;

/**
 * Test class for PatientList
 * @author Josh Stetson
 */
public class PatientListTest {
	
	private PatientList list;
	private Patient adult1;
	private Patient adult2;
	private Patient adult3;
	private Patient child1;
	private Patient child2;
	private Patient child3;
	File file;
	Scanner fileScanner;
	
	/**
	 * Setup method for edu.ncsu.csc216.er.patient.PatientListTest
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new PatientList();
		adult1 = new AdultPatient("Adult1", 3);
		adult2 = new AdultPatient("Adult2", 4);
		adult3 = new AdultPatient("Adult3", 5);
		child1 = new PediatricPatient("Child1", 1);
		child2 = new PediatricPatient("Child2", 3);
		child3 = new PediatricPatient("Child3", 5);
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.PatientList.PatientListScanner
	 */
	@Test
	public final void testPatientListScanner() {
		file = new File("patients.txt");
		PatientList loadList = null;
		try {
			fileScanner = new Scanner(file);
			loadList = new PatientList(fileScanner);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(loadList.filteredList(""));
		//System.out.println();
		assertEquals("Doe, John", loadList.remove("", 0).getName());
		//System.out.println(loadList.filteredList(""));
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.PatientList.remove
	 */
	@Test
	public final void testRemove() {
		list.add(adult1);
		list.add(adult2);
		list.add(adult3);
		list.add(child1);
		list.add(child2);
		list.add(child3);
		
		//System.out.println(list.filteredList(""));
		//System.out.println();
		assertEquals("Child1", list.remove("c", 0).getName());
		//System.out.println(list.filteredList(""));
		assertEquals("Adult1", list.remove("a", 0).getName());
		//System.out.println(list.filteredList(""));
		assertEquals("Child2", list.remove("C", 0).getName());
		//System.out.println(list.filteredList(""));
		assertEquals("Adult2", list.remove("", 0).getName());
		//System.out.println(list.filteredList(""));
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.PatientList.add
	 */
	@Test
	public final void testAdd() {
		list.add(adult1);
		list.add(adult3);
		list.add(adult2);
		
		SimpleIterator<Patient> iter = list.iterator();
		int i = 1;
		while (iter.hasNext()) {
			Patient p = iter.next();
			assertEquals("Adult" + i++, p.getName());
		}
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.PatientList.changePriority
	 */
	@Test
	public final void testChangePriority() {
		list.add(adult1);
		list.add(adult2);
		list.add(adult3);
		list.add(child1);
		list.add(child2);
		list.add(child3);
		
		//System.out.println(list.filteredList(""));
		
		list.changePriority("", 5, 1);
		
		//System.out.println(list.filteredList(""));
		
		list.changePriority("", 0, 5);
		
		//System.out.println(list.filteredList(""));
		
		String newList = list.filteredList("");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(newList);
		String name = "";
		
		while (reader.hasNextLine()) {
			@SuppressWarnings("resource")
			Scanner lineReader = new Scanner(reader.nextLine());
			lineReader.next();
			lineReader.next();
			name = lineReader.next();
		}
		assertEquals("Child1", name);
		
		
	}

	/**
	 * Test method for edu.ncsu.csc216.er.patient.PatientList.filteredList
	 */
	@Test
	public final void testFilteredList() {
		list.add(adult1);
		list.add(adult2);
		list.add(adult3);
		list.add(child1);
		list.add(child2);
		list.add(child3);
		
		String newList = list.filteredList("a");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(newList);
		
		while (reader.hasNextLine()) {
			@SuppressWarnings("resource")
			Scanner lineReader = new Scanner(reader.nextLine());
			lineReader.next();
			lineReader.next();
			String name = lineReader.next();
			assertTrue(name.startsWith("A"));
		}
	}

}
