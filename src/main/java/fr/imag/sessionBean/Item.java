/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.sessionBean;

import javax.json.JsonObject;

public class Item {
	private int idProduct;
	private String title; 
	private String year;
	private String support;
	private int quantity;
	private int unitPrice;
	private int subTotal;
	
	public Item(JsonObject objItem){
		idProduct = objItem.getInt("idProduct");
		title = objItem.getString("title");
		year = objItem.getString("year");
		support = objItem.getString("support");
		quantity = objItem.getInt("quantity");
		unitPrice = objItem.getInt("unitPrice");
		subTotal = objItem.getInt("subTotal");
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

}
