/*
 *	Author:      Jérémy Leyvraz
 *	Date:        21 sept. 2016
 */

package fr.imag.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * L'EJB Entity représentant l'administrateur
 * @author Jerem
 *
 */
@Entity
public class Admin {

	@Id
	private String idAdmin;
	@Column(length= 20)
	private String password;
		
	/**
	 * Constructeur par défaut
	 */
	public Admin(){
		idAdmin = "N/A";
		password = "N/A";
	}

	public String getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Affiche en console les informations de l'admin
	 */
	public void print(){
		System.out.println("login: "+idAdmin);
		System.out.println("password: "+password);
	}
	
}
