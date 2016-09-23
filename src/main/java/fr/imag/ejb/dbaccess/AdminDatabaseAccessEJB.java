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

@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class AdminDatabaseAccessEJB {
	@Inject EntityManager em;
	
	public List<Admin> allAdmin(){
    	Query query = em.createQuery("SELECT a FROM Admin a ");
    	List<Admin> adminList = query.getResultList();
    	return adminList;
	}
	
	public synchronized void addProduct(Product p){
		em.persist(p);
	}
	
}
