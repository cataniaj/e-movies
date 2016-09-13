package fr.imag.e.movies.ejb;

import javax.inject.Inject;
import javax.json.JsonObject;

import fr.imag.e.movies.dao.Dao;

public class TvManagerEJB {
	@Inject private Dao dao;
	/**
	 * Search a tv on TMDB
	 * @param url
	 * @return
	 */
	public JsonObject searchTv(String url){
		return dao.search(url);
	}
}
