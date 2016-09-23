/*
 *	Author:      Jérémy Leyvraz
 *	Date:        21 sept. 2016
 */

package fr.imag.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CATEGORY")
public abstract class Product {
	
	@Transient
	protected String defaultValue = "N/A";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int idProduct;
	protected String support;
	protected int price;
	protected int stock;
		
	public Product(){
		support = "DVD";
		stock = 0;
		price = 0;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void print(){
		System.out.println("idProduct: "+idProduct);
		System.out.println("support: "+support);
		System.out.println("price: "+price);
		System.out.println("stock: "+stock);
	}
		
}
