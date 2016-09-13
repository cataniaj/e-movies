package fr.imag.e.movies.searchEngine.object;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        11 sept. 2016
 */

public enum Support {
	
	DVD("DVD"),
	BLURAY("Bluray"),
	NUMERIC("Copie numérique");
	
	private String name = "";
		   
	private Support(String name){
		this.name= name;
	}
	
	public String toString(){
		return name;
	}
		   
}
