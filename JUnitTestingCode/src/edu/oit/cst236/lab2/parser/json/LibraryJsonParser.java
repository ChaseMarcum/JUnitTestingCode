package edu.oit.cst236.lab2.parser.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import edu.oit.cst236.lab2.model.ILibrary;
import edu.oit.cst236.lab2.model.core.Library;
import edu.oit.cst236.lab2.parser.ILibraryParser;
import edu.oit.cst236.lab2.parser.ParseException;

/**
 * A library parser that parses json formatted library objects.
 * 
 * @author nferraro
 *
 */
public class LibraryJsonParser implements ILibraryParser {
	protected static final String KEY_ID = "id";
	protected static final String KEY_NAME = "name";
	protected static final String KEY_AVAILABLE_BOOKS = "availableBooks";
	protected static final String KEY_UNAVAILABLE_BOOKS = "unavailableBooks";
	
	@Override
	public List<ILibrary> parseLibraries(String librariesJsonString) throws ParseException {
		List<ILibrary> parsedlibraries = new ArrayList<ILibrary>();
		JSONArray librariesArray = new JSONArray(librariesJsonString);
		
		for(int i = 0; i < librariesArray.length(); ++i ) {
			JSONObject library = librariesArray.getJSONObject(i);
			
			ILibrary parsedlibrary = _parseLibrary(library);
			parsedlibraries.add(parsedlibrary);
		}
		
		return parsedlibraries;
	}
	
	@Override
	public ILibrary parseLibrary(String libraryJsonString) throws ParseException {
		try {
			JSONObject library = new JSONObject(libraryJsonString);
			ILibrary parsedLibrary = _parseLibrary(library);
			return parsedLibrary;
		} catch(JSONException e) {
			throw new ParseException();
		}
	}


	
	/**
	 * Parse a JSONObject that represents a library.
	 * @param libraryJson A JSONObject representing a Library object
	 * @return a parsed Library object
	 * @throws ParseException thrown when parsing fails
	 */
	private ILibrary _parseLibrary(JSONObject libraryJson) throws ParseException {
		try {
			Library parsedLibrary = new Library(libraryJson.getString(KEY_ID), libraryJson.getString(KEY_NAME));
			parsedLibrary.setAvailableBooks(libraryJson.optInt(KEY_AVAILABLE_BOOKS));
			parsedLibrary.setUnavailableBooks(libraryJson.optInt(KEY_UNAVAILABLE_BOOKS));
			return parsedLibrary;
		} catch(Exception e) {
			throw new ParseException();
		}
	}
}
