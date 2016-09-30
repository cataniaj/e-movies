package fr.imag.searchEngine.object;

import javax.json.Json;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        9 sept. 2016
 */


import javax.json.JsonObject;

/**
 * Représente une série télé
 * @author Jerem
 *
 */
public class FullTv {
	
	private final String defaultValue = "N/A ";
	
	private String title ;
	private String creator ;
	private String actors ;
	private String genre;
	private String year ;
	private String poster ;
	private String inProduction ;
	private String overview ;
	private String numberOfSeasons ;
	private String numberOfEpisodes ;
	private String id;
	
	public FullTv(){
		title = defaultValue;
		creator = defaultValue;
		actors = defaultValue;
		year = defaultValue;
		genre = defaultValue;
		poster = defaultValue;
		inProduction = defaultValue;
		overview = defaultValue;
		numberOfSeasons = defaultValue;
		numberOfEpisodes = defaultValue;
		id = defaultValue;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		if(creator.isEmpty()){
			this.creator = defaultValue;
		}else{
			this.creator = creator;
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
	public String getInProduction() {
		return inProduction;
	}
	public void setInProduction(String inProduction) {
		if(inProduction.isEmpty()){
			this.inProduction = defaultValue;
		}else{
			this.inProduction = inProduction;
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
	public String getNumberOfSeasons() {
		return numberOfSeasons;
	}
	public void setNumberOfSeasons(String numberOfSeasons) {
		if(numberOfSeasons.isEmpty()){
			this.numberOfSeasons = defaultValue;
		}else{
			this.numberOfSeasons = numberOfSeasons;
		}
	}
	public String getNumberOfEpisodes() {
		return numberOfEpisodes;
	}
	public void setNumberOfEpisodes(String numberOfEpisodes) {
		if(numberOfEpisodes.isEmpty()){
			this.numberOfEpisodes = defaultValue;
		}else{
			this.numberOfEpisodes = numberOfEpisodes;
		}
	}

	public JsonObject convertToShortJson(){		
		JsonObject tv = Json.createObjectBuilder()
				.add("title", title)
				.add("year", year.substring(0, 4))
				.add("poster", poster)
				.add("id", id)
				.build();
		return tv;
	}
	public JsonObject convertToFullJson(){
		JsonObject film = Json.createObjectBuilder()				
				.add("title", title)
				.add("creator", creator)
				.add("year", year.substring(0, 4))
				.add("actors", actors)
				.add("overview", overview)
				.add("genre", genre)
				.add("poster", poster)
				.add("id", id)
				.add("inProduction", inProduction)
				.add("numberOfSeasons", numberOfSeasons)
				.add("numberOfEpisodes", numberOfEpisodes)
				.build();
		return film;
	}

}
