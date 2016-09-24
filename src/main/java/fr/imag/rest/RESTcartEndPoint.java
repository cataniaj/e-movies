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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import fr.imag.ejb.business.CartManagerEJB;
import fr.imag.ejb.business.OrderAllManagerEJB;
import fr.imag.ejb.business.OrderLineManagerEJB;
import fr.imag.ejb.business.UserManagerEJB;
import fr.imag.entities.Cart;
import fr.imag.entities.User;

@Path("/cart")
public class RESTcartEndPoint {
	@Inject private CartManagerEJB cartMngr;
	
	// Pour test
	@Inject private UserManagerEJB userMngr;
	@Inject private OrderAllManagerEJB orderAllMngr;
	@Inject private OrderLineManagerEJB orderLineMngr;
	
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
			cartMngr.removeToCart(cartToRemove.getIdProduct(), cartToRemove.getMailUser());
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
    

    @GET
    @Path("/test")
    public void test() {
    	System.out.println("---------- TEST CART ----------\n");
    	
    	//Creation utilisateur 1 et 2
    	System.out.println("---------- Clean data ----------\n");
    	userMngr.clean();
    	cartMngr.clean();
    	orderAllMngr.clean();
    	orderLineMngr.clean();
    	
    	
    	User user1 = new User();
    	User user2 = new User();
    	user1.setMail("Client 1");
    	user2.setMail("Client 2");
    	userMngr.createNewAccount(user1);
    	userMngr.createNewAccount(user2);
    	
    	System.out.println("---------- Users 1  & 2 ----------\n");
    	userMngr.printTable();
    	
    	// Ajout des 3 films au panier du client 1
    	//{"idProduct":54,"title":"Spider-Man","year":"2002","support":"BR","stock":11,"price":7}
    	//{"idProduct":55,"title":"Spider-Man 3","year":"2007","support":"CN","stock":0,"price":9},
    	//{"idProduct":79,"title":"2 Fast 2 Furious","year":"2003","support":"CN","stock":0,"price":15}
    	Cart film1 = new Cart();
    	film1.setIdProduct(54);
    	film1.setMailUser("Client 1");
    	film1.setTitle("Spider-Man");
    	film1.setYear("2002");
    	film1.setQuantity(4);
    	film1.setUnitPrice(7);
    	film1.setSupport("BR");

    	Cart film2 = new Cart();
    	film2.setIdProduct(55);
    	film2.setMailUser("Client 1");
    	film2.setTitle("Spider-Man 3");
    	film2.setYear("2007");
    	film2.setQuantity(1);
    	film2.setUnitPrice(9);
    	film2.setSupport("CN");

    	Cart film3 = new Cart();
    	film3.setIdProduct(80);
    	film3.setMailUser("Client 1");
    	film3.setTitle("2 Fast 2 Furious");
    	film3.setYear("2003");
    	film3.setQuantity(4);
    	film3.setUnitPrice(15);
    	film3.setSupport("DVD");
    	
    	//{"idProduct":92,"title":"Predator","year":"1987","support":"DVD","stock":13,"price":9},
    	//{"idProduct":96,"title":"Grease","year":"1978","support":"BR","stock":16,"price":11}]}
    	Cart film4 = new Cart();
    	film4.setIdProduct(92);
    	film4.setMailUser("Client 2");
    	film4.setTitle("Predator");
    	film4.setYear("1987");
    	film4.setQuantity(3);
    	film4.setUnitPrice(9);
    	film4.setSupport("DVD");

    	Cart film5 = new Cart();
    	film5.setIdProduct(96);
    	film5.setMailUser("Client 2");
    	film5.setTitle("Grease");
    	film5.setYear("1978");
    	film5.setQuantity(7);
    	film5.setUnitPrice(11);
    	film5.setSupport("BR");
    	
    	System.out.println("---------- Initial cart user 1 ----------\n");
    	cartMngr.addToCart(film1);
    	cartMngr.addToCart(film2);
    	cartMngr.addToCart(film3);
    	cartMngr.printTable();
    	
    	System.out.println("---------- Initial cart user 2 ----------\n");
    	cartMngr.addToCart(film4);
    	cartMngr.addToCart(film5);
    	cartMngr.printTable();

    	System.out.println("---------- Remove one product to cart user 1: 80 ----------\n");
    	cartMngr.removeToCart(film3.getIdProduct(), user1.getMail());
    	cartMngr.printTable();

    	System.out.println("---------- Add 5 quantity to product 55 to user 1 ----------\n");
    	cartMngr.incrementOneProduct(film2.getIdProduct(), user1.getMail());
    	cartMngr.incrementOneProduct(film2.getIdProduct(), user1.getMail());
    	cartMngr.incrementOneProduct(film2.getIdProduct(), user1.getMail());
    	cartMngr.incrementOneProduct(film2.getIdProduct(), user1.getMail());
    	cartMngr.incrementOneProduct(film2.getIdProduct(), user1.getMail());
    	cartMngr.printTable();

    	System.out.println("---------- Remove 2 quantity to product 55 to user 1 ----------\n");
    	cartMngr.decrementOneProduct(film2.getIdProduct(), user1.getMail());
    	cartMngr.decrementOneProduct(film2.getIdProduct(), user1.getMail());
    	cartMngr.printTable();
    	
    	System.out.println("---------- Remove product 92 to cart user 2 ----------\n");
    	cartMngr.removeToCart(film4.getIdProduct(), user2.getMail());
    	cartMngr.printTable();
    	
    	System.out.println("---------- Pay cart user 1 ----------\n");
    	cartMngr.payCart(user1.getMail());
    	cartMngr.printTable();
    	
    	System.out.println("---------- Remove all cart user 2 ----------\n");
    	cartMngr.removeCart(user2.getMail());
    	cartMngr.printTable();

    	System.out.println("----------  All order ----------\n");
    	orderAllMngr.printTable();

    	System.out.println("----------  All order lines ----------\n");
    	orderLineMngr.printTable();

    	System.out.println("----------  Remove all user ----------\n");
    	userMngr.clean();
    	userMngr.printTable();

    	System.out.println("----------  All order ----------\n");
    	orderAllMngr.printTable();

    	System.out.println("----------  All order lines ----------\n");
    	orderLineMngr.printTable();
    }

    

}
