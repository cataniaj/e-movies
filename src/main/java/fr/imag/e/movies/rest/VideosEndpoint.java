package fr.imag.e.movies.rest;


import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import fr.imag.e.movies.ejb.MovieManagerEJB;
import fr.imag.e.movies.ejb.TvManagerEJB;

@Path("/videos")
public class VideosEndpoint {
	@Inject private MovieManagerEJB movieMngr;
	@Inject private TvManagerEJB tvMngr;
	/**
	 * target: if we search one movie or all movies
	 * type: movie or tv
	 * title : movie title or movie id
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("search/{target}/{type}/{title}")
	public JsonObject search(@PathParam("target") String target,
			@PathParam("type") String type,
			@PathParam("title") String title
			){
		
		String url = target+"/"+type+"/"+title;
		JsonObject obj = null;
		//movies
		if(type.equals("movie")){
			obj = movieMngr.searchMovie(url);
		}
		//tv
		if(type.equals("tv")){
			obj = tvMngr.searchTv(url);
		}
		//tv and movies
		if(type.equals("multi")){
			//obj = movieMngr.searchMovie(url);
		}
	    return obj;
	}
}
