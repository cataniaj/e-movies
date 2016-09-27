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

import fr.imag.entities.Movie;
import fr.imag.entities.OrderAll;
import fr.imag.entities.OrderLine;

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
	
	private boolean contains(List<OrderLine> list, int idProduct){
		for(OrderLine order : list){
			Movie m = em.find(Movie.class, order.getIdProduct());
			if(idProduct == m.getIdProduct()){
				return true;
			}
		}
		return false;
	}
	
	public JsonObject getAllOrder(String user){
		Query query = em.createQuery("SELECT o FROM OrderAll o");
		List<OrderAll> orderList = (List<OrderAll>) query.getResultList();
		ArrayList<OrderLine> result = new ArrayList<OrderLine>();

		String orderLineListJson = "{\"order\":[";
		boolean first = true;
		for(OrderAll order : orderList){
			if(order.getUser().getMail().compareTo(user) == 0){
				JsonObject obj = Json.createObjectBuilder()
					.add("idOrder", order.getIdOrder())
					.add("date", order.getDate())
					.add("price", order.getPrice())
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
	
	//TODO: Mes achats
	public JsonObject getAllOrderLine(String user){
		Query query = em.createQuery("SELECT o FROM OrderAll o");
		List<OrderAll> orderList = (List<OrderAll>) query.getResultList();
		ArrayList<OrderLine> result = new ArrayList<OrderLine>();

		String orderLineListJson = "{\"order\":[";
		boolean first = true;
		for(OrderAll order : orderList){
			if(order.getUser().getMail().compareTo(user) == 0){
				for(OrderLine orderLine : order.getAllOrderLine()){
					Movie movie = em.find(Movie.class, orderLine.getIdProduct());
					if(!contains(result, movie.getIdTMDB())){
						result.add(orderLine);
						int idProduct = Integer.parseInt(orderLine.getIdProduct());
						Movie m = em.find(Movie.class, idProduct);
						String title = m.getTitle();
						String year = m.getYear();
						JsonObject obj = Json.createObjectBuilder()
								.add("idProduct", orderLine.getIdProduct())
								.add("idOrder", order.getIdOrder())
								.add("title", title)
								.add("year", year)
								.add("review", false)
								.add("date", order.getDate())
								.build();
						if(first){
							orderLineListJson = orderLineListJson + obj.toString();
							first = false;
						}else{
							orderLineListJson = orderLineListJson + "," + obj.toString();
						}
					}
				}
			}
		}

		orderLineListJson = orderLineListJson + "]}";
		JsonReader r = Json.createReader(new StringReader(orderLineListJson));
		JsonObject obj = r.readObject();
		return obj;
	}

}
