package fr.imag.e.movies.dao;

import javax.inject.Inject;
import javax.json.JsonObject;

import fr.imag.e.movies.themoviedatabase.SearchEngine;

/**
 * This class performs all the access to the databases
 * @author le06
 *
 */
public class Dao {
	@Inject private SearchEngine searchEngine;
	
	/**
	 * Search a movie or tv on the database and return the json object representing
	 * it
	 * @param url
	 * @return 
	 */
	public JsonObject search(String url){
		JsonObject obj = searchEngine.search(url);
		return obj;
	}
}
