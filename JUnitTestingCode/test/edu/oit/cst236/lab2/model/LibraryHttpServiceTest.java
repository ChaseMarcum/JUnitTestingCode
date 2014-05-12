package edu.oit.cst236.lab2.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.oit.cst236.lab2.lib.ConnectionException;
import edu.oit.cst236.lab2.lib.IWebClient;
import edu.oit.cst236.lab2.lib.InvalidRequestException;
import edu.oit.cst236.lab2.parser.IBookParser;
import edu.oit.cst236.lab2.parser.ILibraryParser;
import edu.oit.cst236.lab2.parser.ParseException;
import edu.oit.cst236.lab2.service.http.LibraryHttpService;

public class LibraryHttpServiceTest {
	private LibraryHttpService sut;
	private IWebClient mockClient;
	private IBookParser mockBookParser;
	private ILibraryParser mockLibraryParser;
	
	@Before
	public void setup() throws ConnectionException, ParseException {
		mockClient = Mockito.mock(IWebClient.class);
		mockLibraryParser = Mockito.mock(ILibraryParser.class);
		mockBookParser = Mockito.mock(IBookParser.class);
		sut = new LibraryHttpService(mockClient);
		sut.setBookParser(mockBookParser);
		sut.setLibraryParser(mockLibraryParser);
	}
	
	@Test
	public void testSetBookParser_IllegalArgumentException() throws IllegalArgumentException{
		sut.setBookParser(null);
	}
	
	@Test
	public void testSetLibraryParser_IllegalArgumentException() throws IllegalArgumentException{
		sut.setLibraryParser(null);
	}
	
	@Test
	public void testClientSetter_WithGetter() throws ConnectionException
	{
		class LibraryHttpServiceExtra extends LibraryHttpService
		{			
			public LibraryHttpServiceExtra(IWebClient client) throws ConnectionException { mockClient = client; }
		}				 
		LibraryHttpServiceExtra newLibService = new LibraryHttpServiceExtra(mockClient);
		IWebClient anotherClient = Mockito.mock(IWebClient.class);		
		newLibService.setWebClient(anotherClient);
	}
	
	@Test
	public void testGetLibrary() throws InvalidRequestException, ConnectionException, ParseException {
		ILibrary mockLibrary = Mockito.mock(ILibrary.class);
		Mockito.when(mockClient.query("http://www.fake.com/library/123")).thenReturn("goodjson");
		Mockito.when(mockLibraryParser.parseLibrary("goodjson")).thenReturn(mockLibrary);
		
		ILibrary library = sut.getLibrary("123");
		Assert.assertEquals(mockLibrary, library);
	}
	
	@Test
	public void testGetLibrary_ParseException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query("http://www.fake.com/library/123")).thenReturn("badjson");
		Mockito.when(mockLibraryParser.parseLibrary(Mockito.anyString())).thenThrow(new ParseException());
		
		Assert.assertNull(sut.getLibrary("123"));
	}
	
	@Test
	public void testGetLibrary_ConnectionException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query(Mockito.anyString())).thenThrow(new ConnectionException());
		
		Assert.assertNull(sut.getLibrary("123"));
	}
	
	@Test
	public void testGetLibrary_InvalidRequestException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query(Mockito.anyString())).thenThrow(new InvalidRequestException());

		Assert.assertNull(sut.getLibrary("BadRequest"));
	}
	
	@Test
	public void testGetLibraryBook() throws InvalidRequestException, ConnectionException, ParseException {
		IBook mockBook = Mockito.mock(IBook.class);
		Mockito.when(mockClient.query("http://www.fake.com/book/123")).thenReturn("goodjson");
		Mockito.when(mockBookParser.parseBook("goodjson")).thenReturn(mockBook);
		
		IBook book = sut.getLibraryBook("123");
		Assert.assertEquals(mockBook, book);
	}
	
	@Test
	public void testGetLibraryBook_ParseException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query("http://www.fake.com/book/123")).thenReturn("badjson");
		Mockito.when(mockBookParser.parseBook("badjson")).thenThrow(new ParseException());
		
		Assert.assertNull(sut.getLibraryBook("123"));
	}
	
	@Test
	public void testGetLibraryBook_ConnectionException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query(Mockito.anyString())).thenThrow(new ConnectionException());
		
		Assert.assertNull(sut.getLibraryBook("123"));
	}
	
	@Test
	public void testGetLibraryBook_InvalidRequestException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query(Mockito.anyString())).thenThrow(new InvalidRequestException());
		
		Assert.assertNull(sut.getLibraryBook("Invalid"));
	}
	
	@Test
	public void testGetLibraries() throws InvalidRequestException, ConnectionException, ParseException {
		List<ILibrary> mockLibraries = Mockito.anyListOf(ILibrary.class);

		Mockito.when(mockClient.query("http://www.fake.com/libraries")).thenReturn("goodjson");
		Mockito.when(mockLibraryParser.parseLibraries("goodjson")).thenReturn((List<ILibrary>) mockLibraries);
		
		List<ILibrary> libraries = sut.getLibraries();
		
		Assert.assertEquals(mockLibraries, libraries);
	}
	
	@Test
	public void testGetLibraries_ParseException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query("http://www.fake.com/libraries")).thenReturn("badjson");
		Mockito.when(mockLibraryParser.parseLibraries("badjson")).thenThrow(new ParseException());
		
		Assert.assertNotNull(sut.getLibraries());
	}
	
	@Test
	public void testGetLibraries_ConnectionException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query(Mockito.anyString())).thenThrow(new ConnectionException());
		
		Assert.assertNotNull(sut.getLibraries());
	}
	
	@Test
	public void testGetLibraries_InvalidRequestException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query(Mockito.anyString())).thenThrow(new InvalidRequestException());
		
		Assert.assertNotNull(sut.getLibraries());
	}
	
	@Test
	public void testGetLibraryBooks() throws InvalidRequestException, ConnectionException, ParseException {
		List<IBook> mockBooks = Mockito.anyListOf(IBook.class);

		Mockito.when(mockClient.query("http://www.fake.com/books")).thenReturn("goodjson");
		Mockito.when(mockBookParser.parseBooks("goodjson")).thenReturn((List<IBook>) mockBooks);
		
		List<IBook> books = sut.getLibraryBooks("123");
		Assert.assertEquals(mockBooks, books);
	}
	
	@Test
	public void testGetLibraryBooks_ParseException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query(Mockito.anyString())).thenReturn("badjson");
		Mockito.when(mockBookParser.parseBooks(Mockito.anyString())).thenThrow(new ParseException());
		
		Assert.assertNotNull(sut.getLibraryBooks("123"));
	}
	
	@Test
	public void testGetLibraryBooks_ConnectionException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query(Mockito.anyString())).thenThrow(new ConnectionException());
		
		Assert.assertNotNull(sut.getLibraryBooks("123"));
	}
	
	@Test
	public void testGetLibraryBooks_InvalidRequestException() throws InvalidRequestException, ConnectionException, ParseException {
		Mockito.when(mockClient.query(Mockito.anyString())).thenThrow(new InvalidRequestException());
		
		Assert.assertNotNull(sut.getLibraryBooks("123"));
	}
	
}
