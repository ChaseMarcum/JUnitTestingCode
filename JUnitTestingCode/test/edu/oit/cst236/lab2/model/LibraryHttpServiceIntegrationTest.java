package edu.oit.cst236.lab2.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import edu.oit.cst236.lab2.lib.ConnectionException;
import edu.oit.cst236.lab2.lib.IWebClient;
import edu.oit.cst236.lab2.lib.InvalidRequestException;
import edu.oit.cst236.lab2.model.IBook;
import edu.oit.cst236.lab2.model.ILibrary;
import edu.oit.cst236.lab2.parser.IBookParser;
import edu.oit.cst236.lab2.parser.ILibraryParser;
import edu.oit.cst236.lab2.parser.ParseException;
import edu.oit.cst236.lab2.service.http.LibraryHttpService;

public class LibraryHttpServiceIntegrationTest {
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
	public void testClientSetter_WithGetter() throws ConnectionException	{
		// Need subclass to get access to internal IWebClient
		class LibraryHttpServiceExtra extends LibraryHttpService {			
			public LibraryHttpServiceExtra(IWebClient client) throws ConnectionException { mockClient = client; }
		}				 
		
		LibraryHttpServiceExtra newLibService = new LibraryHttpServiceExtra(mockClient);
		
		IWebClient anotherClient = Mockito.mock(IWebClient.class);		
		newLibService.setWebClient(anotherClient);
	}

	@Test
	public void testGetLibrary() {
		ILibrary library = sut.getLibrary("TestID");		
		Assert.assertNull(library);
	}

	@Test
	public void testGetLibrary_Null() {
		ILibrary library = sut.getLibrary(null);
		Assert.assertNull(library);
	}

	@Test
	public void testGetBooks()	{
		List<IBook> books = sut.getLibraryBooks("TestID");
		Assert.assertNotNull(books);
	}

	@Test
	public void testGetBooks_Null()	{
		List<IBook> books = sut.getLibraryBooks(null);
		Assert.assertNotNull(books);
		Assert.assertTrue(books.isEmpty());
	}

	@Test
	public void testGetBook()	{
		IBook book = sut.getLibraryBook("TestID");

		Assert.assertNull(book);
	}

	@Test
	public void testGetBook_Null()	{
		IBook book = sut.getLibraryBook(null);
		Assert.assertNull(book);
	}
}
