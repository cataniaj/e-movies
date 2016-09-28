package fr.imag.rest;

import java.io.StringReader;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.imag.ejb.business.UserManagerEJB;
import fr.imag.entities.User;
import fr.imag.utilities.LoginData;


/**
 * The users endpoint
 * @author le06
 */
@Path("/users")
public class RESTusersEndpoint {
	@EJB private UserManagerEJB userMngr;
	/**
	 * This method create a new account
	 * by receiving a json object containing the
	 * user data from the client
	 */
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("/createNewAccount")
	public Response createAnAccount(User user){

		boolean res=userMngr.createNewAccount(user);
		System.out.println(user.toString());
		
		if(res == true){
			return Response.status(200).build();			
		}
		return Response.status(204).build();

	}
	
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("/login")
	public Response login(LoginData data){
		User res = userMngr.login(data);

		if(res != null){
			return Response.status(200).entity(res).build();			
		}
		return Response.status(204).entity(res).build();

	}

	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("/information")
	public Response getInformation(String data){
		try{
			JsonReader r = Json.createReader(new StringReader(data));
			JsonObject obj = r.readObject();
	    	String user = obj.getString("mail");
			return Response.status(200).entity(userMngr.getInformation(user)).build();	
		}catch(Exception e){
			return Response.status(204).entity("Une erreur est survenue.").build();
		}
	}

}
