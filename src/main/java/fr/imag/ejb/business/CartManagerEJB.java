/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.ejb.business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

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
	
	public void removeToCart(Cart item){
		dbAccess.removeToCart(item);
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
	
	// TODO Payer le panier
}
