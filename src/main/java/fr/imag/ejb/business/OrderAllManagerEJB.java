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

/**
 * Le manager de la table OrderAll
 * @author Jerem
 *
 */


@Stateless
@Local
public class OrderAllManagerEJB {
	@EJB private OrderAllDatabaseAccessEJB dbAccess;

	/**
	 * Valide la commande
	 * @param order La commande a valider
	 */
	public void validateOrder(OrderAll order){
		dbAccess.validateOrder(order);
	}
	
	/**
	 * Affiche en console la table OrderAll
	 */
	public void printTable(){
		dbAccess.printTable();
	}
	
	/**
	 * Efface la table OrderAll
	 */
	public void clean(){
		dbAccess.clean();
	}
	
	/**
	 * Convertit au format json toutes les commandes d'un client
	 * @param user Le client concerne
	 * @return La liste des produits commandes par le client concerne
	 */
	public JsonObject getAllOrderLine(String user){
		return dbAccess.getAllOrderLine(user);
	}

}
