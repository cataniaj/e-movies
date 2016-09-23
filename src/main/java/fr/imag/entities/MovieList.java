/*
 *	Author:      Jérémy Leyvraz
 *	Date:        21 sept. 2016
 */

package fr.imag.entities;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class MovieList extends ArrayList<Movie> {
	
	public MovieList(){
	}
	
	public MovieList(List<Movie> movies){
		for(Movie m : movies){
			this.add(m);
		}
	}
	
	public boolean contains(int idTMDB){
		for(Movie m : this){
			if(m.getIdTMDB() == idTMDB){
				return true;
			}
		}
		return false;
	}
	
	public JsonObject convertToJsonArray(){
		String allFilm = "{\"movies\":[";
		for(int i=0 ; i<this.size() ; i++){
			if(i==0){
				allFilm = allFilm + this.get(i).convertToJsonProduct().toString();
			}else{
				allFilm = allFilm + "," + this.get(i).convertToJsonProduct().toString();
			}
		}
		allFilm = allFilm + "]}";
		JsonReader r = Json.createReader(new StringReader(allFilm));
		JsonObject obj = r.readObject();
		return obj;
	}
	
	public JsonObject convertToJsonArrayResultSiteWeb(){
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
	

}
