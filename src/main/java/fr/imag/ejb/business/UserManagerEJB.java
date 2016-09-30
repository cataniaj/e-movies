package fr.imag.ejb.business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.json.JsonObject;

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
	
	/**
	 * Creation d'un nouveau compte client
	 * @param user L'utilisateur a créer
	 * @return true or false
	 */
	public boolean createNewAccount(User user){
		return dbAccess.createNewAccount(user);
	}
	
	public User login(LoginData data){
		return dbAccess.login(data);
	}
	
	/**
	 * Efface la table User
	 */
	public void clean(){
		dbAccess.clean();
	}
	
	/**
	 * Donne les informations d'un utilisateur au format Json
	 * @param mail Le client concerne
	 * @return Les informations au format Json
	 */
	public JsonObject getInformation(String mail){
		return dbAccess.getInformation(mail);
	}
	
	/**
	 * Affiche la table User
	 */
	public void printTable(){
		dbAccess.printTable();
	}
}
