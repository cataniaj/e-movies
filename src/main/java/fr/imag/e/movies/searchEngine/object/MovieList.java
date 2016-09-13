package fr.imag.e.movies.searchEngine.object;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        8 sept. 2016
 */


import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class MovieList extends ArrayList<Movie> {
	
	public MovieList(){
		
	}
	
	public JsonObject convertToJsonArray(){
		String allFilm = "{\"movies\":[";
		for(int i=0 ; i<this.size() ; i++){
			if(i==0){
				allFilm = allFilm + this.get(i).convertToShortJson().toString();
			}else{
				allFilm = allFilm + "," + this.get(i).convertToShortJson().toString();
			}
		}
		allFilm = allFilm + "]}";
		JsonReader r = Json.createReader(new StringReader(allFilm));
		JsonObject obj = r.readObject();
		return obj;
	}
	
	public String convertToStringMulti(){
		String allFilm = "\"movies\":[";
		for(int i=0 ; i<this.size() ; i++){
			if(i==0){
				allFilm = allFilm + this.get(i).convertToShortJson().toString();
			}else{
				allFilm = allFilm + "," + this.get(i).convertToShortJson().toString();
			}
		}
		allFilm = allFilm + "]";
		return allFilm;
	}

}
