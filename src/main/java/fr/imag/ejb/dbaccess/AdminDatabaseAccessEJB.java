/*
 *	Author:      Jérémy Leyvraz
 *	Date:        22 sept. 2016
 */

package fr.imag.ejb.dbaccess;

import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.imag.entities.Admin;
import fr.imag.entities.Product;

/**
 * L'EJB qui gère les accès à la table Admin
 * @author Jerem
 *
 */
@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class AdminDatabaseAccessEJB {
	@Inject EntityManager em;
	
	/**
	 * Renvoi la liste de tous les administateurs
	 * @return La liste de tous les administrateurs
	 */
	public List<Admin> allAdmin(){
    	Query query = em.createQuery("SELECT a FROM Admin a ");
    	List<Admin> adminList = query.getResultList();
    	return adminList;
	}
	
	/**
	 * Ajoute un produit dans la base
	 * @param p Le produit concerne
	 */
	public synchronized void addProduct(Product p){
		em.persist(p);
	}
	
}
