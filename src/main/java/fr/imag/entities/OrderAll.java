/*
 *	Author:      Jérémy Leyvraz
 *	Date:        21 sept. 2016
 */

package fr.imag.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This is the ejb which represents an order
 * @author Jeremy Leyvraz
 *
 */
@Entity
public class OrderAll {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrder;

	private int price;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	private User user;

	@OneToMany(mappedBy="order", cascade=CascadeType.REMOVE)
	private Collection<OrderLine> allOrderLine;
	public Collection<OrderLine> getAllOrderLine() {
		return allOrderLine;
	}
	public void setAllOrderLine(Collection<OrderLine> allOrderLine) {
		this.allOrderLine = allOrderLine;
	}

	public OrderAll(){
		price = 0;
		Calendar today = Calendar.getInstance();
		date = today.getTime();
		user = new User();
		allOrderLine = new ArrayList<OrderLine>();
	}

	public int getIdOrder() {
		return idOrder;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.user.addAnOrder(this);
	}
	
	public void addOneOrderLine(OrderLine orderLine){
		allOrderLine.add(orderLine);
	}
	
	public void print(){
		System.out.println("idOrder: "+ idOrder);
		System.out.println("date: "+date);
		System.out.println("price: "+price);
		System.out.println("user: "+user);
		for(OrderLine o : allOrderLine){
			o.print();
		}
	}

}
