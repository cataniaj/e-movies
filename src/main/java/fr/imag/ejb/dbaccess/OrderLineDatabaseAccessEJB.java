/*
 *	Author:      Jérémy Leyvraz
 *	Date:        24 sept. 2016
 */

package fr.imag.ejb.dbaccess;

import java.util.ArrayList;
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

@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class OrderLineDatabaseAccessEJB {
	@Inject EntityManager em;

	public List<OrderLine> buildOrderLine(List<Cart> allCart, OrderAll order){
		List<OrderLine> result = new ArrayList<OrderLine>();
		for(Cart c : allCart){
			OrderLine orderLine = new OrderLine();
			orderLine.setIdProduct(c.getIdProduct());
			orderLine.setQuantity(c.getQuantity());
			orderLine.setOrder(order);
			orderLine.setUnitaryPrice(c.getUnitPrice());
			em.persist(orderLine);
			result.add(orderLine);
		}
		return result;
	}
	
	public void printTable(){
    	Query query = em.createQuery("SELECT o FROM OrderLine o");
    	List<OrderLine> allOrder = (List<OrderLine>) query.getResultList();
    	for(OrderLine order : allOrder){
    		order.print();
    	}
	}
	
	public void clean(){
    	Query query = em.createQuery("SELECT c FROM OrderLine c");
    	List<OrderLine> allOrder = (List<OrderLine>) query.getResultList();
    	for(OrderLine orderLine : allOrder){
    		em.remove(orderLine);
    	}
	}

}
