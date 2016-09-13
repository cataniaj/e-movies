package fr.imag.e.movies.themoviedatabase;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        6 sept. 2016
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

//import javax.json.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import fr.imag.e.movies.searchEngine.object.Movie;
import fr.imag.e.movies.searchEngine.object.MovieList;



public class MoviesDB {
	
	private final String apiKey = "api_key=db1096cd136c906c06e7d77b313df0d4";
	private final String language = "language=fr";
	
	/*
	 * Exemple renvoi plusieurs films
	 * https://api.themoviedb.org/3/search/movie?query=jurassic+park&language=fr&api_key=db1096cd136c906c06e7d77b313df0d4
	 */
	private final String urlManyFilmTmdb = "https://api.themoviedb.org/3/search/movie?query=%s&"+language+"&"+apiKey;
    private final String urlJustOneFilmTmdb = "https://api.themoviedb.org/3/movie/%s?append_to_response=credits&"+language+"&"+apiKey;
	private final String urlImage = "https://image.tmdb.org/t/p/w300";
	
	private final String jsonTmdbTitle = "title";
	private final String jsonTmdbOverview = "overview";
	private final String jsonTmdbYear = "release_date";
	private final String jsonTmdbPoster = "poster_path";
	private final String jsonTmdbRuntime = "runtime";
	private final String jsonTmdbId = "id";
	
	private final String urlTrailer = "https://api.themoviedb.org/3/movie/%s/videos?"+language+"&"+apiKey;
	private final String urlYoutube = "https://www.youtube.com/watch?v=";
	private final String jsonTmdbVideoKey = "key";
		
	public MoviesDB(){
	}
	
	private JsonObject getCredits(JsonObject filmJson){
		return filmJson.getJsonObject("credits");
	}
	
	private JsonArray getCrew(JsonObject credits){
		return credits.getJsonArray("crew");
	}
	
	private JsonArray getCast(JsonObject credits){
		return credits.getJsonArray("cast");
	}
	
	private String getDirector(JsonArray crew){
		String jobValueToFound = "Director";
		String jobValueFound = "";

		for(int i=0 ; i<crew.size() ; i++){
			JsonObject o = crew.getJsonObject(i);
			jobValueFound = o.getString("job");
			if(jobValueFound.compareTo(jobValueToFound)==0){
				return o.getString("name");
			}
		}
		return "";
	}
	
	private String getGenres(JsonObject filmJson){
		String allGenres = "";
		JsonArray genres = filmJson.getJsonArray("genres");
		for(int i=0 ; i<genres.size() ; i++){
			JsonObject o = genres.getJsonObject(i);
			if(i==0){
				allGenres = o.getString("name");
			}else{
				allGenres = allGenres + ", " + o.getString("name");
			}
		}
		return allGenres;
	}
	
	private String getActors(JsonArray cast){
		String allActors = "";
		for(int i=0 ; i<cast.size() && i<5 ; i++){
			JsonObject o = cast.getJsonObject(i);
			if(i==0){
				allActors = o.getString("name");
			}else{
				allActors = allActors + ", " + o.getString("name");
			}
		}
		return allActors;
	}
	
	private String connectionAndRequestManyFilm(String title){
		URLConnection urlConnection;
		try {
			String url = String.format(urlManyFilmTmdb, title.replace(" ", "+"));
			URL urlRequest = new URL(url);
			urlConnection = urlRequest.openConnection();
			urlConnection.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			return in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private String connectionAndRequestJustOneFilm(String idFilm){
		try {
		    URLConnection urlConnection;
	        //String urlJustOneFilmTmdb = "https://api.themoviedb.org/3/movie/%s?append_to_response=credits&language=fr&"+apiKey;
			String url = String.format(urlJustOneFilmTmdb, idFilm);
			URL urlRequest = new URL(url);
			urlConnection = urlRequest.openConnection();
			urlConnection.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		    return in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private String connectionAndRequestTrailerLink(String idFilm){
	    URLConnection urlConnection;
		try {
			String url = String.format(urlTrailer, idFilm);
			URL urlRequest = new URL(url);
			urlConnection = urlRequest.openConnection();
			urlConnection.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		    return in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private String getTrailer(Movie film){
		String jsonTrailer = connectionAndRequestTrailerLink(film.getId());
		JsonReader r = Json.createReader(new StringReader(jsonTrailer));
		JsonObject obj = r.readObject();
		JsonArray array = obj.getJsonArray("results");
		if(array.size()>0){
			JsonObject o = array.getJsonObject(0);
			return urlYoutube+o.getString(jsonTmdbVideoKey);	
		}
		return "";
	}

	public MovieList buildFilmList(String title){
			MovieList filmList = new MovieList();
			String jsonFilmList = connectionAndRequestManyFilm(title);
			JsonReader r = Json.createReader(new StringReader(jsonFilmList));
			JsonObject obj = r.readObject();
			JsonArray array = obj.getJsonArray("results");
			for(int i=0 ; i<array.size() ; i++){
				Movie film = new Movie();
				Filter filter = new Filter();
				
				JsonObject filmJson = array.getJsonObject(i);
				try{
					film.setTitle(filmJson.getString(jsonTmdbTitle));
				}catch(Exception e){}
				try{
					film.setYear(filmJson.getString(jsonTmdbYear));
				}catch(Exception e){}
				try{
					film.setId(String.valueOf(filmJson.getInt(jsonTmdbId)));
				}catch(Exception e){}
				try{
					film.setPoster(urlImage+filmJson.getString(jsonTmdbPoster));
				}catch(Exception e){
				}

				if(filter.contains(film.getId())){
					continue;
				}
				filmList.add(film);
			}
			System.out.println(filmList.convertToJsonArray());
			return filmList;
	}
	
	public Movie buildFilm(String idFilm){
		Movie movie = new Movie();
		try {
			String jsonOneFilm = connectionAndRequestJustOneFilm(idFilm);
			JsonReader r = Json.createReader(new StringReader(jsonOneFilm));
			JsonObject obj = r.readObject();
			try{
				movie.setTitle(obj.getString(jsonTmdbTitle));
			}catch(Exception e){}
			
			try{
				movie.setOverview(obj.getString(jsonTmdbOverview));
			}catch(Exception e){}
			
			try{
				movie.setYear(obj.getString(jsonTmdbYear));
			}catch(Exception e){}
			
			try{
				movie.setId(String.valueOf(obj.getInt(jsonTmdbId)));
			}catch(Exception e){}
			
			try{
				movie.setPoster(urlImage+obj.getString(jsonTmdbPoster));
			}catch(Exception e){}
			try{
				movie.setRuntime(String.valueOf(obj.getInt(jsonTmdbRuntime)));
			}catch(Exception e){}

			JsonObject credits = getCredits(obj);
			JsonArray crew = getCrew(credits);
			JsonArray cast = getCast(credits);
			
			try{
				movie.setDirector(getDirector(crew));
			}catch(Exception e){}
			
			try{
				movie.setActors(getActors(cast));
			}catch(Exception e){}
			
			try{
				movie.setGenre(getGenres(obj));
			}catch(Exception e){}
			
			try{
				movie.setVideo(getTrailer(movie));
			}catch(Exception e){}
			
		} catch (Exception e) {
			System.out.println("ERREUR");
		}
		return movie;
	}
	
	

}
