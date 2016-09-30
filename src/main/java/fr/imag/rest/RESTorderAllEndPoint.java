/*
 *	Author:      Jérémy Leyvraz
 *	Date:        24 sept. 2016
 */

package fr.imag.rest;

import java.io.StringReader;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.imag.ejb.business.OrderAllManagerEJB;

/**
 * Module REST pour les acces orderAll
 * @author Jerem
 *
 */
@Path("/order")
public class RESTorderAllEndPoint {
	@Inject private OrderAllManagerEJB orderAllMngr;

	/**
	 * Renvoie la liste des commandes d'un client
	 * @param data Un json: "mail"
	 * @return La liste des commandes en json ou "Une erreur est survenue"
	 */
    @POST
    @Path("/getOrder")
	@Consumes({"application/json"})
	@Produces({"application/json"})
    public Response getOrder(String data){
    	try{
    		JsonReader r = Json.createReader(new StringReader(data));
    		JsonObject obj = r.readObject();
        	String user = obj.getString("mail");
    		return Response.status(200).entity(orderAllMngr.getAllOrderLine(user)).build();
    	}catch(Exception e){
    		return Response.status(204).entity("Une erreur est survenue").build();
    	}
    }
}
