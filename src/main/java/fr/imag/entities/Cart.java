/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.entities;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCartItem;
	private String idProduct;
	private String mailUser;
	private String title;
	private String year;
	private String support;
	private String quantity;
	private String unitPrice;
	
	public Cart(){
	}
	
	public Cart(JsonObject item){
		idProduct = item.getString("idProduct");
		mailUser = item.getString("mail");
		title = item.getString("title");
		year = item.getString("year");
		support = item.getString("support");
		quantity = ""+1;
		unitPrice = item.getString("unitPrice");
	}

	public int getIdCartItem() {
		return idCartItem;
	}

	public void setIdCartItem(int idCartItem) {
		this.idCartItem = idCartItem;
	}

	public String getMailUser() {
		return mailUser;
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
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
	
	public JsonObject convertToJson(){
		JsonObject obj = Json.createObjectBuilder()
				.add("idProduct", idProduct)
				.add("mail", mailUser)
				.add("title", title)
				.add("year", year)
				.add("support", support)
				.add("quantity", quantity)
				.add("unitPrice", unitPrice)
				.build();
		return obj;
	}
	
	public void print(){
		System.out.println("idCartItem: "+idCartItem);
		System.out.println("idProduct: "+idProduct);
		System.out.println("mailUser: "+mailUser);
		System.out.println("title: "+title);
		System.out.println("year: "+year);
		System.out.println("support: "+support);
		System.out.println("quantity: "+quantity);
		System.out.println("unitPrice: "+unitPrice+"\n");
	}
	
}
