package fr.imag.entities;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        15 sept. 2016
 */

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * This is the ejb which represents a movie
 * @author Jeremy Leyvraz
 *
 */
@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Product{
	
	private int idTMDB;
	private String title;
	private String year;
	private String genre;
	private String poster;
	private String director;
	private String actors;
	@Column(name="text", columnDefinition="LONGTEXT")
	private String overview;
	private String runtime;
	private String trailer;
	
	/**
	 * Constructeur par defaut
	 */
	public Movie(){
		super();
		idTMDB = 0;
		title = defaultValue;
		year = defaultValue;
		genre = defaultValue;
		poster = defaultValue;
		director = defaultValue;
		actors = defaultValue;
		overview = defaultValue;
		runtime = defaultValue;
		trailer = defaultValue;
	}

	public int getIdTMDB() {
		return idTMDB;
	}

	public void setIdTMDB(int idTMDB) {
		this.idTMDB = idTMDB;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
		//this.overview = (overview.length() > 254) ? overview.substring(0, 254): overview;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	
	/**
	 * Affiche en console le film
	 */
	public void print(){
		super.print();
		System.out.println("idTMDB: "+idTMDB);
		System.out.println("title: "+title);
		System.out.println("year: "+year);
		System.out.println("genre: "+genre);
		System.out.println("poster: "+poster);
		System.out.println("director: "+director);
		System.out.println("actors: "+actors);
		System.out.println("overview: "+overview);
		System.out.println("runtime: "+runtime);
		System.out.println("trailer: "+trailer);
	}

	/**
	 * Convertit en Json minimal un film
	 * @return La description minimale d'un film en json
	 */
	public JsonObject convertToShortJson(){		
		JsonObject film = Json.createObjectBuilder()
				.add("title", title)
				.add("year", year)
				.add("poster", poster)
				.add("idTMDB", idTMDB)
				.build();
		return film;
	}
	
	/**
	 * Convertit au format json un produit
	 * @return Le produit au format Json
	 */
	public JsonObject convertToJsonProduct(){
		JsonObject film = Json.createObjectBuilder()
				.add("idProduct", idProduct)
				.add("title", title)
				.add("year", year)
				.add("support", support)
				.add("stock", stock)
				.add("price", price)
				.build();
		return film;
	}
	
	/**
	 * Convertit au format json la description complete d'un film
	 * @return Le film au format json
	 */
	public JsonObject convertToJsonFull(){
		JsonObject film = Json.createObjectBuilder()
				.add("idProduct", idProduct)
				.add("support", support)
				.add("stock", stock)
				.add("price", price)
				
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
		return film;
	}
	
}


