package fr.imag.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * This is the ejb which represents an user
 * @author Jeremy Leyvraz
 *
 */
@Entity
public class User {
	
	private String defaultValue = "N/A";
	
	@Id
	private String mail;
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	private String firstName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	private String lastName;
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private String zipcode;
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	private String city;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	private String country;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	private int coins;
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private Collection<OrderAll> allOrders;
	public Collection<OrderAll> getAllOrders() {
		return allOrders;
	}
	public void setAllOrders(Collection<OrderAll> allOrders) {
		this.allOrders = allOrders;
	}
	
	public User(){
		mail = defaultValue;
		password = defaultValue;
		firstName = defaultValue;
		lastName = defaultValue;
		address = defaultValue;
		zipcode = defaultValue;
		city = defaultValue;
		country = defaultValue;
		phone = defaultValue;
		coins = 0;
		allOrders = new ArrayList<OrderAll>();
	}

	public void addAnOrder(OrderAll order){
		this.allOrders.add(order);
	}
	
	public void print(){
		System.out.println("mail: "+mail);
		System.out.println("password: "+password);
		System.out.println("firstName: "+firstName);
		System.out.println("lastName: "+lastName);
		System.out.println("address: "+address);
		System.out.println("zipcode: "+zipcode);
		System.out.println("city: "+city);
		System.out.println("country: "+country);
		System.out.println("phone: "+phone);
		System.out.println("coins: "+coins);
		System.out.println("orders: "+allOrders);
	}

}
