package fr.imag.ejb.dbaccess;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	/**
	 * Creer un nouveau compte utilisateur 
	 * @param user L'utilisateur a créer
	 * @return true or false
	 */
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
	
	// TODO changer le retour
	/**
	 * Efface un utilisateur
	 * @param user L'utilisateur a effacer
	 * @return "ok"
	 */
	public synchronized String removeUser(String user){
		User res = em.find(User.class, user);
		em.remove(res);
		return "ok";
	}
	
	/**
	 * Renvoie les information d'un utilisateur
	 * @param user L'utilisateur concerne
	 * @return Une chaine contenant les informations au format Json
	 */
	public synchronized String infoUser(String user){
		User res = em.find(User.class, user);
		return res.convertToJson().toString();
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public synchronized User login(LoginData data){
		User res = em.find(User.class, data.getMail());
		return res;
	}
	
	/**
	 * Efface la table
	 */
	public void clean(){
    	Query query = em.createQuery("SELECT u FROM User u");
    	List<User> allUser = (List<User>) query.getResultList();
    	for(User u : allUser){
    		em.remove(u);
    	}
	}
	
	/**
	 * Renvoie la liste de tous les identifiants des utilisateurs
	 * @return La liste de tous les identifiant des utilisateurs
	 */
	public ArrayList<String> all(){
    	Query query = em.createQuery("SELECT u FROM User u ");
    	List<User> allUser =  (List<User>) query.getResultList();
    	ArrayList<String> allUserString = new ArrayList<String>();
    	for(User u : allUser){
    		allUserString.add(u.getMail());
    	}
    	return allUserString;
	}
	
	/**
	 * Renvoie les informations d'un utilisateur au format Json
	 * @param mail L'utilisateur concerne
	 * @return Les informations au format Json
	 */
	public JsonObject getInformation(String mail){
		User res = em.find(User.class, mail);
		return res.convertToJson();
	}
	
	/**
	 * Affiche la table en console
	 */
	public void printTable(){
    	Query query = em.createQuery("SELECT u FROM User u ");
    	List<User> userList = query.getResultList();
    	for(User u : userList){
    		u.print();
    	}
	}
}
