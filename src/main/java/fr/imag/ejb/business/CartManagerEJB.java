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

/**
 * Le manager de la table Cart
 * @author Jerem
 *
 */

@Stateless
@Local
public class CartManagerEJB {
	@EJB private CartDatabaseAccessEJB dbAccess;
	
	/**
	 * Recherche si un produit est present dans le panier
	 * @param item Le produit a chercher dans le panier
	 * @return true or false
	 */
	public Cart isPresentInTheCart(Cart item){
		return dbAccess.isPresentInTheCart(item);
	}
	
	/**
	 * Ajoute un produit au panier
	 * @param item Le produit a ajouter au panier
	 */
	public void addToCart(Cart item){
		dbAccess.addToCart(item);
	}
	
	/**
	 * Retire un produit du panier
	 * @param idProduct Le produit a retirer
	 * @param mail Le client du panier
	 */
	public void removeToCart(String idProduct, String mail){
		dbAccess.removeToCart(idProduct, mail);
	}
	
	/**
	 * Vide le panier d'un utilisateur
	 * @param mail Le client dont le panier doit etre vide
	 */
	public void removeCart(String mail){
		dbAccess.removeCart(mail);
	}
	
	/**
	 * Ajoute une quantite d'un produit dans le panier
	 * @param idProduct Le produit a ajouter
	 * @param mail Le client concerne
	 */
	public void incrementOneProduct(String idProduct,  String mail){
		dbAccess.incrementOneProduct(idProduct, mail);
	}
	
	/**
	 * Retire une quantite d'un produit dans le panier
	 * @param idProduct Le produit a ajouter
	 * @param mail Le client concerne
	 */
	public void decrementOneProduct(String idProduct,  String mail){
		dbAccess.decrementOneProduct(idProduct, mail);
	}
	
	/**
	 * Payer le panier
	 * @param user Le client qui souhaite payer
	 */
	public void payCart(String user){
		dbAccess.payCart(user);
	}
	
	/**
	 * Efface la table Cart
	 */
	public void clean(){
		dbAccess.clean();
	}
	
	/**
	 * Affiche la table en console
	 */
	public void printTable(){
		dbAccess.printTable();
	}
	
	/**
	 * Convertit le panier d'un client en json
	 * @param user Le client concerne
	 * @return Le panier du client au format json
	 */
	public JsonObject cartInJson(String user){
		return dbAccess.convertToJsonArray(user);
	}
}
