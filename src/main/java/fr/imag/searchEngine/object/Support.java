package fr.imag.searchEngine.object;

/**
 * Enumeration décrivant le support d'un produit
 * @author Jerem
 *
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
