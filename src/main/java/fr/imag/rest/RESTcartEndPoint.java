/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import fr.imag.ejb.business.CartManagerEJB;

@Path("/cart")
public class RESTcartEndPoint {
	@EJB private CartManagerEJB cartMngr;
	
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("/addOneItem")
	public void addOneItem(){
		
	}

}
