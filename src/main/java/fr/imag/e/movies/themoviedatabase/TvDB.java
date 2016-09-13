package fr.imag.e.movies.themoviedatabase;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        8 sept. 2016
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import fr.imag.e.movies.searchEngine.object.FullTv;
import fr.imag.e.movies.searchEngine.object.FullTvList;

public class TvDB {

	private final String apiKey = "api_key=db1096cd136c906c06e7d77b313df0d4";
	private final String language = "language=fr";

	private final String urlManyTvTmdb = "https://api.themoviedb.org/3/search/tv?query=%s&"+language+"&"+apiKey;
	private final String urlJustOneTvTmdb = "https://api.themoviedb.org/3/tv/%s?append_to_response=credits&"+language+"&"+apiKey;
	private final String urlImage = "https://image.tmdb.org/t/p/w300";

	private final String jsonTmdbTitle = "name";
	private final String jsonTmdbOverview = "overview";
	private final String jsonTmdbYear = "first_air_date";
	private final String jsonTmdbPoster = "poster_path";
	private final String jsonTmdbCreator = "created_by";
	private final String jsonTmdbId = "id";
	private final String jsonTmdbNumberOfSeasons = "number_of_seasons";
	private final String jsonTmdbNumberOfEpisodes = "number_of_episodes";
	private final String jsonTmdbInProduction = "in_production";

	public TvDB(){
	}

	private JsonObject getCredits(JsonObject tvJson){
		return tvJson.getJsonObject("credits");
	}

	private JsonArray getCast(JsonObject credits){
		return credits.getJsonArray("cast");
	}

	private String getCreator(JsonObject tv){
		String creator = "";
		JsonArray jsonCreator = tv.getJsonArray(jsonTmdbCreator);
		for(int i=0 ; i<jsonCreator.size() ; i++){
			JsonObject person = jsonCreator.getJsonObject(i);
			if(i==0){
				creator = person.getString("name");
			}else{
				creator = creator + ", " + person.getString("name");
			}
		}
		return creator;
	}

	private String getGenres(JsonObject tvJson){
		String allGenres = "";
		JsonArray genres = tvJson.getJsonArray("genres");
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

	private String connectionAndRequestManyTv(String title){
		URLConnection urlConnection;
		try {
			String url = String.format(urlManyTvTmdb, title.replace(" ", "+"));
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

	private String connectionAndRequestJustOneTv(String idFilm){
		try {
			URLConnection urlConnection;
			//String urlJustOneFilmTmdb = "https://api.themoviedb.org/3/movie/%s?append_to_response=credits&language=fr&"+apiKey;
			String url = String.format(urlJustOneTvTmdb, idFilm);
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

	public FullTvList buildTvList(String title){
		FullTvList tvList = new FullTvList();
		String jsonTvList = connectionAndRequestManyTv(title);
		JsonReader r = Json.createReader(new StringReader(jsonTvList));
		JsonObject obj = r.readObject();
		JsonArray array = obj.getJsonArray("results");
		for(int i=0 ; i<array.size() ; i++){
			FullTv tv = new FullTv();
			JsonObject tvJson = array.getJsonObject(i);
			try{
				tv.setTitle(tvJson.getString(jsonTmdbTitle));
			}catch(Exception e){}
			try{
				tv.setYear(tvJson.getString(jsonTmdbYear));
			}catch(Exception e){}
			try{
				tv.setId(String.valueOf(tvJson.getInt(jsonTmdbId)));
			}catch(Exception e){}
			try{
				tv.setPoster(urlImage+tvJson.getString(jsonTmdbPoster));
			}catch(Exception e){
			}
			tvList.add(tv);
		}
		System.out.println(tvList.convertToJsonArray());
		return tvList;
	}

	public FullTv buildTv(String idTv){
		FullTv tv = new FullTv();
		try {
			String jsonOneTv = connectionAndRequestJustOneTv(idTv);
			JsonReader r = Json.createReader(new StringReader(jsonOneTv));
			JsonObject obj = r.readObject();
			try{
				tv.setTitle(obj.getString(jsonTmdbTitle));
			}catch(Exception e){System.out.println(e);}

			try{
				tv.setOverview(obj.getString(jsonTmdbOverview));
			}catch(Exception e){System.out.println(e);}

			try{
				boolean inProduction = obj.getBoolean(jsonTmdbInProduction);
				if(inProduction){
					tv.setInProduction("Oui");
				}else{
					tv.setInProduction("Non");
				}
			}catch(Exception e){System.out.println(e);}

			try{
				tv.setNumberOfEpisodes(String.valueOf(obj.getInt(jsonTmdbNumberOfEpisodes)));
			}catch(Exception e){System.out.println(e);}

			try{
				tv.setNumberOfSeasons(String.valueOf(obj.getInt(jsonTmdbNumberOfSeasons)));
			}catch(Exception e){System.out.println(e);}

			try{
				tv.setYear(obj.getString(jsonTmdbYear));
			}catch(Exception e){System.out.println(e);}

			try{
				tv.setId(String.valueOf(obj.getInt(jsonTmdbId)));
			}catch(Exception e){System.out.println(e);}

			try{
				tv.setPoster(urlImage+obj.getString(jsonTmdbPoster));
			}catch(Exception e){System.out.println(e);}

			JsonObject credits = getCredits(obj);
			JsonArray cast = getCast(credits);

			try{
				tv.setCreator(getCreator(obj));
			}catch(Exception e){System.out.println(e);}

			try{
				tv.setActors(getActors(cast));
			}catch(Exception e){System.out.println(e);}

			try{
				tv.setGenre(getGenres(obj));
			}catch(Exception e){System.out.println(e);}
			

		} catch (Exception e) {
			System.out.println(e);
		}
		return tv;
	}

}
