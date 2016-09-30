package fr.imag.rest;

import java.io.StringReader;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	
	/**
	 * Recherche un produit
	 * @param target All or exact
	 * @param type Le type de produit
	 * @param title Les mots clés de la recherche
	 * @return Un json contenant le resultat de la recherche
	 */
	@GET
	@Produces({"application/json"})
	@Path("/search/{target}/{type}/{title}")
	public Response search(@PathParam("target") String target,
			@PathParam("type") String type,
			@PathParam("title") String title
			){
		String url = target+"/"+type+"/"+title;
		JsonObject obj = videosMngr.search(url);
		return Response.status(200).entity(obj).build();
	}
	
	/**
	 * Renvoie le stock d'un produit
	 * @param data L'identifiant du produit: "idProduct"
	 * @return Le stock du produit
	 */
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	@Path("/getStock")
	public Response stock(String data){
		try{
			JsonReader r = Json.createReader(new StringReader(data));
			JsonObject obj = r.readObject();
			int stock = videosMngr.getStock(obj.getInt("idProduct"));
			return Response.status(200).entity(stock).build();
		}catch(Exception e){
			return Response.status(204).entity("Une erreur est survenue").build();
		}
	}
}
