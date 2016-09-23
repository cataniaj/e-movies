/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.ejb.dbaccess;

import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.imag.entities.Cart;

@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CartDatabaseAccessEJB {
	@Inject EntityManager em;
	
	public Cart isPresentInTheCart(Cart item){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getIdProduct() == item.getIdProduct()
    				&& (cart.getMailUser().compareTo(item.getMailUser())==0)){
    			return cart;
    		}
    	}
    	return null;
	}
	
	public void removeCart(String mail){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getMailUser().compareTo(mail) == 0){
    			em.remove(cart);
    		}
    	}
	}
	
	public void addToCart(Cart item){
		em.persist(item);
	}
	
	public void removeToCart(Cart item){
		em.remove(item);
	}
	
	public void incrementOneProduct(int idProduct,  String mail){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getMailUser().compareTo(mail) == 0
    				&& cart.getIdProduct()==idProduct){
    			cart.setQuantity(cart.getQuantity()+1);
    		}
    	}
	}
	
	public void decrementOneProduct(int idProduct,  String mail){
    	Query query = em.createQuery("SELECT c FROM Cart c");
    	List<Cart> allCart = (List<Cart>) query.getResultList();
    	for(Cart cart : allCart){
    		if(cart.getMailUser().compareTo(mail) == 0
    				&& cart.getIdProduct()==idProduct){
    			cart.setQuantity(cart.getQuantity()-1);
    		}
    	}
	}
	
	//TODO Payer le panier
	

}
