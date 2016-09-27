package fr.imag.searchEngine.object;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        9 sept. 2016
 */


import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class FullTvList extends ArrayList<FullTv> {

	public FullTvList(){
		
	}
	
	public JsonObject convertToJsonArray(){
		String allTv = "{\"tvs\":[";
		for(int i=0 ; i<this.size() ; i++){
			if(i==0){
				allTv = allTv + this.get(i).convertToShortJson().toString();
			}else{
				allTv = allTv + "," + this.get(i).convertToShortJson().toString();
			}
		}
		allTv = allTv + "]}";
		JsonReader r = Json.createReader(new StringReader(allTv));
		JsonObject obj = r.readObject();
		return obj;
	}
	
	public String convertToStringMulti(){
		String allTv = "\"tvs\":[";
		for(int i=0 ; i<this.size() ; i++){
			if(i==0){
				allTv = allTv + this.get(i).convertToShortJson().toString();
			}else{
				allTv = allTv + "," + this.get(i).convertToShortJson().toString();
			}
		}
		allTv = allTv + "]";
		return allTv;
	}
}
