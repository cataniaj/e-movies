package fr.imag.searchEngine.object;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * Représente un film
 * @author Jerem
 *
 */
public class Movie {
	
	private final String defaultValue = "N/A";
	
	private String title;
	private String director;
	private String year;
	private String actors;
	private String overview;
	private String genre;
	private String poster;
	private String id;
	private String runtime;
	private String video;
		
	/**
	 * Constructeur par défaut
	 */
	public Movie(){
		title = defaultValue;
		director = defaultValue;
		year = defaultValue;
		actors = defaultValue;
		overview = defaultValue;
		poster = defaultValue;
		id = defaultValue;
		genre = defaultValue;
		runtime = defaultValue;
		video = defaultValue;
	}

	public String getVideo() {
		return video;
	}
	
	public void setVideo(String video) {
		if(video.isEmpty()){
			this.video = defaultValue;
		}else{
			this.video = video;	
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title.isEmpty()){
			this.title = defaultValue;
		}else{
			this.title = title;	
		}
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		if(director.isEmpty()){
			this.director = defaultValue;
		}else{
			this.director = director;	
		}
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		if(year.isEmpty()){
			this.year = defaultValue;
		}else{
			this.year = year;	
		}
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		if(actors.isEmpty()){
			this.actors = defaultValue;
		}else{
			this.actors = actors;	
		}
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		if(overview.isEmpty()){
			this.overview = defaultValue;
		}else{
			this.overview = overview;
		}
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		if(poster.isEmpty()){
			this.poster = defaultValue;
		}else{
			this.poster = poster;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id.isEmpty()){
			this.id = defaultValue;
		}else{
			this.id = id;
		}
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if(genre.isEmpty()){
			this.genre = defaultValue;
		}else{
			this.genre = genre;
		}
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		if(runtime.isEmpty()){
			this.runtime = defaultValue;
		}else{
			this.runtime = runtime;
		}
	}
	
	/**
	 * Convertit la durée en chaine de caractere
	 * @return une chaine representant la durée du film
	 */
	private String convertRuntime(){
		if(runtime.compareTo(defaultValue)==0){
			return "0 min";
		}else{
			int numberMinute = Integer.parseInt(runtime);
			return numberMinute/60+" h "+ numberMinute%60 +" min";
		}
	}
	
	/**
	 * Convertit le film en json minimal
	 * @return
	 */
	public JsonObject convertToShortJson(){		
		JsonObject film = Json.createObjectBuilder()
				.add("title", title)
				.add("year", year)
				.add("poster", poster)
				.add("id", id)
				.build();
		return film;
	}
	
	/**
	 * Convertit le film en json complet
	 * @return
	 */
	public JsonObject convertToFullJson(){
		JsonObject film = Json.createObjectBuilder()
				.add("title", title)
				.add("director", director)
				.add("year", year.substring(0, 4))
				.add("actors", actors)
				.add("overview", overview)
				.add("genre", genre)
				.add("poster", poster)
				.add("id", id)
				.add("runtime", convertRuntime())
				.add("video", video)
				.build();
		return film;
	}

}
