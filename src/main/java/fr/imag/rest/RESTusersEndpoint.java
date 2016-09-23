package fr.imag.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import fr.imag.ejb.business.UserManagerEJB;
import fr.imag.entities.User;
import fr.imag.utilities.LoginData;


/**
 * The users endpoint
 * @author le06
 *
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
		userMngr.createNewAccount(user);
		System.out.println(user.toString());
		return Response.status(200).build();
	}
	
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("/login")
	public Response login(LoginData data){
		User res = userMngr.login(data);
		if(res != null)
			System.out.println(res.toString());
		//System.out.println(data.getMail()+" "+data.getPassword());
		return Response.status(200).entity(res).build();
	}
}
