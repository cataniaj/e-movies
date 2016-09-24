/*
 *	Author:      Jérémy Leyvraz
 *	Date:        24 sept. 2016
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

import fr.imag.entities.OrderAll;

@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class OrderAllDatabaseAccessEJB {
	@Inject EntityManager em;
	
	public void validateOrder(OrderAll order){
		em.persist(order);
	}
	
	public void printTable(){
    	Query query = em.createQuery("SELECT o FROM OrderAll o");
    	List<OrderAll> allOrder = (List<OrderAll>) query.getResultList();
    	for(OrderAll order : allOrder){
    		order.print();
    	}
	}
	
	public void clean(){
    	Query query = em.createQuery("SELECT c FROM OrderAll c");
    	List<OrderAll> allOrder = (List<OrderAll>) query.getResultList();
    	for(OrderAll order : allOrder){
    		em.remove(order);
    	}
	}

}
