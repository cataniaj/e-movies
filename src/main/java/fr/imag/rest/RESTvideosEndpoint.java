package fr.imag.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.imag.ejb.business.VideoManagerEJB;

/**
 * The videos (movies and tvs) endpoint
 * @author le06
 *
 */
@Path("/videos")
public class RESTvideosEndpoint {
	@Inject private VideoManagerEJB videosMngr;
	
	@GET
	@Produces({"application/json"})
	@Path("/search/{target}/{type}/{title}")
	public Response search(@PathParam("target") String target,
			@PathParam("type") String type,
			@PathParam("title") String title
			){
		String url = target+"/"+type+"/"+title;
		JsonObject obj = videosMngr.search(url);
		//return obj;
		return Response.status(200).entity(obj).build();
	}
}
