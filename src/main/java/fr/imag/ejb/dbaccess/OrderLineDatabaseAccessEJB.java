/*
 *	Author:      Jérémy Leyvraz
 *	Date:        24 sept. 2016
 */

package fr.imag.ejb.dbaccess;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.imag.entities.Cart;
import fr.imag.entities.Movie;
import fr.imag.entities.OrderAll;
import fr.imag.entities.OrderLine;

/**
 * L'EJB qui gère les acces à la table OrderLine
 * @author Jerem
 *
 */
@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class OrderLineDatabaseAccessEJB {
	@Inject EntityManager em;

	/**
	 * Construit une commande
	 * @param allCart La liste des produits représentant le panier 
	 * @param order La commande a créer
	 * @return La liste de toutes les lignes de commandes
	 */
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
	
	/**
	 * Renvoie la liste de toutes les lignes de commandes d'une commande
	 * @param idOrder La commande concernee
	 * @return La liste de toutes les lignes de commande au format Json
	 */
	public JsonObject getAllOrderLine(String idOrder){
		Query query = em.createQuery("SELECT o FROM OrderLine o");
		List<OrderLine> orderLineList = (List<OrderLine>) query.getResultList();

		String orderLineListJson = "{\"orderLine\":[";
		boolean first = true;
		
		for(OrderLine orderLine : orderLineList){
			if(Integer.parseInt(idOrder) == orderLine.getOrder().getIdOrder()){
				int idProduct = Integer.parseInt(orderLine.getIdProduct());
				Movie m = em.find(Movie.class, idProduct);
				String title = m.getTitle();
				String year = m.getYear();
				JsonObject obj = Json.createObjectBuilder()
					.add("idProduct", orderLine.getIdProduct())
					.add("title", title)
					.add("year", year)
					.add("quantity", orderLine.getQuantity())
					.add("unitaryPrice", orderLine.getUnitaryPrice())
					.build();
				if(first){
					orderLineListJson = orderLineListJson + obj.toString();
					first = false;
				}else{
					orderLineListJson = orderLineListJson + "," + obj.toString();
				}
			}
		}
	
		orderLineListJson = orderLineListJson + "]}";
		JsonReader r = Json.createReader(new StringReader(orderLineListJson));
		JsonObject obj = r.readObject();
		return obj;
	}
	
	/**
	 * Affiche la table en console
	 */
	public void printTable(){
    	Query query = em.createQuery("SELECT o FROM OrderLine o");
    	List<OrderLine> allOrder = (List<OrderLine>) query.getResultList();
    	for(OrderLine order : allOrder){
    		order.print();
    	}
	}
	
	/**
	 * Efface la table
	 */
	public void clean(){
    	Query query = em.createQuery("SELECT c FROM OrderLine c");
    	List<OrderLine> allOrder = (List<OrderLine>) query.getResultList();
    	for(OrderLine orderLine : allOrder){
    		em.remove(orderLine);
    	}
	}

}
