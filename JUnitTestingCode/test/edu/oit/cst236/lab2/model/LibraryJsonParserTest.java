package edu.oit.cst236.lab2.model;

import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.oit.cst236.lab2.model.ILibrary;
import edu.oit.cst236.lab2.parser.ParseException;
import edu.oit.cst236.lab2.parser.json.LibraryJsonParser;

public class LibraryJsonParserTest {
	private LibraryJsonParser sut;
	
	@Before
	public void setup() {
		sut = new LibraryJsonParser();
	}
	
	@Test 
	public void ParseLibaryTest() throws ParseException {
		String goodLibrary = "{ 'id' : '123', 'name' : 'Test Library' }";
		ILibrary parsedLibrary = sut.parseLibrary(goodLibrary);
		Assert.assertNotNull(parsedLibrary);
		Assert.assertEquals("123", parsedLibrary.getId());
		Assert.assertEquals("Test Library", parsedLibrary.getName());
	}
	
	@Test(expected = ParseException.class)
	public void testParseLibrary_MissingParameters() throws ParseException {
		String goodLibraryBadParams = "{ }";
		ILibrary parsedLibrary = sut.parseLibrary(goodLibraryBadParams);
		Assert.assertNotNull(parsedLibrary);
		Assert.assertNotEquals(null, parsedLibrary.getId());
		Assert.assertNotEquals(null, parsedLibrary.getName());
	}
	
	@Test(expected = ParseException.class)
	public void testParseArrayBook_InvalidJson() throws ParseException {
		String badLibraryJson = ">crap<";
		sut.parseLibrary(badLibraryJson);
	}
	
	@Test
	public void testListParseLibrary() throws ParseException {
		String goodLibraries = "[ { 'id' : '123',	'name' : 'Test Library0'},"
						   	   + "{ 'id' : '1234', 	'name' : 'Test Library1'},"
						   	   + "{ 'id' : '12345', 'name' : 'Test Library2'} ]";
		List<ILibrary> parsedLibraries = sut.parseLibraries(goodLibraries);
		
		String testIDs[] = {"123", "1234", "12345"};
		String testTitles[] = {"Test Library0", "Test Library1", "Test Library2"};
		
		for(int i = 0; i < parsedLibraries.size(); ++i) {
			Assert.assertNotNull(parsedLibraries.get(i));
			Assert.assertEquals(testIDs[i], parsedLibraries.get(i).getId());
			Assert.assertEquals(testTitles[i], parsedLibraries.get(i).getName());
		}
	}
	
	@Test(expected = ParseException.class)
	public void testListParseLibrary_MissingParameters() throws ParseException {
		String goodLibrariesBadParams = "[ { 'id' : '123',	'name' : 'Test Library0'},"
				   				   		+ "{ },"
				   				   		+ "{ 'id' : '12345', 'name' : 'Test Library2'} ]";
		List<ILibrary> parsedLibraries = sut.parseLibraries(goodLibrariesBadParams);
		
		for(int i = 0; i < parsedLibraries.size(); ++i) {
			Assert.assertNotNull(parsedLibraries.get(i));
			Assert.assertNotEquals(null, parsedLibraries.get(i).getId());
			Assert.assertNotEquals(null, parsedLibraries.get(i).getName());
		}
	}
	
	@Test(expected = JSONException.class)
	public void testListParseArrayBook_InvalidJson() throws JSONException, ParseException {		
		String badLibriesJson = "[ { 'id' : '123',	'title' : 'Test Library0'},"
				   			  + "{ >crap< },"
				   			  + "{ 'id' : '12345', 'title' : 'Test Library2'} ]";

		sut.parseLibraries(badLibriesJson);
	}

}
