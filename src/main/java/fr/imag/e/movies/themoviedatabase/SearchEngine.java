package fr.imag.e.movies.themoviedatabase;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        11 sept. 2016
 */


import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import fr.imag.e.movies.searchEngine.object.*;

public class SearchEngine {

	/// Exemple url = target/type/title
	
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
		
		switch(target){
			case allKeyword:
				return searchAll(type, title);
			case exactKeyword:
				return searchExact(type, title);
			default:
				JsonReader r = Json.createReader(new StringReader("{}"));
				JsonObject obj = r.readObject();
				return obj;
		}
	}
	
	private JsonObject searchAll(String type, String title){
		
		switch(type){
			case movieKeyword:
				MoviesDB moviesDB = new MoviesDB();
				MovieList moviesList = moviesDB.buildFilmList(title);
				return moviesList.convertToJsonArray();
			case tvKeyword:
				TvDB tvDB = new TvDB();
				FullTvList tvsList = tvDB.buildTvList(title);
				return tvsList.convertToJsonArray();
			case multiKeyword:
				MultiVideo multi = new MultiVideo();
				return multi.buildMultiVideo(title);
			default:
				JsonReader r = Json.createReader(new StringReader("{}"));
				JsonObject obj = r.readObject();
				return obj;
		}
	}
	
	private JsonObject searchExact(String type, String id){
		
		switch(type){
			case movieKeyword:
				MoviesDB moviesDB = new MoviesDB();
				Movie movie = moviesDB.buildFilm(id);
				return movie.convertToFullJson();
			case tvKeyword:
				TvDB tvDB = new TvDB();
				FullTv tv = tvDB.buildTv(id);
				return tv.convertToFullJson();
			default:
				JsonReader r = Json.createReader(new StringReader("{}"));
				JsonObject obj = r.readObject();
				return obj;
		}
	}
	
}
