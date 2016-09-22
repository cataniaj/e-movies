package fr.imag.themoviedatabase;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        11 sept. 2016
 */

import java.util.ArrayList;

public class Filter {

	private ArrayList<String> testFilter;
	
	public Filter(){
		testFilter = new ArrayList<String>();
		//testFilter.add("124905"); // id dernier Godzilla
		//testFilter.add("330"); // id le monde perdu jurassic park
		//testFilter.add("8077"); // id alien 3
		//testFilter.add("579"); // id les dents de la mer 2
	}
	
	public boolean contains(String idFilm){
		for(String id : testFilter){
			if(id.compareTo(idFilm)==0){
				return true;
			}
		}
		return false;
	}
	
}
