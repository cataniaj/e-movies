/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.ejb.dbaccess;

import java.io.StringReader;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.imag.ejb.business.OrderAllManagerEJB;
import fr.imag.ejb.business.OrderLineManagerEJB;
import fr.imag.entities.Cart;
import fr.imag.entities.OrderAll;
import fr.imag.entities.OrderLine;
import fr.imag.entities.User;
/**
 * L'EJB qui gère les accès à la table Cart
 * @author Jerem
 *
 */
@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CartDatabaseAccessEJB {
	@Inject EntityManager em;
	@Inject OrderAllManagerEJB orderAllMngr;
	@Inject OrderLineManagerEJB orderLineMngr;
	
	/**
	 * Recherche si un produit est présent dans le panier
	 * @param item Le produit concerne
	 * @return Le produit trouvé ou null
	 */
	public Cart isPresentInTheCart(Cart item){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getIdProduct().compareTo(item.getIdProduct())==0
    				&& (cart.getMailUser().compareTo(item.getMailUser())==0)){
    			return cart;
    		}
    	}
    	return null;
	}
	
	/**
	 * Efface le panier d'un utilisateur
	 * @param mail L'utilisateur concerne
	 */
	public void removeCart(String mail){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getMailUser().compareTo(mail) == 0){
    			em.remove(cart);
    		}
    	}
	}
	
	/**
	 * Ajoute au panier un produit
	 * @param item Le produit concerne
	 */
	public void addToCart(Cart item){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getIdProduct().compareTo(item.getIdProduct())==0
    				&& (cart.getMailUser().compareTo(item.getMailUser())==0)){
    			Cart c = em.find(Cart.class, cart.getIdCartItem());
    			c.setQuantity(String.valueOf(Integer.parseInt(c.getQuantity())+1));
    			//em.persist(c);
    			return;
    		}
    	}
		em.persist(item);
	}
	
	/**
	 * Retire du panier un produit 
	 * @param idProduct Le produit a retire
	 * @param mail L'utilisateur concerne
	 */
	public void removeToCart(String idProduct, String mail){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getMailUser().compareTo(mail) == 0
    				&& cart.getIdProduct().compareTo(idProduct)==0){
    			em.remove(cart);
    		}
    	}
	}
	
	/**
	 * Ajoute une quantité d'un produit deja présent
	 * @param idProduct Le produit concerne
	 * @param mail L'utilisateur concerne
	 */
	public void incrementOneProduct(String idProduct,  String mail){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getMailUser().compareTo(mail) == 0
    				&& cart.getIdProduct().compareTo(idProduct)==0
    				&& cart.getSupport().compareTo("Copie Numerique")!=0){
    			Cart c = em.find(Cart.class, cart.getIdCartItem());
    			c.setQuantity(String.valueOf(Integer.parseInt(c.getQuantity())+1));
    		}
    	}
	}
	
	/**
	 * Retire une quantité d'un produit deja present
	 * @param idProduct Le produit concerne
	 * @param mail L'utilisateur concerne
	 */
	public void decrementOneProduct(String idProduct,  String mail){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getMailUser().compareTo(mail) == 0
    				&& cart.getIdProduct().compareTo(idProduct)==0){
    			cart.setQuantity(
    					String.valueOf(Integer.parseInt(cart.getQuantity())-1)) ;
    		}
    	}
	}
	
	/**
	 * Renvoie le panier d'un utilisateur
	 * @param user L'utilisateur concerne
	 * @return Une liste de "Cart" représentant le panier
	 */
	private List<Cart> allCartForUser(String user){
		Query query = em.createQuery("SELECT c FROM Cart c "+
				"WHERE c.mailUser=:param");
		query.setParameter("param", user);
		return (List<Cart>) query.getResultList();
	}
	
	/**
	 * Calcule le prix total du panier
	 * @param allCart Le panier
	 * @return Le prix du panier
	 */
	private int totalPrice(List<OrderLine> allCart){
		int total = 0;
		for(OrderLine ol : allCart){
			total += Integer.parseInt(ol.getQuantity())*Integer.parseInt(ol.getUnitaryPrice());
		}
		return total;
	}
	
	/**
	 * Calcule le prix total du panier
	 * @param allCart Le panier
	 * @return Le prix du panier
	 */
	private int totalPriceCart(List<Cart> allCart){
		int total = 0;
		for(Cart c : allCart){
			total += Integer.parseInt(c.getQuantity())*Integer.parseInt(c.getUnitPrice());
		}
		return total;
	}
	
	/**
	 * Paye le panier d'un utilisateur
	 * @param user L'utilisateur concerne
	 */
	public void payCart(String user){
		
		User customer = em.find(User.class, user);
		
		// Create and save all order lines
		OrderAll order = new OrderAll();
    	List<Cart> allCart = allCartForUser(user);
    	List<OrderLine> allOrderLine = orderLineMngr.buildOrderLine(allCart, order);
    	
    	// Create and save the order
    	order.setUser(customer);
    	order.setAllOrderLine(allOrderLine);
    	order.setPrice(totalPrice(allOrderLine));
    	orderAllMngr.validateOrder(order);
    	
    	customer.addAnOrder(order);
    	
    	// Remove all cart entries for user
    	for(Cart cart : allCart){
    		if(cart.getMailUser().compareTo(user)==0){
        		em.remove(cart);
    		}
    	}
    	
	}
	
	/**
	 * Efface la table Cart
	 */
	public void clean(){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		em.remove(cart);
    	}
	}
	
	/**
	 * Affiche la table en console
	 */
	public void printTable(){
    	Query query = em.createQuery("SELECT c FROM Cart c ");
    	List<Cart> cartList = (List<Cart>) query.getResultList();
    	for(Cart c : cartList){
    		c.print();
    	}
	}

	/**
	 * Convertit le panier d'un utilisateur au format Json 
	 * @param user L'utilisateur concerne
	 * @return Le panier au format Json
	 */
	public JsonObject convertToJsonArray(String user){
		List<Cart> allCart = allCartForUser(user);
		String cartListJson = "{\"cart\":[";
		for(int i=0 ; i<allCart.size() ; i++){
			if(i==0){
				cartListJson = cartListJson + allCart.get(i).convertToJson().toString();
			}else{
				cartListJson = cartListJson + "," + allCart.get(i).convertToJson().toString();
			}
		}
		cartListJson = cartListJson + "], \"totalPrice\":\"" + totalPriceCart(allCart) + "\"}";
		JsonReader r = Json.createReader(new StringReader(cartListJson));
		JsonObject obj = r.readObject();
		return obj;
	}
	
}
