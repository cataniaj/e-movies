/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.ejb.business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.json.JsonObject;

import fr.imag.ejb.dbaccess.CartDatabaseAccessEJB;
import fr.imag.entities.Cart;

@Stateless
@Local
public class CartManagerEJB {
	@EJB private CartDatabaseAccessEJB dbAccess;
	
	public Cart isPresentInTheCart(Cart item){
		return dbAccess.isPresentInTheCart(item);
	}
	
	public void addToCart(Cart item){
		dbAccess.addToCart(item);
	}
	
	public void removeToCart(int idProduct, String mail){
		dbAccess.removeToCart(idProduct, mail);
	}
	
	public void removeCart(String mail){
		dbAccess.removeCart(mail);
	}
	
	public void incrementOneProduct(int idProduct,  String mail){
		dbAccess.incrementOneProduct(idProduct, mail);
	}
	
	public void decrementOneProduct(int idProduct,  String mail){
		dbAccess.decrementOneProduct(idProduct, mail);
	}
	
	public void payCart(String user){
		dbAccess.payCart(user);
	}
	
	public void clean(){
		dbAccess.clean();
	}
	
	public void printTable(){
		dbAccess.printTable();
	}
	
	public JsonObject cartInJson(String user){
		return dbAccess.convertToJsonArray(user);
	}
}
