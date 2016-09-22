package fr.imag.themoviedatabase;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        11 sept. 2016
 */


import java.io.StringReader;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import fr.imag.ejb.dbaccess.MovieDatabaseAccessEJB;
import fr.imag.entities.Movie;
import fr.imag.entities.MovieList;
/**
 * We want only one copy of this class for all the client
 * that will use it and it is thread-safe (allow concurrent access)
 * @author le06
 *
 */

@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class SearchEngine {

	/// Exemple url = target/type/title
	@EJB private MovieDatabaseAccessEJB dbAccess;

	private final String defaultValue = "N/A";
	
	// TARGET 
	private final String allKeyword = "all";
	private final String exactKeyword = "exact";
	
	// TYPE
	private final String movieKeyword = "movie";
	private final String tvKeyword = "tv";
	private final String multiKeyword = "multi";
	
	public SearchEngine(){
	}
	
	public JsonObject search(String url){
		String[] elementUrl = url.split("/");
		String target = elementUrl[0];
		String type = elementUrl[1];
		String title = elementUrl[2];
		
		if(target.compareTo(allKeyword) == 0){
			return searchAll(type, title);
		}else if(target.compareTo(exactKeyword) == 0){
			return searchExact(type, title);
		}else {
			JsonReader r = Json.createReader(new StringReader("{}"));
			JsonObject obj = r.readObject();
			return obj;
		}
	}

	private JsonObject searchAll(String type, String title){
		if(type.compareTo(movieKeyword)==0){
			return searchAllMovies(title);
		}
		return null;
	}
	
	private JsonObject searchExact(String type, String id){
		if(type.compareTo(movieKeyword)==0){
			return searchExactMovie(id);
		}
		return null;
	}
	
	private JsonObject searchAllMovies(String title){
    	MovieList movieList = findAllFilmInOurDataBase();
    	String lowerTitle = title.toLowerCase();
    	MovieList result = new MovieList();
    	for(Movie m : movieList){
    		String movieTitle = m.getTitle().toLowerCase();
    		if(movieTitle.contains(lowerTitle)){
    			result.add(m);
    		}
    	}
    	return result.convertToJsonArrayResultSiteWeb();
	}
	
    private JsonObject searchExactMovie(String id){
		
    	String idProductDvd = defaultValue;
    	String idProductBluray = defaultValue;
    	String idProductNumeric = defaultValue;
    	
    	String dvdPrice = defaultValue;
    	String blurayPrice = defaultValue;
    	String numericPrice = defaultValue;
    	
    	String dvdStock = defaultValue;
    	String blurayStock = defaultValue;
    	
    	int idTMDB = 0;
    	String title = defaultValue;
    	String year = defaultValue;
    	String genre = defaultValue;
    	String poster = defaultValue;
    	String director = defaultValue;
    	String actors = defaultValue;
    	String overview = defaultValue;
    	String runtime = defaultValue;
    	String trailer = defaultValue;
    	
    	List<Movie> movieList = findAllFilmInOurDataBase();
		
		// extraire les 2 stocks et les 3 prix
    	for(Movie m : movieList){
    		
    		if(m.getIdTMDB()!=Integer.parseInt(id)){
    			continue;
    		}
    		
        	idTMDB = m.getIdTMDB();
        	title = m.getTitle();
        	year = m.getYear();
        	genre = m.getGenre();
        	poster = m.getPoster();
        	director = m.getDirector();
        	actors = m.getActors();
        	overview = m.getOverview();
        	runtime = m.getRuntime();
        	trailer = m.getTrailer();
    		
    		if(m.getSupport().compareTo("DVD")==0){
    			idProductDvd = String.valueOf(m.getIdProduct());
    			dvdPrice = String.valueOf(m.getPrice());
    			dvdStock = String.valueOf(m.getStock());
    		}else if(m.getSupport().compareTo("BR")==0){
    			idProductBluray = String.valueOf(m.getIdProduct());
    			blurayPrice = String.valueOf(m.getPrice());
    			blurayStock = String.valueOf(m.getStock());
    		}else if(m.getSupport().compareTo("CN")==0){
    			idProductNumeric = String.valueOf(m.getIdProduct());
    			numericPrice = String.valueOf(m.getPrice());
    		}
    	}
		
		// Construite le json
		JsonObject movie = Json.createObjectBuilder()
				.add("idProductDvd", idProductDvd)
				.add("idProductBR", idProductBluray)
				.add("idProductNumeric", idProductNumeric)
				.add("dvdStock", dvdStock)
				.add("blurayStock", blurayStock)
				.add("dvdPrice", dvdPrice)
				.add("blurayPrice", blurayPrice)
				.add("numericPrice", numericPrice)
				.add("title", title)
				.add("year", year)
				.add("genre", genre)
				.add("poster", poster)
				.add("director", director)
				.add("overview", overview)
				.add("runtime", runtime)
				.add("actors", actors)
				.add("trailer", trailer)
				.add("idTMDB", idTMDB)
				.build();
    	
    	return movie;
    }

	private MovieList findAllFilmInOurDataBase(){
		MovieList m = new MovieList(dbAccess.findAllFilmInOurDataBase());
    	return m;
	}

}
