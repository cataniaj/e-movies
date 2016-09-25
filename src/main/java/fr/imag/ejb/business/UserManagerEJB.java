package fr.imag.ejb.business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.imag.ejb.dbaccess.UserDatabaseAccessEJB;
import fr.imag.entities.User;
import fr.imag.utilities.LoginData;
/**
 * This User EJB manages all the operations
 * about users
 * @author le06
 *
 */
@Stateless
@Local
public class UserManagerEJB {
	@EJB private UserDatabaseAccessEJB dbAccess;
	
	public void createNewAccount(User user){
		dbAccess.createNewAccount(user);
	}
	
	public User login(LoginData data){
		return dbAccess.login(data);
	}
	
	public void clean(){
		dbAccess.clean();
	}
	
	public void printTable(){
		dbAccess.printTable();
	}
}
