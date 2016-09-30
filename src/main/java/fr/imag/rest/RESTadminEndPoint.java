/*
 *	Author:      Jérémy Leyvraz
 *	Date:        21 sept. 2016
 */

package fr.imag.rest;

import java.io.StringReader;
import java.util.ArrayList;

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
import fr.imag.entities.Movie;
import fr.imag.entities.MovieList;
import fr.imag.themoviedatabase.AdminRegistration;

/**
 * Module REST pour les acces admin
 * @author Jerem
 *
 */

@Path("/admin")
public class RESTadminEndPoint {
	private String jsonLogin = "login";
	private String jsonPwd = "pwd";
	
	private String jsonIdProduct = "idProduct";
	private String jsonStock = "stock";
	private String jsonPrice = "price";
	
    @Inject
    AdminRegistration adminRegistration;
	
    /**
     * Initialise la base de données
     */
    @GET
    @Path("/reboot")
    public void reboot() {
    	adminRegistration.cleanAll();
    	adminRegistration.chargeAllMovies();
    }
	
    /**
     * Renvoi les produits
     * @return Les produits au format json
     */
    @GET
    @Path("/getProduct")
    public String getProduct() {
    	MovieList allMovie;
    	allMovie = adminRegistration.allMovies();
        return allMovie.convertToJsonArray().toString();
    }
	
    /**
     * Renvoie la liste des commandes au format Json
     * @param user L'utilisateur concerne
     * @return La liste des commandes de l'utilisateurs au format json 
     */
    @POST
    @Path("/getOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getOrder(String user) {
        return adminRegistration.allOrder(user);
    }
	
    /**
     * Renvoie les informations de l'utilisateur 
     * @param user L'utilisateur concerne
     * @return Les informations au format json
     */
    @POST
    @Path("/infoUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String infoUser(String user) {
        return adminRegistration.infoUser(user);
    }
	
    /**
     * Efface un utilisateur
     * @param user L'utilisateur concerne
     * @return true or false en json
     */
    @POST
    @Path("/removeUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String removeUser(String user) {
        return adminRegistration.removeUser(user);
    }
	
    /**
     * Renvoie la liste des lignes des commandes d'une commande
     * @param idOrder La commande concernee
     * @return La liste des lignes commandes au format json
     */
    @POST
    @Path("/getOrderLine")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getOrderLine(String idOrder) {
        return adminRegistration.getAllOrderLine(idOrder);
    }
	
    /**
     * Renvoie la liste des utilisateurs inscrits
     * @return La liste des utilisateurs au format json
     */
    @GET
    @Path("/getUsers")
    @Produces("application/json")
    public ArrayList<String> getUsers() {
    	return adminRegistration.allUser();
    }
	
    /**
     * Renvoie la liste de tous les films
     * @return La liste de tous les films au format json
     */
    @GET
    @Path("/allMovies")
    public String getAllMovies() {
    	MovieList allMovies;
    	String result = "";
    	allMovies = adminRegistration.allMovies();
    	for(Movie m : allMovies){
    		result = result + "\n" + m.convertToJsonFull().toString();
    	}
        return result;
    }
    
    /**
     * Verifie la connexion d'un administrateur
     * @param data Les informations de connexion: login, password
     * @return true ou false dans un json
     */
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
    
    /**
     * Demande la mise a jour d'un produit
     * @param data Un json contenant les champs: (idProduct, stock, price)
     * @return une chaine "updateProduct ok"
     */
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
    
    /**
     * Ajoute un film dans la base de données
     * @param product Le film a ajouter au format json
     * @return une chaine "updateProduct ok"
     */
    @POST
    @Path("/addMovie")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addMovie(String product) {
		adminRegistration.addMovie(product);
        return "/updateProduct ok"; 
    }
}
