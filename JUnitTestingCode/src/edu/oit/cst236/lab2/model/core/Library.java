package edu.oit.cst236.lab2.model.core;

import edu.oit.cst236.lab2.model.ILibrary;

/**
 * A Library POJO that follows the ILibrary interface
 * 
 * @author nferraro
 *
 */
public class Library implements ILibrary {
	private final String id;
	private String name;
	private int availableBooks = 0;
	private int unavailableBooks = 0;
	
	/**
	 * Creates an instance of Library.
	 * @param id the id of the library.
	 * @param name the name of the library.
	 */
	public Library(String id, String name) throws IllegalArgumentException {
		if(id == null | id == "")
			throw new IllegalArgumentException();
		
		this.id = id;
		setName(name);
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	/**
	 * Set the name of the library.
	 * @param name the library's name.
	 */
	public void setName(String name)  throws IllegalArgumentException {
		if(name == null | name == "")
			throw new IllegalArgumentException();
		
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the number of available books at this library.
	 * @param availableBooks the number of available books.
	 */
	public void setAvailableBooks(int availableBooks) throws IllegalArgumentException {
		if(availableBooks < 0)
			throw new IllegalArgumentException();
		
		this.availableBooks = availableBooks;
	}

	@Override
	public int getAvailableBooks() {
		return this.availableBooks;
	}
	
	/**
	 * Set the number of unavailable books at this library.
	 * @param unavailableBooks the number of unavailable books.
	 */
	public void setUnavailableBooks(int unavailableBooks) throws IllegalArgumentException {
		if(unavailableBooks < 0)
			throw new IllegalArgumentException();
		
		this.unavailableBooks = unavailableBooks;
	}

	@Override
	public int getUnavailableBooks() {
		return this.unavailableBooks;
	}
}
