/*
 *	Author:      Jérémy Leyvraz
 *	Date:        21 sept. 2016
 */

package fr.imag.rest;

import java.io.IOException;
import java.io.StringReader;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.imag.entities.Admin;
import fr.imag.entities.MovieList;
import fr.imag.themoviedatabase.AdminRegistration;


@Path("/admin")
public class RESTadminEndPoint {
	private String jsonLogin = "login";
	private String jsonPwd = "pwd";
	
	private String jsonIdProduct = "idProduct";
	private String jsonStock = "stock";
	private String jsonPrice = "price";
	
    @Inject
    AdminRegistration adminRegistration;
	
    @GET
    @Path("/getProduct")
    public String getProduct() {
    	MovieList allMovie;
    	allMovie = adminRegistration.allMovies();
        return allMovie.convertToJsonArray().toString();
    }
	
    @GET
    @Path("/secretDoor")
    public String chargeManyMovies() throws IOException {
    	adminRegistration.chargeAllMovies();
    	return "WhY sO sErIoUs ? o_o";
    }
	
    @GET
    @Path("/redemption")
    public String removeAllMovies() throws IOException {
    	adminRegistration.cleanMovies();
    	return "HaStA lA vIsTa, BaBy !";
    }
    
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String data) { 
   
		JsonReader r = Json.createReader(new StringReader(data));
		JsonObject obj = r.readObject();

		JsonObject result;
		String login = obj.getString(jsonLogin);
		String pwd = obj.getString(jsonPwd);
		Admin admin = new Admin();
		admin.setIdAdmin(login);
		admin.setPassword(pwd);
		if(adminRegistration.isAdmin(admin)){
			result = Json.createObjectBuilder()				
					.add("result", "true")
					.build();
		}else{
			result = Json.createObjectBuilder()				
					.add("result", "false")
					.build();
		}
    	
        return Response.status(201).entity(result).build(); 
    }
    
    @POST
    @Path("/updateProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateStock(String data) { 
		JsonReader r = Json.createReader(new StringReader(data));
		JsonObject obj = r.readObject();
		String idProduct = String.valueOf(obj.getInt(jsonIdProduct));
		String stock = obj.getString(jsonStock);
		String price = obj.getString(jsonPrice);
		adminRegistration.updateProduct(idProduct, stock, price);
        return "/updateProduct ok"; 
    }
    
    @POST
    @Path("/addMovie")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addMovie(String product) {
		adminRegistration.addMovie(product);
        return "/updateProduct ok"; 
    }
}
