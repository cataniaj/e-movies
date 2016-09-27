package fr.imag.searchEngine.object;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        11 sept. 2016
 */

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import fr.imag.themoviedatabase.MoviesDB;
import fr.imag.themoviedatabase.TvDB;


public class MultiVideo {

	
	public MultiVideo(){
	}
	
	public JsonObject buildMultiVideo(String title){
		MoviesDB moviesDB = new MoviesDB();
		MovieList movieList = moviesDB.buildFilmList(title);
		String moviesListJson = movieList.convertToStringMulti();
		
		TvDB tvDB = new TvDB();
		FullTvList tvList = tvDB.buildTvList(title);
		String tvsListJson = tvList.convertToStringMulti();
		
		String result = "{\n" 
				+ tvsListJson + ", \n"
				+ moviesListJson + "\n}";

		JsonReader r = Json.createReader(new StringReader(result));
		JsonObject obj = r.readObject();
		
		return obj;
	}
}
