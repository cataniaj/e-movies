package fr.imag.ejb.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;

import fr.imag.ejb.dbaccess.MovieDatabaseAccessEJB;
import fr.imag.themoviedatabase.SearchEngine;

/**
 * This class performs all the operations
 * concerning the videos
 * @author le06
 *
 */
@Stateless
public class VideoManagerEJB {
	@Inject private SearchEngine engine;
	@EJB private MovieDatabaseAccessEJB dbAccess;
	
	public JsonObject search(String url){
		return engine.search(url);
	}
	
	public int getStock(int idProduct){
		return dbAccess.getStock(idProduct);
	}
}
