/*
 *	Author:      Jérémy Leyvraz
 *	Date:        24 sept. 2016
 */

package fr.imag.ejb.business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.json.JsonObject;

import fr.imag.ejb.dbaccess.OrderAllDatabaseAccessEJB;
import fr.imag.entities.OrderAll;

@Stateless
@Local
public class OrderAllManagerEJB {
	@EJB private OrderAllDatabaseAccessEJB dbAccess;

	public void validateOrder(OrderAll order){
		dbAccess.validateOrder(order);
	}
	
	public void printTable(){
		dbAccess.printTable();
	}
	
	public void clean(){
		dbAccess.clean();
	}
	
	public JsonObject getAllOrderLine(String user){
		return dbAccess.getAllOrderLine(user);
	}

}
