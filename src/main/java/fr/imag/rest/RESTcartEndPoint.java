/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
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
import javax.ws.rs.core.MediaType;

import fr.imag.ejb.business.CartManagerEJB;
import fr.imag.entities.Cart;

@Path("/cart")
public class RESTcartEndPoint {
	@Inject private CartManagerEJB cartMngr;
    
    @POST
    @Path("/addToCart")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addToCart(String data) { 
		JsonReader r = Json.createReader(new StringReader(data));
		JsonObject obj = r.readObject();
		Cart cartToAdd = new Cart(obj);
		Cart cartFound = cartMngr.isPresentInTheCart(cartToAdd);
		if(cartFound == null){
			cartMngr.addToCart(cartToAdd);
		}else{
			cartFound.setQuantity(cartFound.getIdProduct()+1);
		}
    }
    
    @POST
    @Path("/removeToCart")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeToCart(String data) { 
		JsonReader r = Json.createReader(new StringReader(data));
		JsonObject obj = r.readObject();
		Cart cartToRemove = new Cart(obj);
		Cart cartFound = cartMngr.isPresentInTheCart(cartToRemove);
		if(cartFound == null){
			cartMngr.removeToCart(cartToRemove);
		}else{
			cartFound.setQuantity(cartFound.getIdProduct()-1);
		}
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/removeCart")
    public void removeCart(String data) {
		JsonReader r = Json.createReader(new StringReader(data));
		JsonObject obj = r.readObject();
    	cartMngr.removeCart(obj.getString("mail"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/increment")
    public void incrementOneProduct(String data) {
		JsonReader r = Json.createReader(new StringReader(data));
		JsonObject obj = r.readObject();
    	cartMngr.incrementOneProduct(obj.getInt("idProduct"), obj.getString("mail"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/decrement")
    public void decrementOneProduct(String data) {
		JsonReader r = Json.createReader(new StringReader(data));
		JsonObject obj = r.readObject();
    	cartMngr.decrementOneProduct(obj.getInt("idProduct"), obj.getString("mail"));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/pay")
    public void pay(String data) {
		JsonReader r = Json.createReader(new StringReader(data));
		JsonObject obj = r.readObject();
    	cartMngr.payCart(obj.getString("mail"));
    }

    

}
