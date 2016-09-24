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
	private int idProduct;
	private int quantity;
	private int unitaryPrice;

	@ManyToOne
	private OrderAll order;
	
	public OrderLine(){
		quantity = 0;
		order = null;
		unitaryPrice = 0;
	}
	
	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(int unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public OrderAll getOrder() {
		return order;
	}

	public void setOrder(OrderAll order) {
		this.order = order;
	}

	public int getIdOrderLine() {
		return idOrderLine;
	}

	public void print(){
		System.out.print("idOrderLine: "+idOrderLine);
		System.out.print("idProduct: "+idProduct);
		System.out.print(" quantity: "+quantity);
		System.out.println(" unitaryPrice: "+unitaryPrice);
	}
	
}
