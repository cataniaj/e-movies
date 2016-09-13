package fr.imag.e.movies.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;

import fr.imag.e.movies.dao.Dao;

/**
 * This is the ejb which manages all the operations on the movies
 * @author le06
 *
 */
@Stateless
public class MovieManagerEJB {
	@Inject private Dao dao;
	/**
	 * Search a movie on TMDB
	 * @param url
	 * @return
	 */
	public JsonObject searchMovie(String url){
		return dao.search(url);
	}
}
