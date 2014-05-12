/**
 * 
 */
package edu.oit.cst236.lab2.model;

import org.junit.Assert;
import org.junit.Test;

import edu.oit.cst236.lab2.model.core.Book;

/**
 * Unit test class for a Book POJO that follows the IBook interface.
 * 
 * @author Chase Marcum
 *
 */
public class BookTest {
	private Book sut;
	
	@Test
	public void testGetIDHappy() {
		sut = new Book("testNumber","test");
		Assert.assertEquals("Library ID should be equal", "testNumber", sut.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetIDNull() {
		sut = new Book(null,null);
		Assert.assertNotNull("Library ID should not be null", sut.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetIdEmpty() {
		sut = new Book("","");
		Assert.assertNotEquals("Book ID should not be empty", "", sut.getId());
	}
	
	@Test
	public void testGetTitleHappy() {
		sut = new Book("testNumber","test");
		Assert.assertEquals("test", sut.getTitle());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetTitleNull() {
		sut = new Book(null,null);
		Assert.assertNotNull(sut.getTitle());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetTitleEmpty() {
		sut = new Book("","");
		Assert.assertNotEquals("Book Title should not be empty", "", sut.getTitle());
	}
	
	@Test
	public void testGetDescriptionHappy() {
		sut = new Book("testNumber","test");
		sut.setDescription("test");
		Assert.assertEquals("test", sut.getDescription());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDescriptionNull() {
		sut = new Book("testNumber","test");
		sut.setDescription(null);
		Assert.assertNotNull(sut.getDescription());
	}
	
	@Test
	public void testGetDescriptionEmpty() {
		sut = new Book("testNumber","test");
		sut.setDescription("");
		Assert.assertEquals("Book Description should be empty", "", sut.getDescription());
	}
	
	@Test
	public void testSetDescriptionHappy() {
		sut = new Book("testNumber","test");
		sut.setDescription("test");
		Assert.assertEquals("test", sut.getDescription());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetDescriptionNull() throws IllegalArgumentException {
		sut = new Book("testNumber","test");
		sut.setDescription(null);
	}
	
	@Test
	public void testSetDescriptionEmpty() {
		sut = new Book("testNumber","test");
		sut.setDescription("");
		Assert.assertEquals("Book Description should be empty", "", sut.getDescription());
	}
	
	@Test
	public void testBookHappy() {
		sut = new Book("testNumber","test");
		Assert.assertEquals("testNumber", sut.getId());
		Assert.assertEquals("test", sut.getTitle());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBookNull() {
		sut = new Book(null,null);
		Assert.assertNotNull(sut.getId());
		Assert.assertNotNull(sut.getTitle());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBookEmpty() {
		sut = new Book("","");
		Assert.assertNotEquals("Book ID should not be empty", "", sut.getId());
		Assert.assertNotEquals("Book Title should not be empty", "", sut.getTitle());
	}

}
