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

/**
 * Le manager de la table OrderLine
 * @author Jerem
 *
 */

public class OrderLineManagerEJB {
	@EJB private OrderLineDatabaseAccessEJB dbAccess;

	/**
	 * Construit la commande à partir d'un panier
	 * @param allCart La liste des items du panier a valider
	 * @param order La commande a créer
	 * @return La liste des lignes de commande créée
	 */
	public List<OrderLine> buildOrderLine(List<Cart> allCart, OrderAll order){
		return dbAccess.buildOrderLine(allCart, order);
	}
	
	/**
	 * Affiche la table OrderLine en console
	 */
	public void printTable(){
		dbAccess.printTable();
	}
	
	/**
	 * Efface la table OrderLine
	 */
	public void clean(){
		dbAccess.clean();
	}
}
