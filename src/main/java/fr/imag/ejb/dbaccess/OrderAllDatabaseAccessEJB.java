/*
 *	Author:      Jérémy Leyvraz
 *	Date:        24 sept. 2016
 */

package fr.imag.ejb.dbaccess;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.imag.entities.OrderAll;

public class OrderAllDatabaseAccessEJB {
	@Inject EntityManager em;
	
	public void validateOrder(OrderAll order){
		em.persist(order);
	}

}
