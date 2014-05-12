package edu.oit.cst236.lab2.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.json.JSONException;

import edu.oit.cst236.lab2.model.IBook;
import edu.oit.cst236.lab2.parser.ParseException;
import edu.oit.cst236.lab2.parser.json.BookJsonParser;

public class BookJsonParserTest {
	private BookJsonParser sut;
	
	@Before
	public void setup() {
		sut = new BookJsonParser();
	}

	@Test
	public void testParseBook() throws ParseException {
		String goodBook = "{ 'id' : '123', 'title' : 'Test Book' }";
		IBook parsedBook = sut.parseBook(goodBook);
		Assert.assertNotNull(parsedBook);
		Assert.assertEquals("123", parsedBook.getId());
		Assert.assertEquals("Test Book", parsedBook.getTitle());
	}

	@Test(expected = ParseException.class)
	public void testParseBook_MissingParameters() throws ParseException {
		String goodBookBadParams = "{ }";
		IBook parsedBook = sut.parseBook(goodBookBadParams);
		Assert.assertNotNull(parsedBook);
		Assert.assertNotEquals(null, parsedBook.getId());
		Assert.assertNotEquals(null, parsedBook.getTitle());
	}
	
	@Test(expected = ParseException.class)
	public void testParseArrayBook_InvalidJson() throws ParseException {
		String badBookJson = ">crap<";
		sut.parseBook(badBookJson);
	}
	
	@Test
	public void testListParseBook() throws ParseException {
		String goodBooks = "[ { 'id' : '123',	'title' : 'Test Book0'},"
						   + "{ 'id' : '1234', 	'title' : 'Test Book1'},"
						   + "{ 'id' : '12345', 'title' : 'Test Book2'} ]";
		List<IBook> parsedBooks = sut.parseBooks(goodBooks);
		
		String testIDs[] = {"123", "1234", "12345"};
		String testTitles[] = {"Test Book0", "Test Book1", "Test Book2"};
		
		for(int i = 0; i < parsedBooks.size(); ++i) {
			Assert.assertNotNull(parsedBooks.get(i));
			Assert.assertEquals(testIDs[i], parsedBooks.get(i).getId());
			Assert.assertEquals(testTitles[i], parsedBooks.get(i).getTitle());
		}
	}
	
	@Test(expected = ParseException.class)
	public void testListParseBook_MissingParameters() throws ParseException {
		String goodBookBadParams = "[ { 'id' : '123',	'title' : 'Test Book0'},"
				   				   + "{ },"
				   				   + "{ 'id' : '12345', 'title' : 'Test Book2'} ]";
		List<IBook> parsedBooks = sut.parseBooks(goodBookBadParams);
		
		for(int i = 0; i < parsedBooks.size(); ++i) {
			Assert.assertNotNull(parsedBooks.get(i));
			Assert.assertNotEquals(null, parsedBooks.get(i).getId());
			Assert.assertNotEquals(null, parsedBooks.get(i).getTitle());
		}
	}
	
	@Test(expected = JSONException.class)
	public void testListParseArrayBook_InvalidJson() throws JSONException, ParseException {		
		String badBooksJson = "[ { 'id' : '123',	'title' : 'Test Book0'},"
				   			  + "{ >badJSON< },"
				   			  + "{ 'id' : '12345', 'title' : 'Test Book2'} ]";

		sut.parseBooks(badBooksJson);
	}
}
