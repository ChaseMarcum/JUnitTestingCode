package edu.oit.cst236.lab2.model;

import org.junit.Assert;
import org.junit.Test;

import edu.oit.cst236.lab2.model.core.Library;

/**
 * Unit test class for a Library POJO that follows the ILibrary interface
 * 
 * @author Chase Marcum
 *
 */
public class LibraryTest {
	private Library sut;

	@Test
	public void testLibraryHappy() {
		sut = new Library("testNumber","test");
		Assert.assertEquals("Library ID should be equal", "testNumber", sut.getId());
		Assert.assertEquals("Library Name should be equal", "test", sut.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLibraryNull() {
		sut = new Library(null,null);
		Assert.assertNotNull("Library ID should not be null", sut.getId());
		Assert.assertNotNull("Library Name should not be null", sut.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLibraryEmpty() {
		sut = new Library("","");
		Assert.assertNotEquals("Library ID should not be empty", "", sut.getId());
		Assert.assertNotEquals("Library Name should not be empty", "", sut.getName());
	}

	@Test
	public void testGetIdHappy() {
		sut = new Library("testNumber","test");
		Assert.assertEquals("Library ID should be equal", "testNumber", sut.getId());
	}
	
	public void testGetIdNull() {
		sut = new Library(null,null);
		Assert.assertNotNull("Library ID should not be null", sut.getId());
	}
	
	public void testGetIdEmpty() {
		sut = new Library("","");
		Assert.assertNotEquals("Library ID should not be empty", "", sut.getId());
	}

	@Test
	public void testSetNameHappy() {
		sut = new Library("testNumber","test");
		sut.setName("testSetNumber");
		Assert.assertEquals("Library Name should be equal", "testSetNumber", sut.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameNull() {
		sut = new Library("testNumber","test");
		sut.setName(null);
		Assert.assertNotNull("Library Name should not be null", sut.getName());
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameEmpty() {
		sut = new Library("testNumber","test");
		sut.setName("");
		Assert.assertNotEquals("Library Name should not be empty", "", sut.getName());
	}


	@Test
	public void testGetNameHappy() {
		sut = new Library("testNumber","test");
		Assert.assertEquals("Library Name should be equal", "test", sut.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetNameNull() {
		sut = new Library(null, null);
		Assert.assertNotNull("Library Name should not be null", sut.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetNameEmpty() {
		sut = new Library("","");
		Assert.assertNotEquals("Library Name should not be empty", "", sut.getName());
	}

	@Test
	public void testSetAvailableBooksHappy() {
		sut = new Library("testNumber","test");
		sut.setAvailableBooks(1);
		Assert.assertEquals("Library availableBooks should be equal", 1, sut.getAvailableBooks());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAvailableBooksNegitive() {
		sut = new Library("testNumber","test");
		sut.setAvailableBooks(-1);
		Assert.assertNotEquals("Library availableBooks should not be equal", -1, sut.getAvailableBooks());
	}

	@Test
	public void testGetAvailableBooksHappy() {
		sut = new Library("testNumber","test");
		sut.setAvailableBooks(1);
		Assert.assertEquals("Library availableBooks should be equal", 1, sut.getAvailableBooks());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetAvailableBooksNegitive() {
		sut = new Library("testNumber","test");
		sut.setAvailableBooks(-1);
		Assert.assertNotEquals("Library availableBooks should not be equal", -1, sut.getAvailableBooks());
	}

	@Test
	public void testSetUnavailableBooksHappy() {
		sut = new Library("testNumber","test");
		sut.setUnavailableBooks(1);
		Assert.assertEquals("Library unavailableBooks should be equal", 1, sut.getUnavailableBooks());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetUnavailableBooksNegitive() {
		sut = new Library("testNumber","test");
		sut.setUnavailableBooks(-1);
		Assert.assertNotEquals("Library unavailableBooks should not be equal", -1, sut.getAvailableBooks());
	}

	@Test
	public void testGetUnavailableBooksHappy() {
		sut = new Library("testNumber","test");
		sut.setUnavailableBooks(1);
		Assert.assertEquals("Library unavailableBooks should be equal", 1, sut.getUnavailableBooks());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetUnavailableBooksNegitive() {
		sut = new Library("testNumber","test");
		sut.setUnavailableBooks(-1);
		Assert.assertNotEquals("Library unavailableBooks should not be equal", -1, sut.getAvailableBooks());
	}

}
