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
import fr.imag.entities.Cart;
import fr.imag.entities.User;
import fr.imag.utilities.LoginData;

/**
 * This EJB performs all the db accesses
 * concerning the users
 * @author le06
 *
 */
@Singleton
@Local
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UserDatabaseAccessEJB {
	
	@Inject EntityManager em;
	public synchronized boolean createNewAccount(User user){
		if(em.contains(user)){
			return false;
		}
		try{
			em.persist(user);
		}catch (Exception e){
			return false;
		}		
		return true;
	}
	
	public synchronized User login(LoginData data){
		User res = em.find(User.class, data.getMail());
		return res;
	}
	
	public void clean(){
    	Query query = em.createQuery("SELECT u FROM User u");
    	List<User> allUser = (List<User>) query.getResultList();
    	for(User u : allUser){
    		em.remove(u);
    	}
	}
	
	public void printTable(){
    	Query query = em.createQuery("SELECT u FROM User u ");
    	List<User> userList = query.getResultList();
    	for(User u : userList){
    		u.print();
    	}
	}
}
