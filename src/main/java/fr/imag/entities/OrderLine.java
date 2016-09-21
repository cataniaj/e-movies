/*
 *	Author:      Jérémy Leyvraz
 *	Date:        21 sept. 2016
 */

package fr.imag.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrderLine;
	public int getIdOrderLine() {
		return idOrderLine;
	}
	public void setIdOrderLine(int idOrderLine) {
		this.idOrderLine = idOrderLine;
	}

	private int quantity;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private int unitaryPrice;
	public int getUnitaryPrice() {
		return unitaryPrice;
	}
	public void setUnitaryPrice(int unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	@ManyToOne
	private OrderAll order;
	public OrderAll getOrder() {
		return order;
	}
	public void setOrder(OrderAll order) {
		this.order = order;
	}
	
	public OrderLine(){
		quantity = 0;
		order = new OrderAll();
		unitaryPrice = 0;
	}
	
	public void print(){
		System.out.print("id: "+idOrderLine);
		System.out.print(" quantity: "+quantity);
		System.out.println(" unitaryPrice: "+unitaryPrice);
	}
	
}
