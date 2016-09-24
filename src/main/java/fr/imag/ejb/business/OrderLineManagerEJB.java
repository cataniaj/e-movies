/*
 *	Author:      Jérémy Leyvraz
 *	Date:        24 sept. 2016
 */

package fr.imag.ejb.business;

import java.util.List;

import javax.ejb.EJB;

import fr.imag.ejb.dbaccess.OrderLineDatabaseAccessEJB;
import fr.imag.entities.Cart;
import fr.imag.entities.OrderAll;
import fr.imag.entities.OrderLine;

public class OrderLineManagerEJB {
	@EJB private OrderLineDatabaseAccessEJB dbAccess;

	public List<OrderLine> buildOrderLine(List<Cart> allCart, OrderAll order){
		return dbAccess.buildOrderLine(allCart, order);
	}
	
	public void printTable(){
		dbAccess.printTable();
	}
	
	public void clean(){
		dbAccess.clean();
	}
}
