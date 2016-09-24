/*
 *	Author:      Jérémy Leyvraz
 *	Date:        24 sept. 2016
 */

package fr.imag.ejb.dbaccess;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.imag.entities.Cart;
import fr.imag.entities.OrderAll;
import fr.imag.entities.OrderLine;

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

}
