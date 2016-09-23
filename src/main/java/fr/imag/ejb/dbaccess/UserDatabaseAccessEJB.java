package fr.imag.ejb.dbaccess;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
	public synchronized void createNewAccount(User user){
		em.persist(user);
	}
	public synchronized User login(LoginData data){
		User res = em.find(User.class, data.getMail());
		return res;
	}
}
