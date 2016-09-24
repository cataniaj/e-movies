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
import fr.imag.entities.OrderAll;
import fr.imag.entities.OrderLine;
import fr.imag.entities.User;

@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CartDatabaseAccessEJB {
	@Inject EntityManager em;
	@Inject OrderLineDatabaseAccessEJB orderLineDBaccess;
	@Inject OrderAllDatabaseAccessEJB orderAllDBaccess;
	
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
	
	private List<Cart> allCartForUser(String user){
		Query query = em.createQuery("SELECT c FROM Cart c "+
				"WHERE c.user=:param");
		query.setParameter("param", user);
		return (List<Cart>) query.getResultList();
		
	}
	
	private int totalPrice(List<OrderLine> allCart){
		int total = 0;
		for(OrderLine ol : allCart){
			total += ol.getQuantity()*ol.getUnitaryPrice();
		}
		return total;
	}
	
	public void payCart(String user){
		
		User customer = em.find(User.class, user);
		
		// Create and save all order lines
		OrderAll order = new OrderAll();
    	List<Cart> allCart = allCartForUser(user);
    	List<OrderLine> allOrderLine = orderLineDBaccess.buildOrderLine(allCart, order);
    	
    	// Create and save the order
    	order.setUser(customer);
    	order.setAllOrderLine(allOrderLine);
    	order.setPrice(totalPrice(allOrderLine));
    	orderAllDBaccess.validateOrder(order);
    	
    	customer.addAnOrder(order);
	}
	

}
